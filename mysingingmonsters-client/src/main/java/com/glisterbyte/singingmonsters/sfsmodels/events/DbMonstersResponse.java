package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsArrayElementType;
import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsMonsterSpecies;
import com.glisterbyte.singingmonsters.sfsmodels.SfsChunkedDbResponse;

import java.util.ArrayList;
import java.util.List;

@SfsCmd("db_monster")
public class DbMonstersResponse extends SfsChunkedDbResponse {

    @SfsArrayElementType(SfsMonsterSpecies.class)
    public List<SfsMonsterSpecies> monsters_data = new ArrayList<>();

    public int getElementCount() {
        return monsters_data.size();
    }

}
