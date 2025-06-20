package com.glisterbyte.SingingMonsters.SfsModels.Client;

public class BuyIslandFailed extends SfsRequestFailed {

    public BuyIslandFailed() {
        super();
    }

    public BuyIslandFailed(String message) {
        super(message);
    }

    public BuyIslandFailed(String message, Exception ex) {
        super(message, ex);
    }

}
