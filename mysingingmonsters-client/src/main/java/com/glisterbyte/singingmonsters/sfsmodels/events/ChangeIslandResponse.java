package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsOptional;
import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsResultResponse;

@SfsCmd("gs_change_island")
public class ChangeIslandResponse extends SfsResultResponse {

    @SfsOptional
    public Long user_island_id;

    public boolean success;

}