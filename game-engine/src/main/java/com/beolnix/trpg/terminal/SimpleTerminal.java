package com.beolnix.trpg.terminal;

import com.beolnix.trpg.terminal.model.InputOption;
import com.beolnix.trpg.terminal.model.UserInputRequest;

/**
 * Terminal is used for the interaction with the user.
 * Created by beolnix on 30/08/15.
 */
public interface SimpleTerminal {

    /**
     * Ask user some input according to the userInput request.
     * Validates the input and if it is correct returns the input option was chosen by user
     * @param userInputRequest
     * @return
     */
    public InputOption askUserInput(UserInputRequest userInputRequest);

    /**
     * Displays text to user.
     * @param text
     */
    public void print(String text);

    /**
     * Displays text to user and moves caret to the new line.
     * @param text
     */
    public void println(String text);
}
