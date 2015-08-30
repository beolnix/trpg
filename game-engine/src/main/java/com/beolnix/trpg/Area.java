package com.beolnix.trpg;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by beolnix on 30/08/15.
 */
public class Area {

    private String asciiMap;
    private Set<Integer> exits;
    private String description;

    private Area() {}

    public static Area loadAreaForPosition(int position) {
        Area area = new Area();
        area.asciiMap = loadArea(position);
        area.exits = identifyExits(area.asciiMap);
        area.description = loadDescription(position);
        return area;
    }

    private static String loadArea(int position) {
        return ContentHelper.getContent("/content/area_" + position + ".txt");
    }

    private static String loadDescription(int position) {
        return ContentHelper.getContent("/content/area_" + position + "_description.txt");
    }

    private static Set<Integer> identifyExits(String asciiMap) {
        Pattern p = Pattern.compile("A\\d");
        Matcher m = p.matcher(asciiMap);
        Set<Integer> exits = new HashSet<>();
        while (m.find()) {
            String exitLiteral = m.group();
            if (exitLiteral != null) {
                String positionStr = exitLiteral.replace("A", "").trim();
                Integer exitPositionNumber = Integer.parseInt(positionStr);
                exits.add(exitPositionNumber);
            }

        }
        return exits;
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