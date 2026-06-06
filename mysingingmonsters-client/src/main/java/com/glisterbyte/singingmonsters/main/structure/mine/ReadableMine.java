package com.glisterbyte.singingmonsters.main.structure.mine;

import com.glisterbyte.singingmonsters.main.common.HasTimer;
import com.glisterbyte.singingmonsters.common.StringUtil;
import com.glisterbyte.singingmonsters.main.structure.ReadableStructure;

import java.time.Duration;
import java.time.Instant;

public interface ReadableMine extends ReadableStructure, HasTimer {

    Instant getLastCollectionTime();

    default boolean isReady() {
        return isDone();
    }

    @Override
    default Instant getStartTime() {
        return getLastCollectionTime();
    }

    @Override
    default Instant getFinishTime() {
        return getLastCollectionTime().plus(Duration.ofDays(1));
    }

    default String toString(String superStr) {
        return StringUtil.format("Mine(structure={}, ready={})", superStr, isReady());
    }

}