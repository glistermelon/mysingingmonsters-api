package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsUpdate;
import com.glisterbyte.singingmonsters.sfsmodels.SfsResultResponse;

@SfsCmd("gs_buy_structure")
public class BuyStructureResponse extends SfsResultResponse {
    public SfsStructure user_structure;
}