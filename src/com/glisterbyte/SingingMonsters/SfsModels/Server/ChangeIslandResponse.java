package com.glisterbyte.SingingMonsters.SfsModels.Server;

import com.glisterbyte.SfsMapping.SfsOptional;
import com.glisterbyte.SingingMonsters.SfsModels.SfsCmd;

@SfsCmd("gs_change_island")
public class ChangeIslandResponse extends SfsResponseModel {

    @SfsOptional
    public Long userIslandId;

    public boolean success;

    public boolean failed() {
        return !success;
    }

}