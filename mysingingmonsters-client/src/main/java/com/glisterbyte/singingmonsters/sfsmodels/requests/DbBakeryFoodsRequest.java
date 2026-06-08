package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.DbRequestModel;

/**
 * Queries the bakery recipe database.
 */
@SfsCmd("db_bakery_foods")
public class DbBakeryFoodsRequest extends DbRequestModel { }