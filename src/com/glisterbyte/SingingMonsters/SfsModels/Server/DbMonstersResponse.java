package com.glisterbyte.SingingMonsters.SfsModels.Server;

import com.glisterbyte.SfsMapping.SfsArrayElementType;
import com.glisterbyte.SingingMonsters.SfsModels.SfsChunked;
import com.glisterbyte.SingingMonsters.SfsModels.SfsCmd;

import java.util.List;

@SfsCmd("db_monster")
@SfsChunked("monsters_data")
public class DbMonstersResponse extends SfsResponseModel {
    @SfsArrayElementType(SfsMonsterInfo.class)
    public List<SfsMonsterInfo> monstersData;
}
