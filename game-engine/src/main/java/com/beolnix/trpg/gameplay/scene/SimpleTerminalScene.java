package com.beolnix.trpg.gameplay.scene;

import com.beolnix.trpg.terminal.model.InputOption;
import com.beolnix.trpg.terminal.model.UserInputRequest;
import com.beolnix.trpg.terminal.SimpleTerminal;
import com.beolnix.trpg.terminal.impl.InteractiveConsole;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by beolnix on 30/08/15.
 */
public abstract class SimpleTerminalScene implements Scene {

    private SimpleTerminal terminal =
            new InteractiveConsole(
                    new BufferedWriter(new OutputStreamWriter(System.out)),
                    new BufferedReader(new InputStreamReader(System.in)));

    protected void print(String text) {
        terminal.print(text);
    }

    protected InputOption askUserInput(UserInputRequest userInputRequest) {
        return terminal.askUserInput(userInputRequest);
    }

    private void setTerminal(SimpleTerminal terminal) {
        this.terminal = terminal;
    }

}
