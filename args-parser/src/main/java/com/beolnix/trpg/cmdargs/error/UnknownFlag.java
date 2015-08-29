package com.beolnix.trpg.cmdargs.error;

/**
 * Created by beolnix on 29/08/15.
 */
public class UnknownFlag extends Exception {
    private final String unknownFlag;

    public UnknownFlag(String unknownFlag) {
        super("Unknown command line argument: " + unknownFlag);
        this.unknownFlag = unknownFlag;
    }

    public String getUnknownFlag() {
        return unknownFlag;
    }
}
