package com.glisterbyte.SingingMonsters.SfsModels.Server;

import com.glisterbyte.SfsMapping.SfsArrayElementType;
import com.glisterbyte.SingingMonsters.SfsModels.SfsChunked;
import com.glisterbyte.SingingMonsters.SfsModels.SfsCmd;

import java.util.List;

@SfsCmd("db_structure")
@SfsChunked("structures_data")
public class DbStructuresResponse extends SfsResponseModel {
    @SfsArrayElementType(SfsStructureInfo.class)
    public List<SfsStructureInfo> structuresData;
}