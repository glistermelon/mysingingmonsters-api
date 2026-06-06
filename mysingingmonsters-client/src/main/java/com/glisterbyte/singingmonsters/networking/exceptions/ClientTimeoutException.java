package com.glisterbyte.singingmonsters.networking.exceptions;

import com.glisterbyte.singingmonsters.exceptions.ClientException;

public class ClientTimeoutException extends ClientException {
    public ClientTimeoutException(String message) {
        super(message);
    }
}