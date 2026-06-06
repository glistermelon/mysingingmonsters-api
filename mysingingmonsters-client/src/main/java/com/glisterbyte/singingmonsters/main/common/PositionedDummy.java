package com.glisterbyte.singingmonsters.main.common;

import com.glisterbyte.singingmonsters.main.catalog.Entity;

public class PositionedDummy implements Positioned {

    public Position position;
    public Size size;
    public Entity entity;
    public boolean flipped;

    public PositionedDummy(Entity entity, Position position, Size size, boolean flipped) {
        this.entity = entity;
        this.position = position;
        this.size = size;
        this.flipped = flipped;
    }

    @Override
    public Entity getEntity() {
        return entity;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public boolean isFlipped() {
        return flipped;
    }

}