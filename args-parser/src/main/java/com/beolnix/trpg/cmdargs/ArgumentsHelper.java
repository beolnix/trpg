package com.beolnix.trpg.cmdargs;

import com.beolnix.trpg.cmdargs.model.PassedArgument;

import java.util.List;

/**
 * Created by beolnix on 29/08/15.
 */
public class ArgumentsHelper {
    private ArgumentsHelper() {
    }

    public static boolean consistOfHelp(List<PassedArgument> parsedArguments) {
        return parsedArguments.stream()
                .anyMatch(parsedArg ->
                        ArgumentsParser.helpCommandLineArgument.equals(parsedArg.getCommandLineArgument())
                );
    }
}
