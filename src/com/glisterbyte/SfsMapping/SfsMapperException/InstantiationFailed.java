package com.glisterbyte.SfsMapping.SfsMapperException;

public class InstantiationFailed extends RuntimeException {
    public InstantiationFailed(String message, Exception ex) {
        super(message, ex);
    }
}
