package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

@SfsCmd("gs_box_add_egg")
public class BoxEggRequest extends SfsRequestModel {
    public long user_egg_id;
    public long user_monster_id;
    public boolean underling;
}