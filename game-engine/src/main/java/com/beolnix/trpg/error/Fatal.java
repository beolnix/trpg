package com.beolnix.trpg.error;

/**
 * Created by beolnix on 30/08/15.
 */
public class Fatal extends Exception {

    public Fatal(String message) {
        super(message);
    }

    public Fatal(String message, Throwable cause) {
        super(message, cause);
    }
}
