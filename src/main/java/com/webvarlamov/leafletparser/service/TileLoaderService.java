package com.webvarlamov.leafletparser.service;

import com.webvarlamov.leafletparser.model.Coord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class TileLoaderService {
    private final Logger logger = LoggerFactory.getLogger(OsmParsingManager.class);

    @Value("${tileserver.host}")
    String host;
    @Value("${tileserver.uri}")
    String uri;

    @Autowired
    RestTemplate restTemplate;

    public byte[] loadTileDataByCoord(Coord coord) {
        String url = this.getTileUrlByCoords(coord);

        try {
            byte[] imageBytes = restTemplate.getForObject(url, byte[].class);
            logger.info("Tile by " + url + " loading success");
            return imageBytes;
        } catch (RestClientException restClientException) {
            logger.error("Tile by " + url + " loading error");
            restClientException.printStackTrace();
            return new byte[0];
        }
    }

    public String getTileUrlByCoords(Coord coord) {
        return this.host + this.uri
                .replace("{z}", String.valueOf(coord.z))
                .replace("{x}", String.valueOf(coord.x))
                .replace("{y}", String.valueOf(coord.y));
    }
}
