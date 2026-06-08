package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

/**
 * Flips a monster.
 */
@SfsCmd("gs_flip_monster")
public class FlipMonsterRequest extends SfsRequestModel {
    public long user_monster_id;
    public boolean flipped;
}