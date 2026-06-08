package com.glisterbyte.singingmonsters.sfsmodels.data;

import com.glisterbyte.singingmonsters.sfsmapping.SfsOptional;
import com.glisterbyte.singingmonsters.sfsmodels.SfsModel;

public class SfsMonsterMultiUpdate extends SfsModel {

    public long user_monster_id;

    @SfsOptional
    public Integer happiness;

    @SfsOptional
    public Long last_collection;

}