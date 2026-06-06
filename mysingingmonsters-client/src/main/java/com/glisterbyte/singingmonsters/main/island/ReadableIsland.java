package com.glisterbyte.singingmonsters.main.island;

import com.glisterbyte.singingmonsters.main.catalog.MonsterSpecies;
import com.glisterbyte.singingmonsters.main.catalog.MultiMonsterSpecies;
import com.glisterbyte.singingmonsters.main.client.HasClient;
import com.glisterbyte.singingmonsters.main.common.*;
import com.glisterbyte.singingmonsters.main.monster.ReadableMonster;
import com.glisterbyte.singingmonsters.main.structure.ReadableStructure;
import com.glisterbyte.singingmonsters.main.structure.StructureCategory;
import com.glisterbyte.singingmonsters.main.structure.bakery.ReadableBakery;
import com.glisterbyte.singingmonsters.main.structure.breeding.ReadableBreedingStructure;
import com.glisterbyte.singingmonsters.main.structure.castle.ReadableCastle;
import com.glisterbyte.singingmonsters.main.structure.mine.ReadableMine;
import com.glisterbyte.singingmonsters.main.structure.nursery.ReadableNursery;
import com.glisterbyte.singingmonsters.main.structure.obstacle.ReadableObstacle;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsIsland;

import java.time.Instant;
import java.util.List;

public interface ReadableIsland extends HasClient, HasSfsData {

    SfsIsland getSfsModel();

    IslandType getIslandType();
    long getUserIslandId();
    Instant getDateCreated();
    int getLikes();
    int getDislikes();
    int getScore();
    boolean isIslandMarkedForTorchLighting();
    double getTimeWarp();

    List<? extends ReadableMonster> getMonsters();
    ReadableMonster getMonster(long userMonsterId);

    List<? extends ReadableMonster> getMonstersOfSpecies(MonsterSpecies species);
    List<? extends ReadableMonster> getMonstersOfSpecies(MultiMonsterSpecies multiSpecies);
    ReadableMonster getMonsterOfSpecies(MonsterSpecies species);
    ReadableMonster getMonsterOfSpecies(MultiMonsterSpecies multiSpecies);

    List<? extends ReadableStructure> getStructures();
    ReadableStructure getStructure(long userStructureId);

    List<? extends ReadableStructure> getStructuresOfCategory(StructureCategory category);
    ReadableStructure getStructureOfCategory(StructureCategory category);

    List<? extends ReadableBakery> getBakeries();
    List<? extends ReadableBreedingStructure> getBreedingStructures();
    ReadableCastle getCastle();
    ReadableMine getMine();
    List<? extends ReadableNursery> getNurseries();
    List<? extends ReadableObstacle> getObstacles();
    List<? extends ReadableStructure> getDecorations();


    ReadableBakery getBakeryWithBakingId(long userBakingId);
    ReadableBreedingStructure getBreedingStructureWithBreedingId(long userBreedingId);
    ReadableNursery getNurseryWithEggId(long userEggId);

    List<Positioned> getObjects();
    List<Positioned> getNearbyObjects(Positioned origin, int radius);

    CurrencyType getCollectionCurrencyType();

    boolean areTilesOccupied(Position position, Size size);

}