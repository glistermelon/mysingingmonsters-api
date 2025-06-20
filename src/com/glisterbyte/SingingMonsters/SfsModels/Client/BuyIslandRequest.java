package com.glisterbyte.SingingMonsters.SfsModels.Client;

import com.glisterbyte.SingingMonsters.SfsModels.Server.BuyIslandResponse;
import com.glisterbyte.SingingMonsters.SfsModels.SfsCmd;

@SfsCmd("gs_buy_island")
@SfsExpectResponse(BuyIslandResponse.class)
public class BuyIslandRequest extends SfsRequestModel {
    public int islandId;
    public String islandName;
    public boolean starpowerPurchase;
}