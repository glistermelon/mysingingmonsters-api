package com.glisterbyte.singingmonsters.sfsmapping.exceptions;

public class MissingCommandException extends SfsMapException {

    final String command;

    public MissingCommandException(String command) {
        super("Unrecognized event command: '" + command + "'");
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

}
