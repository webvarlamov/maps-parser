package com.webvarlamov.leafletparser.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Tile {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Column(unique = true)
    private String uid;
    private int zoom;

    @Lob
    @Column(length=100000)
    private byte[] data;

    public Tile(String uid,  byte[] data, int zoom) {
        this.zoom = zoom;
        this.uid = uid;
        this.data = data;
    }
}
