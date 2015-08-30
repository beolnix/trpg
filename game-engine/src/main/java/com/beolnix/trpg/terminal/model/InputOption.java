package com.beolnix.trpg.terminal.model;

/**
 * Created by beolnix on 30/08/15.
 */
public class InputOption {
    private final String expectedInput;
    private final String description;
    private String actualInput;

    public InputOption(String expectedInput, String description) {
        this.expectedInput = expectedInput;
        this.description = description;
    }

    public String getExpectedInput() {
        return expectedInput;
    }

    public String getDescription() {
        return description;
    }

    public String getActualInput() {
        return actualInput;
    }

    public void setActualInput(String actualInput) {
        this.actualInput = actualInput;
    }
}
