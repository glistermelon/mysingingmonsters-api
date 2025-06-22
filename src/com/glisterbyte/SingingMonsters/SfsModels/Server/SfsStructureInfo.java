package com.glisterbyte.SingingMonsters.SfsModels.Server;

import com.glisterbyte.SfsMapping.SfsArrayElementType;
import com.glisterbyte.SfsMapping.SfsJsonArray;
import com.glisterbyte.SfsMapping.SfsOptional;

import java.util.List;

public class SfsStructureInfo {

    // Skipped keys:
    // sound
    // y_offset
    // platforms
    // view_in_market
    // premium
    // view_in_starmarket
    // extra
    // min_server_version
    // graphic
    // requirements
    // show_in_levelup
    // entity_type (always "structure")
    // level (unlock level)

    public int costCoins;
    public int costKeys;
    public int costSale;
    public int costRelics;
    public int costDiamonds;
    public int costStarpower;
    public int costMedals;
    public int costEthCurrency;

    // nursery, breeding, mine, castle, bakery,
    // decoration, obstacle, time_machine, happiness_tree,
    // warehouse, hotel, torch, recording_studio, buddy
    public String structureType;

    public int sizeX;
    public int sizeY;

    public int buildTime;

    public String name; // codename
    public String description; // codename, usually name + "_DESC"

    public int structureId;
    public int entityId;

    public int xp;

    public int movable;
    public int sellable;

    public int upgradesTo; // structure ID, 0 if it doesn't upgrade

    @SfsOptional
    @SfsJsonArray
    @SfsArrayElementType(Integer.class)
    public List<Integer> allowedOnIsland; // serialized json int array of island type ids

}