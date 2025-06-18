package com.glisterbyte.Network.ClientException;

public class InitializationFailed extends RuntimeException {
    public InitializationFailed(Exception ex) {
        super(ex);
    }
}
