package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

/**
 * Activates a monster, like a Wublin.
 * The monster must be completely filled, or the request will fail.
 */
@SfsCmd("gs_box_activate_monster")
public class BoxActivateMonsterRequest extends SfsRequestModel {
    public long user_monster_id;
    public int validate_only;
}