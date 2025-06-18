package com.glisterbyte.SingingMonsters.SfsModels;

import com.glisterbyte.SfsMapping.SfsOptional;

public class SfsStructure {

    // Skipped Keys:
    // last_collection

    public int structure;
    public long island;
    public long userStructureId;

    @SfsOptional
    public Long dateCreated;

    public int posX;
    public int posY;
    public int flip;
    public int muted;
    public double scale;

    public int inWarehouse;

    public int isUpgrading;
    public int isComplete;

}
