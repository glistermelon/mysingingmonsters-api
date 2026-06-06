package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

@SfsCmd("gs_buy_egg")
public class BuyEggRequest extends SfsRequestModel {
    public int monster_id;
    public long quest_claim_id;
    public boolean starpower_purchase;
    public long structure_id;
}
