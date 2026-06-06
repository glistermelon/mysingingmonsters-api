package com.glisterbyte.singingmonsters.exceptions;

public class ClientException extends Exception {

    public ClientException(String message) {
        super(message);
    }

    public ClientException(Throwable ex) {
        super(ex);
    }

    public ClientException(String message, Throwable ex) {
        super(message, ex);
    }

}
