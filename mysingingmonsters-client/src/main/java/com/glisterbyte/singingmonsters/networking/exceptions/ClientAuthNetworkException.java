package com.glisterbyte.singingmonsters.networking.exceptions;

public class ClientAuthNetworkException extends ClientAuthException {

    public ClientAuthNetworkException(Exception ex) {
        super(ex);
    }

    public ClientAuthNetworkException(String message) {
        super(message);
    }

}
