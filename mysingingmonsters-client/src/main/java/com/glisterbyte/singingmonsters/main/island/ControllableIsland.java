package com.glisterbyte.singingmonsters.main.island;

import com.glisterbyte.singingmonsters.main.catalog.MonsterSpecies;
import com.glisterbyte.singingmonsters.main.catalog.MultiMonsterSpecies;
import com.glisterbyte.singingmonsters.main.catalog.StructureType;
import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.main.common.Position;
import com.glisterbyte.singingmonsters.main.monster.ControllableMonster;
import com.glisterbyte.singingmonsters.main.monster.MonsterPlacement;
import com.glisterbyte.singingmonsters.main.monster.ReadableMonster;
import com.glisterbyte.singingmonsters.main.structure.ControllableStructure;
import com.glisterbyte.singingmonsters.main.structure.breeding.ControllableBreedingStructure;
import com.glisterbyte.singingmonsters.main.structure.nursery.ControllableNursery;

public interface ControllableIsland {

    ControllableStructure buyStructure(StructureType type, Position position, boolean flip, double scale)
            throws InterruptedException, ClientException;

    ControllableStructure buyStructure(StructureType type, Position position, boolean flip)
            throws InterruptedException, ClientException;

    ControllableStructure buyStructure(StructureType type, Position position)
            throws InterruptedException, ClientException;

    ControllableStructure buyStructure(StructureType type)
            throws InterruptedException, ClientException;

    ControllableNursery buyMonsterEgg(MonsterSpecies species)
            throws InterruptedException, ClientException;

    ControllableNursery buyMonsterEgg(MultiMonsterSpecies multiSpecies)
            throws InterruptedException, ClientException;

    ControllableMonster buyMonster(MonsterSpecies species, MonsterPlacement placement)
            throws InterruptedException, ClientException;

    ControllableMonster buyMonster(MultiMonsterSpecies multiSpecies, MonsterPlacement placement)
            throws InterruptedException, ClientException;

    ControllableMonster buyMonster(MonsterSpecies species)
            throws InterruptedException, ClientException;

    ControllableMonster buyMonster(MultiMonsterSpecies multiSpecies)
            throws InterruptedException, ClientException;

    ControllableBreedingStructure breed(ReadableMonster firstMonster, ReadableMonster secondMonster)
            throws InterruptedException, ClientException;

    ControllableBreedingStructure breed(MultiMonsterSpecies firstSpecies, MultiMonsterSpecies secondSpecies)
            throws InterruptedException, ClientException;
    
}