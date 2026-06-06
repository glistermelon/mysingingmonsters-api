package com.glisterbyte.singingmonsters.sfsmapping.exceptions;

import com.smartfoxserver.v2.entities.data.SFSObject;

public class UnmappableTypeException extends SfsMapException {

    public UnmappableTypeException(Class<?> type) {
        super("Unmappable type: '" + type.getName() + "'");
    }

    public UnmappableTypeException(String message) {
        super(message);
    }

    public UnmappableTypeException(Class<?> expectedType, Object unexpectedObject) {
        super(
                "Encountered " + unexpectedObject.getClass().getName() + " where "
                        + expectedType.getName()+ " was expected; object value is " + (
                            unexpectedObject instanceof SFSObject
                                ? ((SFSObject) unexpectedObject).getDump(true) : unexpectedObject.toString()
                        )
        );
    }

}