package com.beolnix.trpg.cmdargs.impl;

import com.beolnix.trpg.cmdargs.ArgumentsParser;
import com.beolnix.trpg.cmdargs.HelpPrinter;
import com.beolnix.trpg.cmdargs.error.UnknownFlag;
import com.beolnix.trpg.cmdargs.model.CommandLineArgument;

import java.util.*;

import static com.beolnix.trpg.cmdargs.DefaultArguments.helpCommandLineArgument;



/**
 * Default implementation of ArgumentsParser interface
 * Created by beolnix on 29/08/15.
 */
public class DefaultArgumentsParser implements ArgumentsParser {

    private final Set<CommandLineArgument> supportedCommandLineArguments = new HashSet<>();

    public DefaultArgumentsParser(CommandLineArgument... supportedCommandLineArguments) {
        this.supportedCommandLineArguments.addAll(Arrays.asList(supportedCommandLineArguments));
        this.supportedCommandLineArguments.add(helpCommandLineArgument);
    }

    @Override
    public Map<CommandLineArgument, String> parse(String[] args) throws UnknownFlag {
        if (args.length == 0) {
            return Collections.emptyMap();
        }

        LinkedList<String> argsList = new LinkedList<>(Arrays.asList(args));
        Map<CommandLineArgument, String> passedArgumentMap = new HashMap<>();
        while(!argsList.isEmpty()) {
            String flagStr = argsList.pop();
            CommandLineArgument commandLineArgument = parseArg(flagStr);
            if (!argsList.isEmpty()) {
                passedArgumentMap.put(commandLineArgument, argsList.pop());
            } else {
                passedArgumentMap.put(commandLineArgument, null);
            }

        }

        return passedArgumentMap;
    }

    @Override
    public Set<CommandLineArgument> getSupportedCommandLineArguments() {
        return supportedCommandLineArguments;
    }

    @Override
    public String getHelpMessage() {
        return HelpPrinter.printHelp(supportedCommandLineArguments);
    }

    /**
     * Method searches for the corresponding commandLineArgument which describes provided argument
     * @param arg provided argument
     * @return commandLineArgument instance in case of success
     * @throws UnknownFlag in case corresponding commandLineArgument not found
     */
    private CommandLineArgument parseArg(String arg) throws UnknownFlag {
        for (CommandLineArgument argument : supportedCommandLineArguments) {
            if (argument.consistFlag(arg)) {
                return argument;
            }
        }

        throw new UnknownFlag(arg);
    }
}
