package com.glisterbyte.singingmonsters.main.common;

import com.glisterbyte.singingmonsters.common.StringUtil;

import java.time.Duration;
import java.time.Instant;

public class Timer implements HasTimer {

    Instant startTime;
    Instant finishTime;

    public Timer(Instant startTime, Instant finishTime) {
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public Timer(Instant startTime, Duration duration) {
        this.startTime = startTime;
        finishTime = startTime.plus(duration);
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getFinishTime() {
        return finishTime;
    }

    @Override
    public String toString() {
        return StringUtil.format(
                "Timer(start={}, finish={}, duration={})",
                getStartTime(), getFinishTime(), getTotalDuration()
        );
    }

}