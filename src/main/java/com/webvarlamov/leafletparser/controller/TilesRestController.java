package com.webvarlamov.leafletparser.controller;

import com.webvarlamov.leafletparser.component.ParsingInitiatorComponent;
import com.webvarlamov.leafletparser.entity.Tile;
import com.webvarlamov.leafletparser.model.Coord;
import com.webvarlamov.leafletparser.repository.TileRepository;
import com.webvarlamov.leafletparser.service.TileBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TilesRestController {
    @Autowired
    TileRepository tileRepository;
    @Autowired
    TileBuilderService tileBuilderService;
    @Autowired
    ParsingInitiatorComponent parsingInitiatorComponent;

    @RequestMapping(value = "/tiles/**", method = RequestMethod.GET)
    public @ResponseBody byte[] getUserActivityCollectionResource(
            @RequestParam int x,
            @RequestParam int y,
            @RequestParam int z
    ) {
        String uid = tileBuilderService.getTileUIDByCoord(new Coord(x, y, z));
        Tile tileByUid = this.tileRepository.findTileByUid(uid);
        if (tileByUid != null) {
            return tileByUid.getData();
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/parse/**", method = RequestMethod.GET)
    public void parse(
            @RequestParam int zoom
    ) {
        this.parsingInitiatorComponent.parsingInit(zoom);
    }
}
