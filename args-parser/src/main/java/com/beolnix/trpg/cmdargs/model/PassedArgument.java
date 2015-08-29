package com.beolnix.trpg.cmdargs.model;

/**
 * Created by beolnix on 29/08/15.
 */
public class PassedArgument {
    private final CommandLineArgument commandLineArgument;
    private final String value;

    public PassedArgument(CommandLineArgument commandLineArgument, String value) {
        this.commandLineArgument = commandLineArgument;
        this.value = value;
    }

    public CommandLineArgument getCommandLineArgument() {
        return commandLineArgument;
    }

    public String getValue() {
        return value;
    }
}
