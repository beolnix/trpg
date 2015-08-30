package com.beolnix.trpg.terminal.impl;

import com.beolnix.trpg.terminal.model.InputOption;
import com.beolnix.trpg.terminal.model.UserInputRequest;
import com.beolnix.trpg.terminal.SimpleTerminal;

import java.io.*;

/**
 * Created by beolnix on 30/08/15.
 */
public class InteractiveConsole implements SimpleTerminal {

    private final BufferedWriter writer;
    private final BufferedReader reader;

    public InteractiveConsole(BufferedWriter writer, BufferedReader reader) {
        this.writer = writer;
        this.reader = reader;
    }

    @Override
    public void print(String text) {
        try {
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            //nop
        }
    }

    @Override
    public void println(String text) {
        try {
            writer.write(text + "\n");
            writer.flush();
        } catch (IOException e) {
            //nop
        }
    }

    @Override
    public InputOption askUserInput(UserInputRequest userInputRequest) {
        printInputSuggestion(userInputRequest);
        printAvailableOptions(userInputRequest);
        print("Input: ");
        String userInput = getValidUserInput(userInputRequest);

        InputOption result = prepareInputResult(userInputRequest, userInput);
        if (result != null) {
            return result;
        } else {
            println("Something went wrong, lets try one more time.");
            return askUserInput(userInputRequest);
        }
    }

    private void printInputSuggestion(UserInputRequest userInputRequest) {
        println(userInputRequest.getDescription() + ": ");
    }

    private void printAvailableOptions(UserInputRequest userInputRequest) {
        for (InputOption inputOption : userInputRequest.getOptions()) {
            println("     " + inputOption.getExpectedInput() + " - " + inputOption.getDescription());
        }
    }

    private InputOption prepareInputResult(UserInputRequest userInputRequest, String userInput) {
        if (userInputRequest.getOptions().isEmpty() && userInput.length() > 0) {
            InputOption inputOption = new InputOption("", "");
            inputOption.setActualInput(userInput);
            return inputOption;
        }
        for (InputOption inputOption : userInputRequest.getOptions()) {
            if (inputOption.getExpectedInput().equals(userInput)) {
                inputOption.setActualInput(userInput);
                return inputOption;
            }
        }

        return null;
    }

    private String getValidUserInput(UserInputRequest userInputRequest) {
        try {
            String userInput = reader.readLine();
            if (validateInput(userInputRequest, userInput)) {
                return userInput;
            } else {
                println("Incorrect input, please try once again.");
                return getValidUserInput(userInputRequest);
            }
        } catch (IOException e) {
            println("Some error occured, lets try once again.");
            return getValidUserInput(userInputRequest);
        }
    }

    private boolean validateInput(UserInputRequest userInputRequest, String userInput) {
        if (userInputRequest.getOptions().isEmpty() && userInput.length() > 0) {
            return true;
        }
        for (InputOption inputOption : userInputRequest.getOptions()) {
            if (inputOption.getExpectedInput().equals(userInput)) {
                return true;
            }
        }

        return false;
    }
}
