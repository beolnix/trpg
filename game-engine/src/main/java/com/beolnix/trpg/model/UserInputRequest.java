package com.beolnix.trpg.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beolnix on 30/08/15.
 */
public class UserInputRequest {
    private final String description;
    private final List<InputOption> options;

    public UserInputRequest(String description, List<InputOption> options) {
        this.description = description;
        this.options = options;
    }

    public String getDescription() {
        return description;
    }

    public List<InputOption> getOptions() {
        return options;
    }
}
