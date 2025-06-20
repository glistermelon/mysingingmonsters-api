package com.glisterbyte.SingingMonsters.SfsModels.Server;

import com.glisterbyte.SingingMonsters.SfsModels.SfsCmd;

@SfsCmd("gs_update_structure")
public class UpdateStructure extends SfsResponseModel {
    public Update properties;
    public long userStructureId;
}