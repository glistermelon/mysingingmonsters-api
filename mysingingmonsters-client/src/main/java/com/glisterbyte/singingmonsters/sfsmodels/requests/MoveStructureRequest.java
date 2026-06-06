package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

@SfsCmd("gs_move_structure")
public class MoveStructureRequest extends SfsRequestModel {
    public long user_structure_id;
    public int pos_x;
    public int pos_y;
    public double scale;
    public float volume;
}