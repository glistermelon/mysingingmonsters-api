package com.glisterbyte.singingmonsters.sfsmodels;

public class MissingCommandException extends RuntimeException {

    private final String command;

    public MissingCommandException(String command) {
        super("Could not find response type for command '" + command + "'");
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

}