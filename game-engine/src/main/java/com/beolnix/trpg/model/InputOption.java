package com.beolnix.trpg.model;

/**
 * Created by beolnix on 30/08/15.
 */
public class InputOption {
    private final String expectedInput;
    private final String description;

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
}
