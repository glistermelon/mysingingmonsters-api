package com.glisterbyte.SingingMonsters.SfsModels.Server;

import com.glisterbyte.SfsMapping.SfsArrayElementType;

import java.util.List;

public class SfsMonsterInfo {

    // Skipped Keys:
    // select_sound
    // portrait_graphic
    // y_offset
    // view_in_market
    // premium
    // view_in_starmarket
    // min_server_version
    // graphic
    // level
    // time_availability
    // entity_type (always "monster")
    // spore_graphic
    // last_changed
    // levels
    // class
    
    public int costCoins;
    public int costKeys;
    public int costSale;
    public int costRelics;
    public int costDiamonds;
    public int costStarpower;
    public int costMedals;
    public int costEthCurrency;
    
    public int sizeX;
    public int sizeY;

    public int buildTime;

    // Just the English name of the monster.
    // This is only included here so the script
    // which generates some of the functions below
    // can take the English names from the cache.
    public String commonName;

    public String name; // codename
    public String description; // codename, usually name + "_DESC"
    public String fam;
    @SfsArrayElementType(SfsMonsterLike.class)
    public List<SfsMonsterLike> happiness;
    public String genes;

    public int monsterId;
    public int entityId;

    public int timeToFillSec;

    public int xp;

    public int beds;

    public String levelupIsland;

    @SfsArrayElementType(String.class)
    public List<String> names;

}
