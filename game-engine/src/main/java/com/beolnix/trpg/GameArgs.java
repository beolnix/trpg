package com.beolnix.trpg;

import com.beolnix.trpg.cmdargs.model.CommandLineArgument;

/**
 * Arguments supported by the game
 * Created by beolnix on 29/08/15.
 */
public class GameArgs {
    public final static CommandLineArgument savedGameCommandLineArgument = new CommandLineArgument(
            //description
            "Path to the file with saved game. " +
                    "If not provided starts new game and saves the progress to the current dir.",

            //example
            "-g ./saved_game.txt",

            //flags
            new String[]{"-g", "--game"}
    );

    public final static CommandLineArgument versionCommandLineArgument =
            new CommandLineArgument("Prints version.", "-v", new String[]{"-v", "--version"});

    private GameArgs() {
    }

    public static CommandLineArgument[] getAll() {
        return new CommandLineArgument[] {savedGameCommandLineArgument, versionCommandLineArgument};
    }

}
