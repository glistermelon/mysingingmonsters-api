package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsArrayElementType;
import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmapping.SfsOptional;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructureType;
import com.glisterbyte.singingmonsters.sfsmodels.SfsChunkedDbResponse;

import java.util.ArrayList;
import java.util.List;

@SfsCmd("db_structure")
public class DbStructuresResponse extends SfsChunkedDbResponse {

    @SfsOptional
    @SfsArrayElementType(SfsStructureType.class)
    public List<SfsStructureType> structures_data = new ArrayList<>();

    public int getElementCount() {
        return structures_data == null ? 0 : structures_data.size();
    }

}