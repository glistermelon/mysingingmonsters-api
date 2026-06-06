package com.glisterbyte.singingmonsters.main.monster;

import com.glisterbyte.singingmonsters.main.island.UnownedIsland;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsMonster;

public class UnownedMonster extends AbstractMonster {

    public UnownedMonster(UnownedIsland island, SfsMonster sfsMonster) {
        super(island, sfsMonster);
    }

    public synchronized UnownedIsland getIsland() {
        return (UnownedIsland)super.getIsland();
    }

}