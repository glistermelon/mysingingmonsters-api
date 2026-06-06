package com.glisterbyte.singingmonsters.networking.exceptions;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class ClientAuthParseException extends ClientAuthException {

    ObjectNode object;

    public ClientAuthParseException(ObjectNode object, String message) {
        super(message + "\nJSON: " + object);
        this.object = object;
    }

    ObjectNode getJson() {
        return object;
    }

}
