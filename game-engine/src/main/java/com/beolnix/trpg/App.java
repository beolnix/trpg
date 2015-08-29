package com.beolnix.trpg;


import com.beolnix.trpg.cmdargs.ArgumentsHelper;
import com.beolnix.trpg.cmdargs.impl.ArgumentsParserImpl;
import com.beolnix.trpg.cmdargs.error.UnknownFlag;
import com.beolnix.trpg.cmdargs.model.CommandLineArgument;
import com.beolnix.trpg.cmdargs.model.PassedArgument;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class App {

    private final static String appName = "Tiny role play game";
    private final static ArgumentsParserImpl argsParser = new ArgumentsParserImpl(GameArgs.getAll());

    public static void main(String[] args) {

        Map<CommandLineArgument, PassedArgument> parsedArguments = parseArgs(args);
        printHelpIfRequired(parsedArguments.values());
        printVersionIfRequired(parsedArguments.values());

        System.out.println("we are running!");

    }

    private static void printVersionIfRequired(Collection<PassedArgument> parsedArguments) {
        if (ArgumentsHelper.consistOf(parsedArguments, GameArgs.versionCommandLineArgument)) {
            String version = VersionHelper.getVersion();
            System.out.println(appName + " version " + version);
            System.exit(1);
        }
    }

    private static void printHelpIfRequired(Collection<PassedArgument> parsedArguments) {
        if (ArgumentsHelper.consistOfHelp(parsedArguments)) {
            System.out.println(argsParser.getHelpMessage());
            System.exit(1);
        }
    }

    private static Map<CommandLineArgument, PassedArgument> parseArgs(String[] args) {

        try {
            return argsParser.transform(args);
        } catch (UnknownFlag unknownFlag) {
            System.out.println("ERROR: Unsupported command line argument given \"" + unknownFlag.getUnknownFlag() + "\"");
            System.out.println("Please use --help to see the list of supported arguments.");
            System.exit(1);
        }

        return Collections.emptyMap();
    }


}
