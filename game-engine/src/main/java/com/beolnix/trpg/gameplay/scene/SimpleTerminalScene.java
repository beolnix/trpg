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
 * It's archetype for all screens,
 * to correctly configure terminal used for interaction with the player.
 *
 * Any new Scene must extends this archetype
 *
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

    protected void println(String text) {
        terminal.println(text);
    }

    protected InputOption askUserInput(UserInputRequest userInputRequest) {
        return terminal.askUserInput(userInputRequest);
    }

    public void setTerminal(SimpleTerminal terminal) {
        this.terminal = terminal;
    }

    @Override
    public SimpleTerminal getTerminal() {
        return terminal;
    }
}
