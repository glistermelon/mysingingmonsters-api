package com.glisterbyte.SingingMonsters.SfsModels.Server;

import com.glisterbyte.SfsMapping.SfsOptional;

public class SfsStructure extends SfsServerModel {

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

    @SfsOptional
    public Long lastCollection;

}