package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsArrayElementType;
import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructureInfo;
import com.glisterbyte.singingmonsters.sfsmodels.SfsChunkedDbResponse;

import java.util.ArrayList;
import java.util.List;

@SfsCmd("db_structure")
public class DbStructuresResponse extends SfsChunkedDbResponse {

    @SfsArrayElementType(SfsStructureInfo.class)
    public List<SfsStructureInfo> structures_data = new ArrayList<>();

    public int getElementCount() {
        return structures_data.size();
    }

}