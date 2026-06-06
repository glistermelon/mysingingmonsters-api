package com.glisterbyte.singingmonsters.main.structure.breeding;

import com.glisterbyte.singingmonsters.main.catalog.Catalog;
import com.glisterbyte.singingmonsters.main.catalog.MonsterSpecies;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsBreeding;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsIsland;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;

import java.time.Instant;

public record BreedingState(
        long breedingId,
        MonsterSpecies species1,
        MonsterSpecies species2,
        MonsterSpecies resultingSpecies,
        Instant startTime,
        Instant finishTime
) {

    public static BreedingState fromSfsBreeding(SfsBreeding sfsBreeding, Catalog catalog) {
        return new BreedingState(
                sfsBreeding.user_breeding_id,
                catalog.getMonsterSpecies(sfsBreeding.monster_1),
                catalog.getMonsterSpecies(sfsBreeding.monster_2),
                catalog.getMonsterSpecies(sfsBreeding.new_monster),
                Instant.ofEpochMilli(sfsBreeding.started_on),
                Instant.ofEpochMilli(sfsBreeding.complete_on)
        );
    }

    public static BreedingState fromSfsIsland(SfsIsland sfsIsland, SfsStructure sfsStructure, Catalog catalog) {
        for (SfsBreeding sfsBreeding : sfsIsland.breeding) {
            if (sfsBreeding.structure == sfsStructure.user_structure_id) {
                return BreedingState.fromSfsBreeding(sfsBreeding, catalog);
            }
        }
        return null;
    }

}