package com.glisterbyte.singingmonsters.main.structure.nursery;

import com.glisterbyte.singingmonsters.main.common.HasTimer;
import com.glisterbyte.singingmonsters.main.catalog.MonsterSpecies;
import com.glisterbyte.singingmonsters.common.StringUtil;
import com.glisterbyte.singingmonsters.main.structure.ReadableStructure;

import java.time.Instant;
import java.util.Optional;

public interface ReadableNursery extends ReadableStructure, HasTimer {

    NurseryState getNurseryState();

    default Optional<Long> getUserEggId() {
        return Optional.ofNullable(getNurseryState()).map(NurseryState::userEggId);
    }

    default MonsterSpecies getEggSpecies() {
        return Optional.ofNullable(getNurseryState()).map(NurseryState::monsterSpecies).orElse(null);
    }

    default Instant getStartTime() {
        return Optional.ofNullable(getNurseryState()).map(NurseryState::createdTime).orElse(null);
    }

    default Instant getFinishTime() {
        return Optional.ofNullable(getNurseryState()).map(NurseryState::hatchTime).orElse(null);
    }

    default boolean isEmpty() {
        return getNurseryState() == null;
    }

    default String toString(String superStr) {
        return StringUtil.format("Nursery(structure={}, egg={})", superStr, getNurseryState());
    }

}