package com.beolnix.trpg.model;

/**
 * Created by beolnix on 30/08/15.
 */
public class UserInputRequest {
    private final String description;
    private final InputOption[] options;

    public UserInputRequest(String description, InputOption[] options) {
        this.description = description;
        this.options = options;
    }

    public String getDescription() {
        return description;
    }

    public InputOption[] getOptions() {
        return options;
    }
}
