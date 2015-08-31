package com.beolnix.trpg.model;

import com.beolnix.trpg.utils.ContentHelper;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Area is the mini map used by ExploreScreen to display where the player is and what variants he has.
 * Created by beolnix on 30/08/15.
 */
public class Area {

    private String asciiMap;
    private Set<Integer> exits;
    private String description;

    public Area(String asciiMap, Set<Integer> exits, String description) {
        this.asciiMap = asciiMap;
        this.exits = exits;
        this.description = description;
    }

    public String getAsciiMap() {
        return asciiMap;
    }

    public Set<Integer> getExits() {
        return exits;
    }

    public String getDescription() {
        return description;
    }
}
