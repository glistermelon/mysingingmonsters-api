package com.glisterbyte.singingmonsters.main.common;

import java.time.Duration;

public enum TimerState {

    IDLE,
    PENDING,
    DONE;

    public static TimerState fromDuration(Duration duration) {
        return duration.isZero() ? DONE : PENDING;
    }

}