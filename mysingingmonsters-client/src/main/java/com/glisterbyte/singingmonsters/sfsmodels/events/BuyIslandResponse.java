package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsIsland;
import com.glisterbyte.singingmonsters.sfsmodels.SfsResultResponse;

@SfsCmd("gs_buy_island")
public class BuyIslandResponse extends SfsResultResponse {

    // skipped keys: songs, tracks

    public SfsIsland user_island;

}
