package com.beolnix.trpg.cmdargs;

import com.beolnix.trpg.cmdargs.error.UnknownFlag;
import com.beolnix.trpg.cmdargs.model.CommandLineArgument;

import java.util.Map;
import java.util.Set;

/**
 * Arguments Parser interface.
 * Parser is used to parse provided command lines parameters into the Passed Arguments objects
 * It also provides functionality to nicely print supported command line arguments.
 * Created by beolnix on 29/08/15.
 */
public interface ArgumentsParser {

    /**
     * Parses passed command line arguments. Returns a map with provided command line arguments.
     * Throws exception if unsupported argument found.
     *
     * @param args array of command line arguments
     * @return Map of parsed command like arguments
     * @throws UnknownFlag if unsupported argument found.
     */
    public Map<CommandLineArgument, String> parse(String[] args) throws UnknownFlag;

    /**
     * Return supported command line arguments
     * @return supported command line arguments
     */
    public Set<CommandLineArgument> getSupportedCommandLineArguments();

    /**
     * Generates help message based on supported arguments.
     * @return help message based on supported arguments.
     */
    public String getHelpMessage();

}
