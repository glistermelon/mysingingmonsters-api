package com.glisterbyte.singingmonsters.sfsmodels.data;

import com.glisterbyte.singingmonsters.sfsmapping.SfsOptional;
import com.glisterbyte.singingmonsters.sfsmodels.SfsModel;

public class SfsTribe extends SfsModel {

    public String name;
    public long chief;
    public long members;
    public long rank;
    public int monster;

    public long user_island_id;

    @SfsOptional
    public Long date_created;

    @SfsOptional
    public Long last_updated;

    @SfsOptional
    public Long ends_on;

}