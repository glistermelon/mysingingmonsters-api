package com.glisterbyte.singingmonsters.sfsmodels;

import java.util.Optional;

public abstract class SfsCorrelatedEventModel extends SfsEventModel {
    public abstract Optional<Long> getCorrelationId();
}