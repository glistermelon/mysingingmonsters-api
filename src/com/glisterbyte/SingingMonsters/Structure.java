package com.glisterbyte.SingingMonsters;

import com.glisterbyte.Network.SfsClient;
import com.glisterbyte.SingingMonsters.Binds.ClientBound;
import com.glisterbyte.SingingMonsters.Binds.IslandBound;
import com.glisterbyte.SingingMonsters.SfsModels.Server.SfsStructure;
import com.glisterbyte.SingingMonsters.Structures.Mine;

import java.time.Instant;

public class Structure extends IslandBound {

    private final SfsStructure initialSfsModel;

    private Island island;

    private Instant dateCreated;

    private Position position;
    private boolean flip;
    private boolean muted;
    private double scale;

    private boolean inWarehouse;
    private boolean isUpgrading;
    private boolean isComplete;

    public Structure(Island island, SfsStructure sfsStructure) {
        super(island);
        initialSfsModel = sfsStructure;
        this.island = island;
        dateCreated = sfsStructure.dateCreated == null ? null : Instant.ofEpochMilli(sfsStructure.dateCreated);
        position = new Position(sfsStructure.posX, sfsStructure.posY);
        flip = sfsStructure.flip == 1;
        muted = sfsStructure.muted == 1;
        scale = sfsStructure.scale;
        inWarehouse = sfsStructure.inWarehouse == 1;
        isUpgrading = sfsStructure.isUpgrading == 1;
        isComplete = sfsStructure.isComplete == 1;
    }

    public static Structure buildStructure(Island island, SfsStructure sfsStructure) {
        return switch (StructureType.fromId(sfsStructure.structure)) {
            case StructureType.UNKNOWN -> new Structure(island, sfsStructure);
            case StructureType.MINE -> new Mine(island, sfsStructure);
        };
    }

    public SfsStructure getInitialSfsModel() {
        return initialSfsModel;
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
