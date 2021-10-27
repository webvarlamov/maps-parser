package com.webvarlamov.leafletparser.repository;

import com.webvarlamov.leafletparser.entity.Tile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TileRepository extends CrudRepository<Tile, String> {
    Tile findTileByUid(String uid);
}
