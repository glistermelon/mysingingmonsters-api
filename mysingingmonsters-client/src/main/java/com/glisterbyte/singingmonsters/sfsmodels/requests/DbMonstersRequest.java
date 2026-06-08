package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsHasChunkedResponse;
import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.DbRequestModel;

/**
 * Queries the database of monster species.
 */
@SfsCmd("db_monster")
@SfsHasChunkedResponse
public class DbMonstersRequest extends DbRequestModel { }