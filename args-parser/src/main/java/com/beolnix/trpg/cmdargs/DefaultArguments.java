package com.beolnix.trpg.cmdargs;

import com.beolnix.trpg.cmdargs.model.CommandLineArgument;


/**
 * Place holder for the default command line attributes supported by Arguments Parser
 * Created by beolnix on 29/08/15.
 */
public class DefaultArguments {

    public final static CommandLineArgument helpCommandLineArgument =
            new CommandLineArgument("Prints this help.", "-h", new String[]{"-h", "--help"});

    private DefaultArguments() {
    }

}
