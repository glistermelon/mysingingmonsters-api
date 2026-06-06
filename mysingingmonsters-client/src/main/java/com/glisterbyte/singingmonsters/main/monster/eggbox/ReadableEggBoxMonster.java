package com.glisterbyte.singingmonsters.main.monster.eggbox;

import com.glisterbyte.singingmonsters.main.common.CurrencyType;
import com.glisterbyte.singingmonsters.main.common.Timer;
import com.glisterbyte.singingmonsters.main.catalog.MonsterSpecies;
import com.glisterbyte.singingmonsters.main.catalog.MultiMonsterSpecies;
import com.glisterbyte.singingmonsters.main.monster.ReadableMonster;

import java.time.Instant;
import java.util.*;

public interface ReadableEggBoxMonster extends ReadableMonster {

    EggBoxMonsterData getInternalEggBoxMonsterData();

    default Timer getFillTimer() {
        return getInternalEggBoxMonsterData().getFillTimer();
    }

    default Map<MultiMonsterSpecies, Integer> getZappedEggCounterMap() {
        return getInternalEggBoxMonsterData().getZappedEggCounterMap();
    }

    default Map<MultiMonsterSpecies, Integer> getRequiredEggCounterMap() {
        return getInternalEggBoxMonsterData().getRequiredEggCounterMap();
    }

    default Instant getNextCollectionTime() {
        return getInternalEggBoxMonsterData().getNextCollectionTime();
    }

    default boolean isReadyToCollect() {
        return Instant.now().isAfter(getNextCollectionTime());
    }

    default ZappedEggData getEggData(MultiMonsterSpecies multiSpecies) {
        return new ZappedEggData(
                multiSpecies,
                getZappedEggCounterMap().getOrDefault(multiSpecies, 0),
                getRequiredEggCounterMap().getOrDefault(multiSpecies, 0)
        );
    }

    default ZappedEggData getEggData(MonsterSpecies species) {
        return getEggData(MultiMonsterSpecies.fromSpecies(species));
    }

    default List<ZappedEggData> getEggData() {
        Set<MultiMonsterSpecies> speciesList = new HashSet<>();
        speciesList.addAll(getZappedEggCounterMap().keySet());
        speciesList.addAll(getRequiredEggCounterMap().keySet());
        List<ZappedEggData> dataList = new ArrayList<>();
        for (MultiMonsterSpecies species : speciesList) {
            dataList.add(getEggData(species));
        }
        return dataList;
    }

    default int getRemainingEggCount(MultiMonsterSpecies species) {
        return getEggData(species).remainingCount();
    }

    default int getRemainingEggCount(MonsterSpecies species) {
        return getEggData(species).remainingCount();
    }

    default int getRequiredEggCount(MultiMonsterSpecies species) {
        return getEggData(species).requiredCount();
    }

    default int getRequiredEggCount(MonsterSpecies species) {
        return getEggData(species).requiredCount();
    }

    default int getZappedEggCount(MultiMonsterSpecies species) {
        return getEggData(species).zappedCount();
    }

    default int getZappedEggCount(MonsterSpecies species) {
        return getEggData(species).zappedCount();
    }

    default boolean needsEgg(MultiMonsterSpecies species) {
        return getRequiredEggCount(species) != 0;
    }

    default boolean needsEgg(MonsterSpecies species) {
        return needsEgg(MultiMonsterSpecies.fromSpecies(species));
    }

    default CurrencyType getNextCollectionType() {
        return getCollectionCurrencyType();
    }

    default boolean isReadyToActivate() {
        if (isActivated()) return false;
        return getRequiredEggCounterMap().entrySet().stream().allMatch(
                (entry) -> {
                    var species = entry.getKey();
                    var count = entry.getValue();
                    return getZappedEggCount(species) == count;
                }
        );
    }

}