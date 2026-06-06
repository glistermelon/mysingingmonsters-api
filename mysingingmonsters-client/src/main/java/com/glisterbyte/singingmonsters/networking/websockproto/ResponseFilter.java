package com.glisterbyte.singingmonsters.networking.websockproto;

import com.glisterbyte.singingmonsters.sfsmodels.SfsEventModel;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public record ResponseFilter(
        Predicate<SfsEventModel> filter,
        Class<? extends SfsEventModel> model
) {
    @Override @NotNull
    public String toString() {
        return "(Model name: " + model.getName() + ")";
    }
}