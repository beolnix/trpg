package com.beolnix.trpg.cmdargs;

import com.beolnix.trpg.cmdargs.model.CommandLineArgument;


import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static com.beolnix.trpg.cmdargs.DefaultArguments.helpCommandLineArgument;

/**
 * Helper is used to identify whether specific command line arguments are provided or not.
 * Created by beolnix on 29/08/15.
 */
public class ArgumentsHelper {
    private ArgumentsHelper() {
    }

    /**
     * Method returns true if help argument was provided
     * @param passedArgs
     * @return
     */
    public static boolean consistOfHelp(Map<CommandLineArgument, String> passedArgs) {
        return passedArgs.containsKey(helpCommandLineArgument);
    }

}
