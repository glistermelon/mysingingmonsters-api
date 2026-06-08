package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

/**
 * Buys an island.
 */
@SfsCmd("gs_buy_island")
public class BuyIslandRequest extends SfsRequestModel {

    /**
     * Island type ID
     */
    public int island_id;

    /**
     * If purchasing Tribal Island, the name of the tribe to be created.
     * Otherwise, any value is accepted, but the authentic client would pass an empty string.
     */
    public String island_name;

    public boolean starpower_purchase;

}