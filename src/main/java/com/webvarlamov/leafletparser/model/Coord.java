package com.webvarlamov.leafletparser.model;

import lombok.Data;

@Data
public class Coord {
    public int x;
    public int y;
    public int z;

    public Coord(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
