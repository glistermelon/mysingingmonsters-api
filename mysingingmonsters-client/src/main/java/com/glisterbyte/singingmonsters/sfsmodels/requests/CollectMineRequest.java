package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

/**
 * Collects from a mine.
 * Fails if the mine isn't ready.
 */
@SfsCmd("gs_collect_from_mine")
public class CollectMineRequest extends SfsRequestModel { }