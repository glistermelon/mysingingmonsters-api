package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsArrayElementType;
import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;
import com.smartfoxserver.v2.entities.data.SFSObject;

import java.util.List;

@SfsCmd("gs_multi_neighbors")
public class MultiUpdateMonsterRequest extends SfsRequestModel {

    @SfsArrayElementType(SFSObject.class)
    public List<SFSObject> entity_array;

    public boolean island_needs_happiness_update;

}