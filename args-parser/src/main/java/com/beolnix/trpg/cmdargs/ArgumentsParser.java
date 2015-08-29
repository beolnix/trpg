package com.beolnix.trpg.cmdargs;

import com.beolnix.trpg.cmdargs.error.UnknownFlag;
import com.beolnix.trpg.cmdargs.model.CommandLineArgument;
import com.beolnix.trpg.cmdargs.model.PassedArgument;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by beolnix on 29/08/15.
 */
public interface ArgumentsParser {

    /**
     * Transforms passed command line arguments to the list of PassedArgument objects.
     * Throws exception if unsupported argument found.
     * @param args array of command line arguments
     * @return list of PassedArgument objects
     * @throws UnknownFlag if unsupported (not passed to the constructor) argument found.
     */
    public Map<CommandLineArgument, PassedArgument> transform(String[] args) throws UnknownFlag;

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
