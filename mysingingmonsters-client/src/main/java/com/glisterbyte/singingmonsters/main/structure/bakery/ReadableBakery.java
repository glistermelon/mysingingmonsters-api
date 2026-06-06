package com.glisterbyte.singingmonsters.main.structure.bakery;

import com.glisterbyte.singingmonsters.main.catalog.BakeryRecipe;
import com.glisterbyte.singingmonsters.main.common.HasTimer;
import com.glisterbyte.singingmonsters.common.StringUtil;
import com.glisterbyte.singingmonsters.main.structure.ReadableStructure;

import java.time.Instant;
import java.util.Optional;

public interface ReadableBakery extends ReadableStructure, HasTimer {

    BakeryState getBakeryState();

    default Optional<Long> getUserBakingId() {
        return Optional.ofNullable(getBakeryState()).map(BakeryState::bakingId);
    }

    default BakeryRecipe getCurrentRecipe() {
        return Optional.ofNullable(getBakeryState())
                .map(s -> getCatalog().getBakeryRecipe(s.foodId()))
                .orElse(null);
    }

    default Instant getStartTime() {
        return Optional.ofNullable(getBakeryState()).map(BakeryState::startTime).orElse(null);
    }

    default Instant getFinishTime() {
        return Optional.ofNullable(getBakeryState()).map(BakeryState::finishTime).orElse(null);
    }

    default boolean isCurrentlyBaking() {
        return isPending();
    }

    default String toString(String superStr) {
        return StringUtil.format("BakeryView(structure={}, baking={})", superStr, getBakeryState());
    }

}