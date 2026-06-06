package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

@SfsCmd("gs_buy_island")
public class BuyIslandRequest extends SfsRequestModel {
    public int islandId;
    public String islandName;
    public boolean starpowerPurchase;
}