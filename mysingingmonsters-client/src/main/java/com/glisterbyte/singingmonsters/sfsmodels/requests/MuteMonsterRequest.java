package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

@SfsCmd("gs_mute_monster")
public class MuteMonsterRequest extends SfsRequestModel {
    public long user_monster_id;
    public int muted;
}