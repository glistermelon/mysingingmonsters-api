package com.glisterbyte.singingmonsters.main.structure.breeding;

import com.glisterbyte.singingmonsters.main.common.HasTimer;
import com.glisterbyte.singingmonsters.main.catalog.MonsterSpecies;
import com.glisterbyte.singingmonsters.common.StringUtil;
import com.glisterbyte.singingmonsters.main.structure.ReadableStructure;

import java.time.Instant;
import java.util.Optional;

public interface ReadableBreedingStructure extends ReadableStructure, HasTimer {

    BreedingState getBreedingState();

    default Optional<Long> getUserBreedingId() {
        return Optional.ofNullable(getBreedingState()).map(BreedingState::breedingId);
    }

    default MonsterSpecies getFirstSpecies() {
        return Optional.ofNullable(getBreedingState()).map(BreedingState::species1).orElse(null);
    }

    default MonsterSpecies getSecondSpecies() {
        return Optional.ofNullable(getBreedingState()).map(BreedingState::species2).orElse(null);
    }

    default MonsterSpecies getResultingSpecies() {
        return Optional.ofNullable(getBreedingState()).map(BreedingState::resultingSpecies).orElse(null);
    }

    default Instant getStartTime() {
        return Optional.ofNullable(getBreedingState()).map(BreedingState::startTime).orElse(null);
    }

    default Instant getFinishTime() {
        return Optional.ofNullable(getBreedingState()).map(BreedingState::finishTime).orElse(null);
    }

    default String toString(String superStr) {
        return StringUtil.format(
                "BreedingStructure(structure={}, breeding={})",
                superStr, getBreedingState()
        );
    }

}