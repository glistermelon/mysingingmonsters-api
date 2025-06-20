package com.glisterbyte.SingingMonsters.SfsModels.Server;

import com.glisterbyte.SfsMapping.SfsOptional;
import com.glisterbyte.SingingMonsters.SfsModels.SfsCmd;

@SfsCmd("gs_buy_island")
public class BuyIslandResponse extends SfsResponseModel {

    // skipped keys: songs, tracks

    public boolean success;

    @SfsOptional
    public String message;

    @SfsOptional
    public Update properties;

    @SfsOptional
    public SfsIsland userIsland;

    public boolean failed() {
        return !success;
    }
}
