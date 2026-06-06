package com.glisterbyte.singingmonsters.sfsmapping.exceptions;

public class SfsMapException extends RuntimeException {

    public SfsMapException(String message) {
        super(message);
    }

    public SfsMapException(Throwable ex) {
        super(ex);
    }

    public SfsMapException(String message, Throwable ex) {
        super(message, ex);
    }

}
