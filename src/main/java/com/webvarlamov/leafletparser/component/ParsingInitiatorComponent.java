package com.webvarlamov.leafletparser.component;

import com.google.common.collect.Iterables;
import com.webvarlamov.leafletparser.entity.Tile;
import com.webvarlamov.leafletparser.model.Coord;
import com.webvarlamov.leafletparser.model.Zone;
import com.webvarlamov.leafletparser.repository.TileRepository;
import com.webvarlamov.leafletparser.service.OsmParsingManager;
import com.webvarlamov.leafletparser.service.TileSaverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ParsingInitiatorComponent {
    private final Logger logger = LoggerFactory.getLogger(ParsingInitiatorComponent.class);

    private final int ZOOM = 3;
    private final int REQUEST_DELAY = 300;
    private final int PARTITION_LENGTH = this.ZOOM * this.ZOOM;

    @Autowired
    OsmParsingManager osmParsingManager;
    @Autowired
    TileRepository tileRepository;
    @Autowired
    TileSaverService tileSaverService;

    // @PostConstruct
    public void parsingInit(int zoom) {
        Zone zone = new Zone(zoom, null, null, null, null);
        List<Coord> coords = osmParsingManager.getCoords(zone);

        logger.info("Total length is: " + coords.size());

        Iterables.partition(coords, 100).forEach(coordBatch -> {
            logger.info("Part length is: " + coordBatch.size());
            osmParsingManager.loadTilesPartAsync(coordBatch).thenApply(tiles -> {
                logger.info("Batch of tiles is laded. Size is:" + tiles.size());
                this.tileSaverService.saveTiles(tiles);
                return true;
            });
            logger.info("Part length is complete: " + coordBatch.size());
        });
    }
}
