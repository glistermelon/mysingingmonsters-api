package com.glisterbyte.singingmonsters.networking.exceptions;

public class ClientAuthFailedException extends ClientAuthException {

    private final Integer code;

    public ClientAuthFailedException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}