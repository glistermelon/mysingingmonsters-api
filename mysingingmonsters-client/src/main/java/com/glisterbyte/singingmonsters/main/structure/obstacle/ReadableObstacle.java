package com.glisterbyte.singingmonsters.main.structure.obstacle;

import com.glisterbyte.singingmonsters.main.common.HasTimer;
import com.glisterbyte.singingmonsters.main.common.MultiCurrencyValue;
import com.glisterbyte.singingmonsters.main.common.Timer;
import com.glisterbyte.singingmonsters.common.StringUtil;
import com.glisterbyte.singingmonsters.main.structure.ReadableStructure;

import java.util.Optional;

public interface ReadableObstacle extends ReadableStructure {

    Timer getRemovalTimer();

    default MultiCurrencyValue getRemovalCost() {
        return getStructureType().getCost();
    }

    default boolean isBeingRemoved() {
        return getRemovalTimer() != null;
    }

    default boolean isRemovalDone() {
        return Optional.ofNullable(getRemovalTimer()).map(HasTimer::isDone).orElse(false);
    }

    default String toString(String superStr) {
        return StringUtil.format("Obstacle({})", superStr);
    }

}