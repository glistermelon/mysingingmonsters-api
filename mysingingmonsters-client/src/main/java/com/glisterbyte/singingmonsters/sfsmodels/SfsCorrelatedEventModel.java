package com.glisterbyte.singingmonsters.sfsmodels;

import java.util.Optional;

public interface SfsCorrelatedEventModel {
    Optional<Long> getCorrelationId();
}