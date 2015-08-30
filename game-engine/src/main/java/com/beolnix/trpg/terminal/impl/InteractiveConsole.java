package com.beolnix.trpg.terminal.impl;

import com.beolnix.trpg.terminal.model.InputOption;
import com.beolnix.trpg.terminal.model.UserInputRequest;
import com.beolnix.trpg.terminal.SimpleTerminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by beolnix on 30/08/15.
 */
public class InteractiveConsole implements SimpleTerminal {

    @Override
    public void print(String text) {
        System.out.println(text);
    }

    public InputOption askUserInput(UserInputRequest userInputRequest) {
        printInputSuggestion(userInputRequest);
        printAvailableOptions(userInputRequest);
        System.out.print("Input: ");
        String userInput = getValidUserInput(userInputRequest);

        InputOption result = prepareInputResult(userInputRequest, userInput);
        if (result != null) {
            return result;
        } else {
            System.out.println("Something went wrong, lets try one more time.");
            return askUserInput(userInputRequest);
        }
    }

    private void printInputSuggestion(UserInputRequest userInputRequest) {
        System.out.println(userInputRequest.getDescription() + ": \n");
    }

    private void printAvailableOptions(UserInputRequest userInputRequest) {
        for (InputOption inputOption : userInputRequest.getOptions()) {
            System.out.println("     " + inputOption.getExpectedInput() + " - " + inputOption.getDescription());
        }
    }

    private InputOption prepareInputResult(UserInputRequest userInputRequest, String userInput) {
        if (userInputRequest.getOptions().size() < 1 && userInput.length() > 0) {
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
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String userInput = bufferRead.readLine();
            if (validateInput(userInputRequest, userInput)) {
                return userInput;
            } else {
                System.out.println("Incorrect input, please try once again.");
                return getValidUserInput(userInputRequest);
            }
        } catch (IOException e) {
            System.out.println("Some error occured, lets try once again.");
            return getValidUserInput(userInputRequest);
        }
    }

    private boolean validateInput(UserInputRequest userInputRequest, String userInput) {
        if (userInputRequest.getOptions().size() < 1 && userInput.length() > 0) {
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
