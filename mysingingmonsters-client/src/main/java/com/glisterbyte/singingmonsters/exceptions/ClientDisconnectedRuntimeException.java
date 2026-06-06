package com.glisterbyte.singingmonsters.exceptions;

public class ClientDisconnectedRuntimeException extends RuntimeException {

    public ClientDisconnectedRuntimeException() {
        super("Client is disconnected");
    }

}