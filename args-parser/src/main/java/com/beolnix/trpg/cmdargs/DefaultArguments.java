package com.beolnix.trpg.cmdargs;

import com.beolnix.trpg.cmdargs.model.CommandLineArgument;

import java.util.Set;

/**
 * Created by beolnix on 29/08/15.
 */
public class DefaultArguments {

    public final static CommandLineArgument helpCommandLineArgument =
            new CommandLineArgument("Prints this help.", "-h", new String[]{"-h", "--help"});

    private DefaultArguments() {
    }

}
