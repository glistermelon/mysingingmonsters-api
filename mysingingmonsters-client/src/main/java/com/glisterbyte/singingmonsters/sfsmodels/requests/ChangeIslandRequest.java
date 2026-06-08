package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

/**
 * Changes the active island.
 */
@SfsCmd("gs_change_island")
public class ChangeIslandRequest extends SfsRequestModel {
    public long user_island_id;
}