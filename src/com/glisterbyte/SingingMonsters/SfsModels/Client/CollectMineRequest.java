package com.glisterbyte.SingingMonsters.SfsModels.Client;

// TODO
// Check in wireshark if the empty params shows up as [] or {}
// Requests from the actual game show up as []
// Make sure it is consistent

import com.glisterbyte.SingingMonsters.SfsModels.Server.CollectMineResponse;
import com.glisterbyte.SingingMonsters.SfsModels.Server.UpdateStructure;
import com.glisterbyte.SingingMonsters.SfsModels.SfsCmd;

@SfsCmd("gs_collect_from_mine")
@SfsExpectResponse(CollectMineResponse.class)
@SfsExpectResponse(UpdateStructure.class)
public class CollectMineRequest extends SfsRequestModel {

}