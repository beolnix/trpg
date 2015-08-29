package com.beolnix.trpg;

import com.beolnix.trpg.model.InputOption;
import com.beolnix.trpg.model.UserInputRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by beolnix on 30/08/15.
 */
public class TerminalHelper {
    public static String askUserInput(UserInputRequest userInputRequest) {
        System.out.println(userInputRequest.getDescription() + ": \n");
        for (InputOption inputOption : userInputRequest.getOptions()) {
            System.out.println("     " + inputOption.getExpectedInput() + " - " + inputOption.getDescription());
        }
        System.out.print("Input: ");

        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String userInput = bufferRead.readLine();
            if (validateInput(userInputRequest, userInput)) {
                return userInput;
            } else {
                System.out.println("Incorrect input, please try once again.");
                return askUserInput(userInputRequest);
            }
        } catch (IOException e) {
            System.out.println("Some error occured, lets try once again.");
            askUserInput(userInputRequest);
        }

        return null;
    }

    private static boolean validateInput(UserInputRequest userInputRequest, String userInput) {
        if (userInputRequest.getOptions().length < 1) {
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
