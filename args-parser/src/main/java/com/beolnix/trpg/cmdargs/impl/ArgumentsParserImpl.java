package com.beolnix.trpg.cmdargs.impl;

import com.beolnix.trpg.cmdargs.ArgumentsParser;
import com.beolnix.trpg.cmdargs.HelpPrinter;
import com.beolnix.trpg.cmdargs.error.UnknownFlag;
import com.beolnix.trpg.cmdargs.model.CommandLineArgument;
import com.beolnix.trpg.cmdargs.model.PassedArgument;

import java.util.*;

import static com.beolnix.trpg.cmdargs.DefaultArguments.helpCommandLineArgument;



/**
 * Created by beolnix on 29/08/15.
 */
public class ArgumentsParserImpl implements ArgumentsParser {

    private final Set<CommandLineArgument> supportedCommandLineArguments = new HashSet<>();

    public ArgumentsParserImpl(CommandLineArgument... supportedCommandLineArguments) {
        this.supportedCommandLineArguments.addAll(Arrays.asList(supportedCommandLineArguments));
        this.supportedCommandLineArguments.add(helpCommandLineArgument);
    }

    public Map<CommandLineArgument, PassedArgument> transform(String[] args) throws UnknownFlag {
        if (args.length == 0) {
            return Collections.emptyMap();
        }

        LinkedList<String> argsList = new LinkedList<>(Arrays.asList(args));
        Map<CommandLineArgument, PassedArgument> passedArgumentMap = new HashMap<>();
        while(!argsList.isEmpty()) {
            String flagStr = argsList.pop();
            CommandLineArgument commandLineArgument = parseArg(flagStr);
            if (!argsList.isEmpty()) {
                passedArgumentMap.put(commandLineArgument, new PassedArgument(commandLineArgument, argsList.pop()));
            } else {
                passedArgumentMap.put(commandLineArgument, new PassedArgument(commandLineArgument, null));
            }

        }

        return passedArgumentMap;
    }

    public Set<CommandLineArgument> getSupportedCommandLineArguments() {
        return supportedCommandLineArguments;
    }

    public String getHelpMessage() {
        return HelpPrinter.printHelp(supportedCommandLineArguments);
    }

    private CommandLineArgument parseArg(String arg) throws UnknownFlag {
        for (CommandLineArgument argument : supportedCommandLineArguments) {
            if (argument.consistFlag(arg)) {
                return argument;
            }
        }

        throw new UnknownFlag(arg);
    }
}
