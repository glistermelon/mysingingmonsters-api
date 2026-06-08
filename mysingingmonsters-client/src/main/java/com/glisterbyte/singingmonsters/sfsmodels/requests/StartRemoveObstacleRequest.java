package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

/**
 * Begins clearing of an obstacle (for a price).
 */
@SfsCmd("gs_start_obstacle")
public class StartRemoveObstacleRequest extends SfsRequestModel {
    public long user_structure_id;
}