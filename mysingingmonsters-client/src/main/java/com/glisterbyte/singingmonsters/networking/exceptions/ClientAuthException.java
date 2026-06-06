package com.glisterbyte.singingmonsters.networking.exceptions;

import com.glisterbyte.singingmonsters.exceptions.ClientException;

public class ClientAuthException extends ClientException {

    protected ClientAuthException(String message) {
        super(message);
    }

    protected ClientAuthException(Throwable cause) {
        super(cause);
    }

}