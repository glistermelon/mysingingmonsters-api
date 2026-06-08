package com.glisterbyte.singingmonsters.sfsmodels.data;

import com.glisterbyte.singingmonsters.sfsmapping.SfsOptional;
import com.glisterbyte.singingmonsters.sfsmapping.SfsEntryArray;
import com.glisterbyte.singingmonsters.sfsmodels.SfsModel;

@SfsEntryArray
public class UpdateStructureProperties extends SfsModel {

    @SfsOptional
    public Long last_collection;

    @SfsOptional
    public Integer pos_x;

    @SfsOptional
    public Integer pos_y;

    @SfsOptional
    public Integer flip;

}