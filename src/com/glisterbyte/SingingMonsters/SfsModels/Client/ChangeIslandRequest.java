package com.glisterbyte.SingingMonsters.SfsModels.Client;

import com.glisterbyte.SingingMonsters.SfsModels.Server.ChangeIslandResponse;
import com.glisterbyte.SingingMonsters.SfsModels.SfsCmd;

@SfsCmd("gs_change_island")
@SfsExpectResponse(ChangeIslandResponse.class)
public class ChangeIslandRequest extends SfsRequestModel {
    public long userIslandId;
}