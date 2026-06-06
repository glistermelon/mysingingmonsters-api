package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

@SfsCmd("gs_box_activate_monster")
public class BoxActivateMonsterRequest extends SfsRequestModel {
    public long user_monster_id;
    public int validate_only;
}