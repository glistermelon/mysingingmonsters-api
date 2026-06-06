package com.glisterbyte.singingmonsters.main.structure.breeding;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.main.monster.ReadableMonster;
import com.glisterbyte.singingmonsters.main.monster.eggbox.ReadableEggBoxMonster;
import com.glisterbyte.singingmonsters.main.structure.ControllableStructure;

public interface ControllableBreedingStructure {
    void breed(ReadableMonster firstMonster, ReadableMonster secondMonster) throws InterruptedException, ClientException;
    ControllableStructure collectEgg() throws InterruptedException, ClientException;
    void zapEgg(ReadableEggBoxMonster monster) throws InterruptedException, ClientException;
}