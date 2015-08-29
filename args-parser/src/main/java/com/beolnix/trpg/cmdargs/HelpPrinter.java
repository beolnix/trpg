package com.beolnix.trpg.cmdargs;

import com.beolnix.trpg.cmdargs.model.CommandLineArgument;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Help message printer is used to get nicely formatted help message.
 * Created by beolnix on 29/08/15.
 */
public class HelpPrinter {

    private final static int cellPadding = 5;
    private final static int descriptionColumnSize = 30;

    private HelpPrinter() {
    }

    /**
     * Method provides nicely formatted in a table manner help message for provided arguments
     * @param commandLineArguments arguments to be presented in the help message
     * @return nicely formatted help message
     */
    public static String printHelp(Set<CommandLineArgument> commandLineArguments) {

        int argsOffset = getArgsOffset(commandLineArguments);
        int exampleOffset = getExampleOffset(commandLineArguments);

        String mainFormat = "%-" + argsOffset + "s %-" + exampleOffset + "s %s\n";
        int descirptionOffset = argsOffset + exampleOffset + 1;

        StringBuilder builder = new StringBuilder();

        builder.append("The following command line arguments are supported:\n\n");
        builder.append(formatHelp(commandLineArguments, mainFormat, descirptionOffset));

        return builder.toString();
    }

    /**
     * Method generates help message as a table using provided formats
     * @param commandLineArguments arguments to be displayed in the table
     * @param format main format for the row in the table
     * @param descirptionOffset offset required to nicely format description field if it was split
     * @return
     */
    private static String formatHelp(Set<CommandLineArgument> commandLineArguments, String format, int descirptionOffset) {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format(format + "\n", "Flags", "Example", "Description"));

        commandLineArguments.stream()
                .forEach(flag -> {

                    String flags = Stream.of(flag.getFlags()).collect(Collectors.joining(","));
                    LinkedList<String> descriptionsSplited = splitByN(flag.getDescription(), descriptionColumnSize);

                    builder.append(String.format(format, flags, flag.getExample(), descriptionsSplited.pop()));
                    descriptionsSplited.stream().forEach(line ->
                                    builder.append(String.format("%" + (line.length() + descirptionOffset) + "s\n", line))
                    );
                    builder.append("\n");
                });

        return builder.toString();
    }

    /**
     * Method split provided lineToSplit by lines trying to make each not more then limit.
     * Lines are split by words so, if there will be long word, the resulting line will also big.
     * @param lineToSplit line to split
     * @param limit limit for the size of each part of the line
     * @return
     */
    private static LinkedList<String> splitByN(String lineToSplit, int limit) {
        // better use guava here but according to the limitations i'm not allowed ..

        LinkedList<String> lines = new LinkedList<>();
        StringBuilder currentString = new StringBuilder();
        boolean cutOneNextSpace = false;
        for (int i = 0; i < lineToSplit.length(); i++) {
            char currentChar = lineToSplit.charAt(i);
            if (cutOneNextSpace && Character.isSpaceChar(currentChar)) {
                lines.add(currentString.toString());
                currentString = new StringBuilder();
                cutOneNextSpace = false;
            }

            currentString.append(lineToSplit.charAt(i));
            if (currentString.length() > limit) {
                cutOneNextSpace = true;
            }

        }

        lines.add(currentString.toString());
        return lines;
    }

    /**
     * Method calculates offset required for the args field of the table in help output
     * @param commandLineArguments
     * @return
     */
    private static int getArgsOffset(Set<CommandLineArgument> commandLineArguments) {
        return getLargestSize(
                commandLineArguments.stream()
                        .map(flag ->
                                        Stream.of(flag.getFlags())
                                                .collect(Collectors.joining(","))
                        )
        );
    }

    /**
     * Method calculates offset required for the examples field of the table in help output
     * @param commandLineArguments
     * @return
     */
    private static int getExampleOffset(Set<CommandLineArgument> commandLineArguments) {
        return getLargestSize(
                commandLineArguments.stream()
                        .map(flag -> flag.getExample())
        );
    }

    /**
     * returns size of the largest string from the input stream
     * @param stream input stream
     * @return size of the largest string
     */
    private static int getLargestSize(Stream<String> stream) {
        return stream.collect(
                Collectors.maxBy(
                        Comparator.comparingInt(arg -> arg.length())
                )
            ).get().length() + cellPadding;
    }

}
