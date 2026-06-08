package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

/**
 * Starts baking a recipe of choice in a bakery of choice.
 */
@SfsCmd("gs_start_baking")
public class StartBakingRequest extends SfsRequestModel {

    /**
     * User structure ID of the bakery
     */
    public long user_structure_id;

    /**
     * The bakery recipe ID
     */
    public int food_id;

}
