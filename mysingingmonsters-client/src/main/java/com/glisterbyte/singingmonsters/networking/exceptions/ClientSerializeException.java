package com.glisterbyte.singingmonsters.networking.exceptions;

import com.glisterbyte.singingmonsters.exceptions.ClientException;

public class ClientSerializeException extends ClientException {

    public ClientSerializeException(Throwable ex) {
        super(ex);
    }

}