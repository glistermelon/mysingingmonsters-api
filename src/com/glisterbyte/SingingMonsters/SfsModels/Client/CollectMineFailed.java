package com.glisterbyte.SingingMonsters.SfsModels.Client;

public class CollectMineFailed extends SfsRequestFailed {

    public CollectMineFailed(String message) {
        super(message);
    }

    public CollectMineFailed(String message, Exception ex) {
        super(message, ex);
    }
}