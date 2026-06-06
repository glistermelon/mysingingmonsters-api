package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

@SfsCmd("gs_name_monster")
public class NameMonsterRequest extends SfsRequestModel {
    public long user_monster_id;
    public String name;
}