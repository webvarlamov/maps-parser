package com.webvarlamov.leafletparser.service;

import com.webvarlamov.leafletparser.entity.Tile;
import com.webvarlamov.leafletparser.model.Coord;
import com.webvarlamov.leafletparser.model.Zone;
import com.webvarlamov.leafletparser.repository.TileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class OsmParsingManager {
    private final Logger logger = LoggerFactory.getLogger(OsmParsingManager.class);

    @Autowired
    public RestTemplate restTemplate;
    @Autowired
    public TileRepository tileRepository;
    @Autowired
    public TileBuilderService tileBuilderService;

    @Async
    @Transactional
    public CompletableFuture<List<Tile>> loadTilesPartAsync(List<Coord> coords) {
        logger.info(" *** ParsingExecution is started");

        return CompletableFuture.completedFuture(coords.stream()
                .map(coord -> this.tileBuilderService.getTileByCoords(coord))
                .collect(Collectors.toList()));
    }

    public List<Coord> getCoords(Zone zone) {
        var coords = new ArrayList<Coord>();

        int maxX = zone.maxX != null ? zone.maxX : getCoordsLength(zone.zoom);
        int maxY = zone.maxY != null ? zone.maxY : getCoordsLength(zone.zoom);
        int minX = zone.minX != null ? zone.minX : 0;
        int minY = zone.minY != null ? zone.minY : 0;


        for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
                coords.add(new Coord(x, y, zone.zoom));
            }
        }
        return coords;
    }

    public int getCoordsLength(int zoom) {
        return (int) Math.pow(2, zoom);
    }
}
