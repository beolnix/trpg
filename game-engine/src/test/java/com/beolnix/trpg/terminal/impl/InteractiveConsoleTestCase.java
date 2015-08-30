package com.beolnix.trpg.terminal.impl;

import com.beolnix.trpg.terminal.SimpleTerminal;
import com.beolnix.trpg.terminal.impl.InteractiveConsole;
import com.beolnix.trpg.terminal.model.InputOption;
import com.beolnix.trpg.terminal.model.UserInputRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by beolnix on 30/08/15.
 */
public class InteractiveConsoleTestCase {

    @Test
    public void printTest() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));

        byte[] buf = "".getBytes();
        InputStreamReader is = new InputStreamReader(new ByteArrayInputStream(buf));
        BufferedReader reader = new BufferedReader(is);

        SimpleTerminal terminal = new InteractiveConsole(writer, reader);

        String expectedString = "expected some";
        terminal.print(expectedString);

        assertEquals(expectedString, os.toString());
    }

    @Test
    public void printlnTest() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));

        byte[] buf = "".getBytes();
        InputStreamReader is = new InputStreamReader(new ByteArrayInputStream(buf));
        BufferedReader reader = new BufferedReader(is);

        SimpleTerminal terminal = new InteractiveConsole(writer, reader);

        String expectedString = "expected some";
        terminal.println(expectedString);

        assertEquals(expectedString + "\n", os.toString());
    }

    @Test
    public void askUserInputTestLimitedWithOptions() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));

        String expectedInput = "2";

        byte[] buf = expectedInput.getBytes();
        InputStreamReader is = new InputStreamReader(new ByteArrayInputStream(buf));
        BufferedReader reader = new BufferedReader(is);

        SimpleTerminal terminal = new InteractiveConsole(writer, reader);

        InputOption inputOption = terminal.askUserInput(getImageUserInputRequest());
        assertEquals(expectedInput, inputOption.getActualInput());
    }

    @Test
    public void askUserInputNonLimitedTest() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));

        String expectedInput = "someIntput";

        byte[] buf = expectedInput.getBytes();
        InputStreamReader is = new InputStreamReader(new ByteArrayInputStream(buf));
        BufferedReader reader = new BufferedReader(is);

        SimpleTerminal terminal = new InteractiveConsole(writer, reader);

        InputOption inputOption = terminal.askUserInput(getNameUserInputRequest());
        assertEquals(expectedInput, inputOption.getActualInput());
    }

    @Test
    public void askUserInputIncorrectTest() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));

        String expectedInput = "2";
        String input = "7\n2\n";

        byte[] buf = input.getBytes();
        InputStreamReader is = new InputStreamReader(new ByteArrayInputStream(buf));
        BufferedReader bufreader = new BufferedReader(is);

        SimpleTerminal terminal = new InteractiveConsole(writer, bufreader);

        InputOption inputOption = terminal.askUserInput(getImageUserInputRequest());
        assertEquals(expectedInput, inputOption.getActualInput());
    }

    private UserInputRequest getImageUserInputRequest() {
        List<InputOption> inputOptionList = new ArrayList<>();
        inputOptionList.add(new InputOption("1", "Skip"));
        inputOptionList.add(new InputOption("2", "Accept"));

        return new UserInputRequest("Enter",
                inputOptionList
        );
    }

    private UserInputRequest getNameUserInputRequest() {
        return new UserInputRequest("Enter character name", Collections.emptyList());
    }
}
