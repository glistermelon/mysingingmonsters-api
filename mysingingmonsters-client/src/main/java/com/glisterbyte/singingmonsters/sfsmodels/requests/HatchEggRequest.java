package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

@SfsCmd("gs_hatch_egg")
public class HatchEggRequest extends SfsRequestModel {
    public int costume;
    public int flip;
    public int pos_x;
    public int pos_y;
    public boolean store_in_hotel;
    public long user_egg_id;
}