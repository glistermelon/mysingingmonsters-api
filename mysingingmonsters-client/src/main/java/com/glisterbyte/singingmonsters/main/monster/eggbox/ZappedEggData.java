package com.glisterbyte.singingmonsters.main.monster.eggbox;

import com.glisterbyte.singingmonsters.main.catalog.MultiMonsterSpecies;

public record ZappedEggData(
        MultiMonsterSpecies multiMonsterSpecies,
        int zappedCount,
        int requiredCount
) {
    public int remainingCount() {
        return requiredCount - zappedCount;
    }
}