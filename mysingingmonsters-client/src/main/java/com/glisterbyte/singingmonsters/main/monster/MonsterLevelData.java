package com.glisterbyte.singingmonsters.main.monster;

import com.glisterbyte.singingmonsters.sfsmodels.data.SfsMonsterLevel;

public record MonsterLevelData(
        int level,
        int foodRequirement,
        int coinsPerMinute,
        int maxCoins,
        int shardsPerHour,
        int maxShards
) {

    public static MonsterLevelData fromSfsMonsterLevel(SfsMonsterLevel sfs) {
        return new MonsterLevelData(
                sfs.level,
                sfs.food,
                sfs.coins != null ? sfs.coins : 0,
                sfs.max_coins != null ? sfs.max_coins : 0,
                sfs.ethereal_currency != null ? sfs.ethereal_currency : 0,
                sfs.max_ethereal != null ? sfs.max_ethereal : 0
        );
    }

}