package com.webvarlamov.leafletparser.service;

import com.webvarlamov.leafletparser.entity.Tile;
import com.webvarlamov.leafletparser.repository.TileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class TileSaverService {
    private final Logger logger = LoggerFactory.getLogger(TileSaverService.class);

    @Autowired
    public TileRepository tileRepository;

    public synchronized void saveTiles(List<Tile> tileList) {

        this.tileRepository.saveAll(tileList);
        logger.info("Tiles batch is saved");
    }
}
