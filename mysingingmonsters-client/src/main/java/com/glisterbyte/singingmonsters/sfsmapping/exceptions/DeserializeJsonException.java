package com.glisterbyte.singingmonsters.sfsmapping.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;

public class DeserializeJsonException extends MapFromSfsException {

    public DeserializeJsonException(String json, JsonProcessingException ex) {
        super("Failed to deserialize JSON due to exception; " + json, ex);
    }

}