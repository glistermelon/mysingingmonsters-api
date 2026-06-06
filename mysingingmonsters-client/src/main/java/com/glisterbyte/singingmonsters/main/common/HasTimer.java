package com.glisterbyte.singingmonsters.main.common;

import java.time.Duration;
import java.time.Instant;

public interface HasTimer {

    Instant getStartTime();
    Instant getFinishTime();

    default TimerState getTimerState() {
        Instant finishTime = getFinishTime();
        if (finishTime == null) return TimerState.IDLE;
        if (Instant.now().isAfter(finishTime)) return TimerState.DONE;
        return TimerState.PENDING;
    }

    default boolean isIdle() {
        return getTimerState() == TimerState.IDLE;
    }

    default boolean isPending() {
        return getTimerState() == TimerState.PENDING;
    }

    default boolean isDone() {
        return getTimerState() == TimerState.DONE;
    }

    default Duration getTotalDuration() {
        Instant startTime = getStartTime();
        Instant finishTime = getFinishTime();
        if (startTime == null || finishTime == null) return null;
        return Duration.between(startTime, finishTime);
    }

    default Duration getRemainingTime() {
        Instant finishTime = getFinishTime();
        if (finishTime == null) return null;
        Duration duration = Duration.between(Instant.now(), finishTime);
        if (duration.isNegative()) return Duration.ZERO;
        return duration;
    }

    default void waitUntilDone() throws InterruptedException {
        Thread.sleep(getRemainingTime().plusSeconds(2));
    }

}