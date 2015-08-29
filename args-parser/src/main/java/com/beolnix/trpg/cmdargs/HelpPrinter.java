package com.beolnix.trpg.cmdargs;

import com.beolnix.trpg.cmdargs.model.CommandLineArgument;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by beolnix on 29/08/15.
 */
public class HelpPrinter {

    private final static int cellPadding = 5;
    private final static int descriptionColumnSize = 30;

    private HelpPrinter() {
    }

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

    private static int getArgsOffset(Set<CommandLineArgument> commandLineArguments) {
        return getOffset(
                commandLineArguments.stream()
                        .map(flag ->
                                        Stream.of(flag.getFlags())
                                                .collect(Collectors.joining(","))
                        )
        );
    }

    private static int getExampleOffset(Set<CommandLineArgument> commandLineArguments) {
        return getOffset(
                commandLineArguments.stream()
                    .map(flag -> flag.getExample())
                );
    }

    private static int getOffset(Stream<String> stream) {
        return stream.collect(
                Collectors.maxBy(
                        Comparator.comparingInt(arg -> arg.length())
                )
            ).get().length() + cellPadding;
    }

}
