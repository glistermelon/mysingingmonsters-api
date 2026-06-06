package com.glisterbyte.singingmonsters.main.structure.nursery;

import com.glisterbyte.singingmonsters.main.catalog.Catalog;
import com.glisterbyte.singingmonsters.main.catalog.MonsterSpecies;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsIsland;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsMonsterEgg;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;

import java.time.Instant;

public record NurseryState(
        long userEggId,
        MonsterSpecies monsterSpecies,
        Instant createdTime,
        Instant hatchTime
) {

    public static NurseryState fromSfsMonsterEgg(SfsMonsterEgg sfsEgg, Catalog catalog) {
        return new NurseryState(
                sfsEgg.user_egg_id,
                catalog.getMonsterSpecies(sfsEgg.monster),
                Instant.ofEpochMilli(sfsEgg.laid_on),
                Instant.ofEpochMilli(sfsEgg.hatches_on)
        );
    }

    public static NurseryState fromSfsIsland(SfsIsland sfsIsland, SfsStructure sfsStructure, Catalog catalog) {
        for (SfsMonsterEgg sfsEgg : sfsIsland.eggs) {
            if (sfsEgg.structure == sfsStructure.user_structure_id) {
                return NurseryState.fromSfsMonsterEgg(sfsEgg, catalog);
            }
        }
        return null;
    }

}