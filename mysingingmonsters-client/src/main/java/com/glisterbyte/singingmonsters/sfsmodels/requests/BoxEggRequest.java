package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

/**
 * Zaps an egg.
 */
@SfsCmd("gs_box_add_egg")
public class BoxEggRequest extends SfsRequestModel {

    /**
     * Despite the name, this is actually the user breeding ID corresponding to the egg.
     */
    public long user_egg_id;

    /**
     * The user monster ID of the monster to zap the egg into
     */
    public long user_monster_id;

    /**
     * When tested with Wublins, this is always true.
     */
    public boolean underling;

}