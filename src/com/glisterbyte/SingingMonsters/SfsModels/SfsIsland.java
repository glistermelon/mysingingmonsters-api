package com.glisterbyte.SingingMonsters.SfsModels;

import com.glisterbyte.SfsMapping.SfsArrayElementType;

import java.util.List;

public class SfsIsland {

    // Skipped Keys:
    // tribal_*
    // user
    // + all undetermined values @ commit 96e34ad045d3aa08f4cc8b79ea33cd2b3b4f7372

    public int island;
    public long dateCreated;
    public long userIslandId;

    public int likes;
    public int dislikes;

    public boolean lightTorchFlag;

    public double warpSpeed;

    @SfsArrayElementType(SfsMonster.class)
    public List<SfsMonster> monsters;

    @SfsArrayElementType(SfsStructure.class)
    public List<SfsStructure> structures;

}
