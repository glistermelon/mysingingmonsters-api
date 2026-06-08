package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

/**
 * Breeds two monsters with a breeding structure.
 */
@SfsCmd("gs_breed_monsters")
public class BreedMonstersRequest extends SfsRequestModel {

    /**
     * Breeding structure user structure ID
     */
    public long structure_id;

    /**
     * User monster ID
     */
    public long user_monster_id_1;

    /**
     * User monster ID
     */
    public long user_monster_id_2;

    /**
     * I don't know what a time mask is.
     */
    public short time_mask;

}