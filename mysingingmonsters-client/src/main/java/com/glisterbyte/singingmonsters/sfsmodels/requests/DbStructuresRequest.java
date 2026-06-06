package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsHasChunkedResponse;
import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.DbRequestModel;

@SfsCmd("db_structure")
@SfsHasChunkedResponse
public class DbStructuresRequest extends DbRequestModel { }