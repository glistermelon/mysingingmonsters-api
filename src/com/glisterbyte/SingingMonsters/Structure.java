package com.glisterbyte.SingingMonsters;

import com.glisterbyte.SfsMapping.SfsOptional;
import com.glisterbyte.SingingMonsters.SfsModels.SfsMonster;
import com.glisterbyte.SingingMonsters.SfsModels.SfsStructure;

import java.time.Instant;

public class Structure {

    private Island island;

    private Instant dateCreated;

    private Position position;
    private boolean flip;
    private boolean muted;
    private double scale;

    private boolean inWarehouse;
    private boolean isUpgrading;
    private boolean isComplete;

    public Structure(SfsStructure sfs, Island island) {
        this.island = island;
        dateCreated = sfs.dateCreated == null ? null : Instant.ofEpochMilli(sfs.dateCreated);
        position = new Position(sfs.posX, sfs.posY);
        flip = sfs.flip == 1;
        muted = sfs.muted == 1;
        scale = sfs.scale;
        inWarehouse = sfs.inWarehouse == 1;
        isUpgrading = sfs.isUpgrading == 1;
        isComplete = sfs.isComplete == 1;
    }

    public Island getIsland() {
        return island;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isFlipped() {
        return flip;
    }

    public boolean isMuted() {
        return muted;
    }

    public double getScale() {
        return scale;
    }

    public boolean isInWarehouse() {
        return inWarehouse;
    }

    public boolean isUpgrading() {
        return isUpgrading;
    }

    public boolean isComplete() {
        return isComplete;
    }
}
