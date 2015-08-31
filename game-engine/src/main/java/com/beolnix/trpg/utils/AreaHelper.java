package com.beolnix.trpg.utils;

import com.beolnix.trpg.model.Area;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class provides method to load Area for position from the content files.
 *
 * Created by beolnix on 01/09/15.
 */
public class AreaHelper {

    private AreaHelper() {}

    /**
     * Loads Area for specific position from the content files
     * @param position
     * @return
     */
    public static Area loadAreaForPosition(int position) {
        String asciiMap = loadArea(position);
        Set<Integer> exits = identifyExits(asciiMap);
        String description = loadDescription(position);
        return new Area(asciiMap, exits, description);
    }

    private static String loadArea(int position) {
        return ContentHelper.getContent("/content/area_" + position + ".txt");
    }

    private static String loadDescription(int position) {
        return ContentHelper.getContent("/content/area_" + position + "_description.txt");
    }

    /**
     * Method identifies exits in Area using regexp
     * @param asciiMap
     * @return
     */
    private static Set<Integer> identifyExits(String asciiMap) {
        Pattern p = Pattern.compile("EXIT:\\d{1,2}");
        Matcher m = p.matcher(asciiMap);
        Set<Integer> exits = new HashSet<>();
        while (m.find()) {
            String exitLiteral = m.group();
            if (exitLiteral != null) {
                String positionStr = exitLiteral.replace("EXIT:", "").trim();
                Integer exitPositionNumber = Integer.parseInt(positionStr);
                exits.add(exitPositionNumber);
            }

        }
        return exits;
    }
}
