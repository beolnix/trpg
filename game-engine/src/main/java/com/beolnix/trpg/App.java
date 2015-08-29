package com.beolnix.trpg;


import com.beolnix.trpg.cmdargs.ArgumentsHelper;
import com.beolnix.trpg.cmdargs.impl.DefaultArgumentsParser;
import com.beolnix.trpg.cmdargs.error.UnknownFlag;
import com.beolnix.trpg.cmdargs.model.CommandLineArgument;
import com.beolnix.trpg.cmdargs.model.PassedArgument;
import com.beolnix.trpg.model.Game;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.beolnix.trpg.GameArgs.savedGameCommandLineArgument;
import static com.beolnix.trpg.GameArgs.versionCommandLineArgument;

public class App {

    private final static String appName = "Tiny role play game";
    private final static DefaultArgumentsParser argsParser = new DefaultArgumentsParser(GameArgs.getAll());

    public static void main(String[] args) {

        Map<CommandLineArgument, PassedArgument> parsedArguments = parseArgs(args);
        printHelpIfRequired(parsedArguments.values());
        printVersionIfRequired(parsedArguments.values());

        Game game = loadGame(parsedArguments);

        GameScenario.run(game);

    }

    private static Game loadGame(Map<CommandLineArgument, PassedArgument> parsedArguments) {
        if (ArgumentsHelper.consistOf(parsedArguments.values(), savedGameCommandLineArgument)) {
            String path = ArgumentsHelper.getArgumentValue(parsedArguments, savedGameCommandLineArgument);
            return GameMaster.loadOrCreateGame(path);
        }

        return GameMaster.loadOrCreateGame();
    }

    private static void printVersionIfRequired(Collection<PassedArgument> parsedArguments) {
        if (ArgumentsHelper.consistOf(parsedArguments, versionCommandLineArgument)) {
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
