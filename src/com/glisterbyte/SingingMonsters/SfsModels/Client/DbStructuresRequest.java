package com.glisterbyte.SingingMonsters.SfsModels.Client;

import com.glisterbyte.SingingMonsters.SfsModels.Server.DbStructuresResponse;
import com.glisterbyte.SingingMonsters.SfsModels.SfsCmd;

@SfsCmd("db_structure")
@SfsExpectResponse(DbStructuresResponse.class)
public class DbStructuresRequest extends SfsRequestModel { }