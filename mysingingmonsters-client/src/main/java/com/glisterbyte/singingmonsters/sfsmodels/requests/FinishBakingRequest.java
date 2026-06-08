package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

/**
 * Collects food from a bakery that has finished baking.
 */
@SfsCmd("gs_finish_baking")
public class FinishBakingRequest extends SfsRequestModel {
    public long user_baking_id;
}
