package com.glisterbyte.singingmonsters.networking.exceptions;

import com.glisterbyte.singingmonsters.exceptions.ClientException;

import java.io.IOException;

public class ClientDeserializeException extends ClientException {

    public ClientDeserializeException(IOException cause) {
        super(cause);
    }

    public ClientDeserializeException(IllegalStateException cause) {
        super(cause);
    }

}