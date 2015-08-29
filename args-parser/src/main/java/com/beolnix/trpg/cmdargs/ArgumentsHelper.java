package com.beolnix.trpg.cmdargs;

import com.beolnix.trpg.cmdargs.model.CommandLineArgument;
import com.beolnix.trpg.cmdargs.model.PassedArgument;


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
     * @param parsedArguments
     * @return
     */
    public static boolean consistOfHelp(Collection<PassedArgument> parsedArguments) {
        return consistOf(parsedArguments, helpCommandLineArgument);
    }


    /**
     * Method returns true if arg was provided
     * @param parsedArguments collection of parsed command line arguments
     * @param arg argument to identify whether it was provided or not
     * @return
     */
    public static boolean consistOf(Collection<PassedArgument> parsedArguments, CommandLineArgument arg) {
        return parsedArguments.stream()
                .anyMatch(parsedArg ->
                                arg.equals(parsedArg.getCommandLineArgument())
                );
    }

    /**
     * Method returns value of command line argument if it was provided. If it wasn't - method returns null;
     * @param parsedArguments
     * @param arg
     * @return
     */
    public static String getArgumentValue(Map<CommandLineArgument, PassedArgument> parsedArguments, CommandLineArgument arg) {
        Optional<PassedArgument> argResult = Optional.of(parsedArguments.get(arg));
        if (argResult.isPresent()) {
            return argResult.get().getValue();
        } else {
            return null;
        }
    }
}
