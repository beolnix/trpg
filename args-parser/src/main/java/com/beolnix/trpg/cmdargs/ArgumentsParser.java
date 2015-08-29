package com.beolnix.trpg.cmdargs;

import com.beolnix.trpg.cmdargs.error.UnknownFlag;
import com.beolnix.trpg.cmdargs.model.CommandLineArgument;
import com.beolnix.trpg.cmdargs.model.PassedArgument;

import java.util.*;



/**
 * Created by beolnix on 29/08/15.
 */
public class ArgumentsParser {

    public final static CommandLineArgument helpCommandLineArgument =
            new CommandLineArgument("Prints this help.", "-h", new String[]{"-h", "--help"});

    private final List<CommandLineArgument> supportedCommandLineArguments = new LinkedList<>();

    public ArgumentsParser(CommandLineArgument... supportedCommandLineArguments) {
        this.supportedCommandLineArguments.addAll(Arrays.asList(supportedCommandLineArguments));
        this.supportedCommandLineArguments.add(helpCommandLineArgument);
    }

    private CommandLineArgument parseArg(String arg) throws UnknownFlag {
        for (CommandLineArgument argument : supportedCommandLineArguments) {
            if (argument.consistFlag(arg)) {
                return argument;
            }
        }

        throw new UnknownFlag(arg);
    }


    public List<PassedArgument> transform(String[] args) throws UnknownFlag {
        if (args.length == 0) {
            return Collections.emptyList();
        }

        LinkedList<String> argsList = new LinkedList<>(Arrays.asList(args));
        List<PassedArgument> passedArgumentList = new ArrayList<>();
        while(!argsList.isEmpty()) {
            String flagStr = argsList.pop();
            CommandLineArgument commandLineArgument = parseArg(flagStr);
            if (!argsList.isEmpty()) {
                passedArgumentList.add(new PassedArgument(commandLineArgument, argsList.pop()));
            } else {
                passedArgumentList.add(new PassedArgument(commandLineArgument, null));
            }

        }

        return passedArgumentList;
    }

    public String getHelpMessage() {
        return HelpPrinter.printHelp(supportedCommandLineArguments);
    }


}
