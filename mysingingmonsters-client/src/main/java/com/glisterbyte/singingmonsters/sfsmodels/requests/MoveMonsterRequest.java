package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

@SfsCmd("gs_move_monster")
public class MoveMonsterRequest extends SfsRequestModel {
    public long user_monster_id;
    public int pos_x;
    public int pos_y;
    public double volume;
}