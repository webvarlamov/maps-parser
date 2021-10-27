package com.webvarlamov.leafletparser.service;

import com.webvarlamov.leafletparser.entity.Tile;
import com.webvarlamov.leafletparser.model.Coord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TileBuilderService {
    @Autowired
    TileLoaderService tileLoaderService;

    public Tile getTileByCoords(Coord coords) {
        return new Tile(
                this.getTileUIDByCoord(coords),
                tileLoaderService.loadTileDataByCoord(coords),
                coords.z
        );
    }

    public String getTileUIDByCoord(Coord coord) {
        return coord.z + "." + coord.x + "." + coord.y;
    }
}
