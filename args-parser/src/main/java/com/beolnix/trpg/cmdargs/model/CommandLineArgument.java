package com.beolnix.trpg.cmdargs.model;

import java.util.stream.Stream;

/**
 * Model describes details of supported Command Line Argument
 * Created by beolnix on 29/08/15.
 */
public class CommandLineArgument {

    private final String description;
    private final String example;
    private final String[] flags;

    public CommandLineArgument(String description, String example, String[] flags) {
        this.description = description;
        this.example = example;
        this.flags = flags;
    }

    public boolean consistFlag(String flagStr) {
        return Stream.of(this.flags)
                .anyMatch(flag -> flag.equals(flagStr));
    }

    public String getDescription() {
        return description;
    }

    public String getExample() {
        return example;
    }

    public String[] getFlags() {
        return flags;
    }

}
