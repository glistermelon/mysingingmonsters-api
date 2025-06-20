package com.glisterbyte.SingingMonsters.SfsModels.Client;

public class ChangeIslandFailed extends SfsRequestFailed {

    public ChangeIslandFailed() {
        super();
    }

    public ChangeIslandFailed(String message) {
        super(message);
    }

    public ChangeIslandFailed(String message, Exception ex) {
        super(message, ex);
    }

}
