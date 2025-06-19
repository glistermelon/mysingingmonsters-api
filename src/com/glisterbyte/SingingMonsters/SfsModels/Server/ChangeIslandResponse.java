package com.glisterbyte.SingingMonsters.SfsModels.Server;

import com.glisterbyte.SfsMapping.SfsOptional;
import com.glisterbyte.SingingMonsters.SfsModels.SfsCmd;

@SfsCmd("gs_change_island")
public class ChangeIslandResponse extends SfsResponseModel {

    public long userIslandId;

    public boolean success;

    @SfsOptional
    public String message;

    public boolean failed() {
        return !success;
    }

}