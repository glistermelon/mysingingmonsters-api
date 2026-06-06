package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

@SfsCmd("gs_breed_monsters")
public class BreedMonstersRequest extends SfsRequestModel {
    public long structure_id;
    public long user_monster_id_1;
    public long user_monster_id_2;
    public short time_mask; // what is a time mask?
}