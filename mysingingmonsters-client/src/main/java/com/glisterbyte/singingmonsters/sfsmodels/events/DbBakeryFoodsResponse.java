package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsArrayElementType;
import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.DbResponse;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsBakeryRecipe;

import java.util.ArrayList;
import java.util.List;

@SfsCmd("db_bakery_foods")
public class DbBakeryFoodsResponse extends DbResponse {

    @SfsArrayElementType(SfsBakeryRecipe.class)
    public List<SfsBakeryRecipe> bakery_data = new ArrayList<>();

    public int getElementCount() {
        return bakery_data.size();
    }

}