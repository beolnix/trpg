package com.beolnix.trpg.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Class provides functionality to load content from txt files into String object
 * Created by beolnix on 30/08/15.
 */
public class ContentHelper {

    private ContentHelper() {}

    /**
     * Method loads text from the file and returns it as a String object
     * @param localContentPath
     * @return
     */
    public static String getContent(String localContentPath) {
        InputStream is = ContentHelper.class.getResourceAsStream(localContentPath);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
        return buffer.lines().collect(Collectors.joining("\n"));
    }
}
