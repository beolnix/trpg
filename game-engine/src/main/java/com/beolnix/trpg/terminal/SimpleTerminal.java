package com.beolnix.trpg.terminal;

import com.beolnix.trpg.terminal.model.InputOption;
import com.beolnix.trpg.terminal.model.UserInputRequest;

/**
 * Created by beolnix on 30/08/15.
 */
public interface SimpleTerminal {

    public InputOption askUserInput(UserInputRequest userInputRequest);
    public void print(String text);
    public void println(String text);
}
