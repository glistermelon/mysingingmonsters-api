package com.glisterbyte.singingmonsters.networking.exceptions;

import com.glisterbyte.singingmonsters.exceptions.ClientException;

public class ClientHttpRequestException extends ClientException {

    public ClientHttpRequestException(Exception ex) {
        super("HTTP failed due to exception: " + ex);
    }

}