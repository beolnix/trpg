package com.beolnix.trpg;

import com.beolnix.trpg.terminal.SimpleTerminal;
import com.beolnix.trpg.terminal.impl.InteractiveConsole;
import com.beolnix.trpg.terminal.model.InputOption;
import com.beolnix.trpg.terminal.model.UserInputRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by beolnix on 30/08/15.
 */
public class InteractiveConsoleTestCase {

    @Test
    public void printTest() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        SimpleTerminal terminal = new InteractiveConsole(ps, System.in);

        String expectedString = "expected some";
        terminal.print(expectedString);

        assertEquals(expectedString + "\n", os.toString());
    }

    @Test
    public void askUserInputTest() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        String expectedInput = "2";

        byte[] buf = expectedInput.getBytes();
        ByteArrayInputStream is = new ByteArrayInputStream(buf);

        SimpleTerminal terminal = new InteractiveConsole(ps, is);

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
}
