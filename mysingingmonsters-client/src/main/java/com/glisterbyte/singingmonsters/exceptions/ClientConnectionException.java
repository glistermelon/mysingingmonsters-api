package com.glisterbyte.singingmonsters.exceptions;

public class ClientConnectionException extends ClientException {

    public ClientConnectionException(Throwable ex) {
        super(ex);
    }

    public ClientConnectionException(String message) {
        super(message);
    }

    public ClientConnectionException(String message, Throwable ex) {
        super(message, ex);
    }

}
