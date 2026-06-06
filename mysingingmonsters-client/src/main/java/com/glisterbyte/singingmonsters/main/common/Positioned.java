package com.glisterbyte.singingmonsters.main.common;

import com.glisterbyte.singingmonsters.main.catalog.Entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface Positioned {

    Position getPosition();
    boolean isFlipped();
    Size getSize();
    Entity getEntity();

    default List<Position> getOccupiedTiles() {
        List<Position> positions = new ArrayList<>();
        Size size = getSize();
        Position position = getPosition();
        int x = position.x();
        int y = position.y();
        for (int dx = 0; dx < size.x(); dx++) {
            for (int dy = 0; dy < size.y(); dy++) {
                positions.add(new Position(x + dx, y + dy));
            }
        }
        return positions;
    }

    default int distanceTo(Positioned other) {
        var otherTiles = other.getOccupiedTiles();
        var distance = getOccupiedTiles().stream().flatMap(
                tile -> otherTiles.stream().map(tile::distanceTo)
        ).min(Comparator.naturalOrder());
        assert distance.isPresent();
        return distance.get();
    }

    default boolean overlapsWith(Positioned other) {
        List<Position> tiles1 = getOccupiedTiles();
        List<Position> tiles2 = other.getOccupiedTiles();
        for (int i = 0; i < tiles1.size(); i++) {
            for (int j = i; j < tiles2.size(); j++) {
                Position tile1 = tiles1.get(i);
                Position tile2 = tiles2.get(j);
                if (tile1.equals(tile2)) return true;
            }
        }
        return false;
    }

    default boolean wouldOverlapWith(Position newPosition, Positioned other) {
        return new PositionedDummy(getEntity(), newPosition, getSize(), false).overlapsWith(other);
    }

}