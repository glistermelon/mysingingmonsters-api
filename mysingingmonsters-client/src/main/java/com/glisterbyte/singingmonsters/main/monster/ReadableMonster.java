package com.glisterbyte.singingmonsters.main.monster;

import com.glisterbyte.singingmonsters.main.catalog.MonsterSpecies;
import com.glisterbyte.singingmonsters.main.client.HasClient;
import com.glisterbyte.singingmonsters.main.common.*;
import com.glisterbyte.singingmonsters.main.island.ReadableIsland;

import java.time.Duration;
import java.time.Instant;

public interface ReadableMonster extends HasClient, HasSfsData, Positioned {

    ReadableIsland getIsland();
    long getUserMonsterId();
    MonsterSpecies getSpecies();
    String getName();
    boolean isMuted();
    double getVolume();
    int getLevel();
    int getTimesFed();
    MonsterHappiness getHappiness();
    boolean isInHotel();
    Instant getLastCollectTime();
    Instant getLastFeedTime();
    boolean isActivated();
    CurrencyType getCollectionCurrencyType();
//    EggBoxMonsterLike asEggBoxMonster();

//    default boolean isEggBoxMonster() {
//        return this instanceof EggBoxMonsterLike;
//    }

    default MonsterPlacement getPlacement() {
        return new MonsterPlacement(getPosition(), isFlipped());
    }

    @Override
    default Size getSize() {
        return getSpecies().getSize();
    }

    default boolean hasNoPolarity() {
        return getHappiness() == MonsterHappiness.PERCENT_0;
    }

    default boolean hasPositivePolarity() {
        return getHappiness().isPositive();
    }

    default boolean hasNegativePolarity() {
        return getHappiness().isNegative();
    }

    /*
        This is loosely based on game::Monster::earnings sand game::Monster::ethEarnings
        as decompiled from the game's binary. If you want to make this more accurate,
        I recommend starting at the function 'approxCollectAllAmt'.
    */
    default MultiCurrencyValue getCollectedCurrency() {
        CurrencyType islandCurrency = getIsland().getCollectionCurrencyType();
        if (islandCurrency == CurrencyType.COINS) {
            MonsterLevelData levelData = getSpecies().getMonsterLevelData(getLevel());
            double coinsPerSec = (double)levelData.coinsPerMinute() * (1.0 + 0.25 * getHappiness().asIndex()) / 60;
            double cumCoins = coinsPerSec * Duration.between(getLastCollectTime(), Instant.now()).toSeconds();
            int maxCoins = levelData.maxCoins();
            long clampedCoins = cumCoins < maxCoins ? (long)cumCoins : maxCoins;
            return MultiCurrencyValue.ofCoins(clampedCoins);
        }
        else if (islandCurrency == CurrencyType.SHARDS) {
            MonsterLevelData levelData = getSpecies().getMonsterLevelData(getLevel());
            double shardsPerSec = (double)levelData.shardsPerHour() * (1.0 + 0.25 * getHappiness().asIndex()) / 3600;
            double cumShards = shardsPerSec * Duration.between(getLastCollectTime(), Instant.now()).toSeconds();
            int maxShards = levelData.maxShards();
            long clampedShards = cumShards < maxShards ? (long)cumShards : maxShards;
            return MultiCurrencyValue.ofCoins(clampedShards);
        }
        else return null;
    }

}