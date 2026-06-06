package com.glisterbyte.singingmonsters.main.structure.nursery;

import com.glisterbyte.singingmonsters.main.monster.ControllableMonster;
import com.glisterbyte.singingmonsters.main.monster.MonsterPlacement;
import com.glisterbyte.singingmonsters.main.catalog.MonsterSpecies;
import com.glisterbyte.singingmonsters.exceptions.ClientException;

public interface ControllableNursery {

    void buyEgg(MonsterSpecies species) throws InterruptedException, ClientException;

    ControllableMonster hatchEgg(MonsterPlacement placement) throws InterruptedException, ClientException;
    ControllableMonster hatchEgg() throws InterruptedException, ClientException;

    void sellEgg() throws InterruptedException, ClientException;

}