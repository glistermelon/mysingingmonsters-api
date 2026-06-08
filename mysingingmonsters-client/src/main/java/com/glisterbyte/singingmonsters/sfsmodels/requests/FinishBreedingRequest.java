package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

/**
 * For a breeding structure that has finished breeding,
 * moves the egg to a nursery (not of the user's choice).
 */
@SfsCmd("gs_finish_breeding")
public class FinishBreedingRequest extends SfsRequestModel {

    public long user_breeding_id;

    public boolean speedup;

    /**
     * In my experience this has always been 47.
     */
    public int purchase_type;

}
