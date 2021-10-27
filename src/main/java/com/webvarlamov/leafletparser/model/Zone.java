package com.webvarlamov.leafletparser.model;

public class Zone {
    public Integer zoom;
    public Integer maxX;
    public Integer maxY;
    public Integer minX;
    public Integer minY;

    public Zone(Integer zoom, Integer maxX, Integer maxY, Integer minX, Integer minY) {
        this.zoom = zoom;
        this.maxX = maxX;
        this.maxY = maxY;
        this.minX = minX;
        this.minY = minY;
    }
}
