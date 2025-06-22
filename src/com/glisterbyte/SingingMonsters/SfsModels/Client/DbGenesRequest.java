package com.glisterbyte.SingingMonsters.SfsModels.Client;

import com.glisterbyte.SingingMonsters.SfsModels.Server.DbGenesResponse;
import com.glisterbyte.SingingMonsters.SfsModels.SfsCmd;

@SfsCmd("db_gene")
@SfsExpectResponse(DbGenesResponse.class)
public class DbGenesRequest extends SfsRequestModel { }