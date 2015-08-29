package com.beolnix.trpg;


import com.beolnix.trpg.cmdargs.ArgumentsHelper;
import com.beolnix.trpg.cmdargs.ArgumentsParser;
import com.beolnix.trpg.cmdargs.HelpPrinter;
import com.beolnix.trpg.cmdargs.error.UnknownFlag;
import com.beolnix.trpg.cmdargs.model.CommandLineArgument;
import com.beolnix.trpg.cmdargs.model.PassedArgument;

import java.util.Collections;
import java.util.List;

public class App {

    private static final ArgumentsParser argsParser = new ArgumentsParser(supportedFlags());

    public static void main(String[] args) {

        List<PassedArgument> parsedArguments = parseArgs(args);
        printHelpIfRequired(parsedArguments);

        System.out.println("we are running!");

    }

    private static void printHelpIfRequired(List<PassedArgument> parsedArguments) {
        if (ArgumentsHelper.consistOfHelp(parsedArguments)) {
            System.out.println(argsParser.getHelpMessage());
            System.exit(1);
        }
    }

    private static List<PassedArgument> parseArgs(String[] args) {

        try {
            return argsParser.transform(args);
        } catch (UnknownFlag unknownFlag) {
            System.out.println("ERROR: Unsupported command line argument given \"" + unknownFlag.getUnknownFlag() + "\"");
            System.out.println("Please use --help to see the list of supported arguments.");
            System.exit(1);
        }

        return Collections.emptyList();
    }

    public static CommandLineArgument[] supportedFlags() {
        CommandLineArgument savedGameCommandLineArgument = new CommandLineArgument(
                //description
                "Path to the file with saved game. " +
                "If not provided starts new game and saves the progress to the current dir.",

                //example
                "-g ./saved_game.txt",

                //flags
                new String[]{"-g", "--game"}
        );

        return new CommandLineArgument[] {savedGameCommandLineArgument};
    }
}
