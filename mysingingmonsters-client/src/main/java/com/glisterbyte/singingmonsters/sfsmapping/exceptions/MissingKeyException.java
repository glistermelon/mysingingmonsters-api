package com.glisterbyte.singingmonsters.sfsmapping.exceptions;

import com.smartfoxserver.v2.entities.data.ISFSObject;

public class MissingKeyException extends SfsMapException {

    private final ISFSObject sfsObject;

    public MissingKeyException(ISFSObject sfsObject, String message) {
        super(message + "; SFS data:" + sfsObject.getDump());
        this.sfsObject = sfsObject;
    }

    public ISFSObject getSfsObject() {
        return sfsObject;
    }

}
