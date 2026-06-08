package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

/**
 * Buys an egg from the market and starts incubating it in a nursery.
 * ```{warning}
 * On islands where incubation of an egg is not required, like Wublin Island,
 * monsters are bought via Hatch Egg.
 * ```
 */
@SfsCmd("gs_buy_egg")
public class BuyEggRequest extends SfsRequestModel {

    /**
     * Monster species ID
     */
    public int monster_id;

    public long quest_claim_id;
    public boolean starpower_purchase;

    /**
     * Nursery user structure id
     */
    public long structure_id;

}