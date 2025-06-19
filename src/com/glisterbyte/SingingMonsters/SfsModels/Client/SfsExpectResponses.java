package com.glisterbyte.SingingMonsters.SfsModels.Client;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SfsExpectResponses {
    SfsExpectResponse[] value();
}