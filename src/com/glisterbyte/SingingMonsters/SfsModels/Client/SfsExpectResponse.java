package com.glisterbyte.SingingMonsters.SfsModels.Client;

import com.glisterbyte.SingingMonsters.SfsModels.Server.SfsResponseModel;
import com.glisterbyte.SingingMonsters.SfsModels.Server.SfsServerModel;

import java.lang.annotation.*;

@Repeatable(SfsExpectResponses.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SfsExpectResponse {
    Class<? extends SfsResponseModel> value();
}