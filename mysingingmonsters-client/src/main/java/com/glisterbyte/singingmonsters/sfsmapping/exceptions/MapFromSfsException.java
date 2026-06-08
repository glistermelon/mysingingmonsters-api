package com.glisterbyte.singingmonsters.sfsmapping.exceptions;

import com.smartfoxserver.v2.entities.data.ISFSObject;

public class MapFromSfsException extends Exception {

    public MapFromSfsException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public MapFromSfsException(String message) {
        super(message);
    }

    public MapFromSfsException(Throwable throwable) {
        super(throwable);
    }

    public MapFromSfsException(ISFSObject sfsObject, MapFromSfsException cause) {
        super("Failed to map SFSObject due to exception; " + sfsObject.getDump(), cause);
    }

}