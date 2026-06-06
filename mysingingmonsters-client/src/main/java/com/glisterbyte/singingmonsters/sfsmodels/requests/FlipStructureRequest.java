package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

@SfsCmd("gs_flip_structure")
public class FlipStructureRequest extends SfsRequestModel {
    public long user_structure_id;
    public boolean flipped;
}