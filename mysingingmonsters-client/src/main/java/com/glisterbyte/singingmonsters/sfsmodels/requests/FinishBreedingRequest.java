package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

@SfsCmd("gs_finish_breeding")
public class FinishBreedingRequest extends SfsRequestModel {
    public long user_breeding_id;
    public boolean speedup;
    public int purchase_type;
}
