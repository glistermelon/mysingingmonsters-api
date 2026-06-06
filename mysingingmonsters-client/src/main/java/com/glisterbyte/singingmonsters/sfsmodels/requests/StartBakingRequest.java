package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

@SfsCmd("gs_start_baking")
public class StartBakingRequest extends SfsRequestModel {
    public long user_structure_id;
    public int food_id;
}
