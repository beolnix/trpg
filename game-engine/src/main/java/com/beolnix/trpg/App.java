package com.beolnix.trpg;


import com.beolnix.trpg.cmdargs.ArgumentsHelper;
import com.beolnix.trpg.cmdargs.impl.DefaultArgumentsParser;
import com.beolnix.trpg.cmdargs.error.UnknownFlag;
import com.beolnix.trpg.cmdargs.model.CommandLineArgument;
import com.beolnix.trpg.gameplay.GameScenario;
import com.beolnix.trpg.model.Game;
import com.beolnix.trpg.terminal.SimpleTerminal;
import com.beolnix.trpg.terminal.impl.InteractiveConsole;
import com.beolnix.trpg.utils.GameMaster;
import com.beolnix.trpg.utils.VersionHelper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Map;

import static com.beolnix.trpg.GameArgs.savedGameCommandLineArgument;
import static com.beolnix.trpg.GameArgs.versionCommandLineArgument;


/**
 * Game start point.
 */
public class App {

    private final static String appName = "Tiny role play game";
    private final static DefaultArgumentsParser argsParser = new DefaultArgumentsParser(GameArgs.getAll());

    public static void main(String[] args) {

        Map<CommandLineArgument, String> parsedArguments = parseArgs(args);
        printHelpIfRequired(parsedArguments);
        printVersionIfRequired(parsedArguments);

        Game game = loadGame(parsedArguments);

        // It is not so hard to make the game accessible via .. telnet
        // just by replacing this terminal
        SimpleTerminal terminal = new InteractiveConsole(
                        new BufferedWriter(new OutputStreamWriter(System.out)),
                        new BufferedReader(new InputStreamReader(System.in)));

        new GameScenario(terminal).run(game);

    }

    private static Game loadGame(Map<CommandLineArgument, String> parsedArguments) {
        if (parsedArguments.containsKey(savedGameCommandLineArgument)) {
            String path = parsedArguments.get(savedGameCommandLineArgument);
            return GameMaster.loadOrCreateGame(path);
        }

        return GameMaster.createNewGame();
    }

    private static void printVersionIfRequired(Map<CommandLineArgument, String> parsedArguments) {
        if (parsedArguments.containsKey(versionCommandLineArgument)) {
            String version = VersionHelper.getVersion();
            System.out.println(appName + " version " + version);
            System.exit(1);
        }
    }

    private static void printHelpIfRequired(Map<CommandLineArgument, String> parsedArguments) {
        if (ArgumentsHelper.consistOfHelp(parsedArguments)) {
            System.out.println(argsParser.getHelpMessage());
            System.exit(1);
        }
    }

    private static Map<CommandLineArgument, String> parseArgs(String[] args) {

        try {
            return argsParser.parse(args);
        } catch (UnknownFlag unknownFlag) {
            System.out.println("ERROR: Unsupported command line argument given \"" + unknownFlag.getUnknownFlag() + "\"");
            System.out.println("Please use --help to see the list of supported arguments.");
            System.exit(1);
        }

        return Collections.emptyMap();
    }


}
