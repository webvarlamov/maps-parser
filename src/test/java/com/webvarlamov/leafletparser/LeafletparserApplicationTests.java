package com.webvarlamov.leafletparser;

import com.webvarlamov.leafletparser.repository.TileRepository;
import com.webvarlamov.leafletparser.service.OsmParsingManager;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LeafletparserApplicationTests {
    private final Logger logger = LoggerFactory.getLogger(OsmParsingManager.class);

    private final int ZOOM = 3;
    private final int REQUEST_DELAY = 300;
    private final int PARTITION_LENGTH = this.ZOOM * this.ZOOM;

    @Autowired
    OsmParsingManager osmParsingManager;
    @Autowired
    TileRepository tileRepository;

    @Test
    void contextLoads() {
//        Zone zone = new Zone(this.ZOOM, null, null, null, null);
//        List<Coord> coords = osmParsingManager.getCoords(zone);
//
//        logger.info("Total length is: " + coords.size());
//
//        Lists.partition(coords, PARTITION_LENGTH)
//                .parallelStream()
//                .forEach(part -> {
//                    logger.info("Part length is: " + part.size());
//                    osmParsingManager.start(coords, this.REQUEST_DELAY);
//                    logger.info("Part length is complete: " + part.size());
//                });
    }

}
