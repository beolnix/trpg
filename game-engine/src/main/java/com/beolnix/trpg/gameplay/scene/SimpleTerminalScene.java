package com.beolnix.trpg.gameplay.scene;

import com.beolnix.trpg.terminal.model.InputOption;
import com.beolnix.trpg.terminal.model.UserInputRequest;
import com.beolnix.trpg.terminal.SimpleTerminal;
import com.beolnix.trpg.terminal.impl.InteractiveConsole;

/**
 * Created by beolnix on 30/08/15.
 */
public abstract class SimpleTerminalScene implements Scene {

    private SimpleTerminal terminal = new InteractiveConsole();

    protected void print(String text) {
        terminal.print(text);
    }

    protected InputOption askUserInput(UserInputRequest userInputRequest) {
        return terminal.askUserInput(userInputRequest);
    }

}
