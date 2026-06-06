package com.glisterbyte.singingmonsters.main.monster.eggbox;

import com.glisterbyte.singingmonsters.main.island.UnownedIsland;
import com.glisterbyte.singingmonsters.main.monster.UnownedMonster;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsMonster;

public class UnownedEggBoxMonster extends UnownedMonster implements ReadableEggBoxMonster {

    private final EggBoxMonsterData data;

    public UnownedEggBoxMonster(UnownedIsland island, SfsMonster sfsMonster) {
        super(island, sfsMonster);
        data = new EggBoxMonsterData(this, sfsMonster);
    }

    public synchronized EggBoxMonsterData getInternalEggBoxMonsterData() {
        return data;
    }

}