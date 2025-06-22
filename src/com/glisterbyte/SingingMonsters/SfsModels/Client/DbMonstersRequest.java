package com.glisterbyte.SingingMonsters.SfsModels.Client;

import com.glisterbyte.SingingMonsters.SfsModels.Server.DbMonstersResponse;
import com.glisterbyte.SingingMonsters.SfsModels.SfsCmd;

@SfsCmd("db_monster")
@SfsExpectResponse(DbMonstersResponse.class)
public class DbMonstersRequest extends SfsRequestModel { }