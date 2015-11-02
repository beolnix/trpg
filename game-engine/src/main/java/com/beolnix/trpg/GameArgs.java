package com.beolnix.trpg;

import com.beolnix.trpg.cmdargs.ArgumentsParser;
import com.beolnix.trpg.cmdargs.impl.DefaultArgumentsParser;
import com.beolnix.trpg.cmdargs.model.CommandLineArgument;
import com.beolnix.trpg.cmdargs.utils.CommandLineArgumentBuilder;

/**
 * Arguments supported by the game
 * Created by beolnix on 29/08/15.
 */
public class GameArgs {
    public final static CommandLineArgument savedGameCommandLineArgument = new CommandLineArgumentBuilder()
            .withDescription(
                    "Path to the file with saved game. " +
                            "If not provided starts new game and saves the progress to the current dir.")
            .withExample("-g ./saved_game.txt")
            .withFlags("-g", "--game")
            .build();

    public final static CommandLineArgument versionCommandLineArgument = new CommandLineArgumentBuilder()
            .withDescription("Prints version.")
            .withExample("-v")
            .withFlags("-v", "--version")
            .build();

    public final static ArgumentsParser argsParser =
            new DefaultArgumentsParser(savedGameCommandLineArgument, versionCommandLineArgument);

    private GameArgs() {
    }

}
