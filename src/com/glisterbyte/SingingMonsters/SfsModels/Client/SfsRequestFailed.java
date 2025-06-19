package com.glisterbyte.SingingMonsters.SfsModels.Client;

public class SfsRequestFailed extends RuntimeException {

    public SfsRequestFailed(String message) {
        super(message);
    }

    public SfsRequestFailed(String message, Exception ex) {
        super(message, ex);
    }

}