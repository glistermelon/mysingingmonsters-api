package com.glisterbyte.singingmonsters.exceptions;

public class ClientRequestFailedException extends ClientException {

    public ClientRequestFailedException(Throwable ex) {
        super(ex);
    }

    public ClientRequestFailedException(String message) {
        super(message);
    }

}
