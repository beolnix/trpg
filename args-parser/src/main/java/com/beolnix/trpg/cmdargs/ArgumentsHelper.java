package com.beolnix.trpg.cmdargs;

import com.beolnix.trpg.cmdargs.model.CommandLineArgument;
import com.beolnix.trpg.cmdargs.model.PassedArgument;

import java.util.List;

import static com.beolnix.trpg.cmdargs.DefaultArguments.helpCommandLineArgument;

/**
 * Created by beolnix on 29/08/15.
 */
public class ArgumentsHelper {
    private ArgumentsHelper() {
    }

    public static boolean consistOfHelp(List<PassedArgument> parsedArguments) {
        return consistOf(parsedArguments, helpCommandLineArgument);
    }


    public static boolean consistOf(List<PassedArgument> parsedArguments, CommandLineArgument arg) {
        return parsedArguments.stream()
                .anyMatch(parsedArg ->
                                arg.equals(parsedArg.getCommandLineArgument())
                );
    }
}
