package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsMonsterEgg;
import com.glisterbyte.singingmonsters.sfsmodels.SfsResultResponse;

@SfsCmd("gs_buy_egg")
public class BuyEggResponse extends SfsResultResponse {
    public SfsMonsterEgg user_egg;
    public boolean remove_buyback;
}
