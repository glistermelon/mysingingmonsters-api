package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

/**
 * Sells an egg from a nursery.
 */
@SfsCmd("gs_sell_egg")
public class SellEggRequest extends SfsRequestModel {
    public long user_egg_id;
}