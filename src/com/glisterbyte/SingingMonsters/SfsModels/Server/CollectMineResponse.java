package com.glisterbyte.SingingMonsters.SfsModels.Server;

import com.glisterbyte.SfsMapping.SfsOptional;
import com.glisterbyte.SingingMonsters.SfsModels.SfsCmd;

@SfsCmd("gs_collect_from_mine")
public class CollectMineResponse extends SfsResponseModel {

    public boolean success;

    @SfsOptional
    public String message;

    public boolean failed() {
        return !success;
    }

}