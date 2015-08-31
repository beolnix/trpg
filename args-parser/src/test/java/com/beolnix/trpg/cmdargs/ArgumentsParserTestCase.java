package com.beolnix.trpg.cmdargs;

import com.beolnix.trpg.cmdargs.error.UnknownFlag;
import com.beolnix.trpg.cmdargs.impl.DefaultArgumentsParser;
import com.beolnix.trpg.cmdargs.model.CommandLineArgument;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

/**
 * Created by beolnix on 29/08/15.
 */
public class ArgumentsParserTestCase {

    private CommandLineArgument someArg = new CommandLineArgument(
            //description
            "Description of the argument. " +
                    "The description will be automatically splitted if it longer then 30 chars.",

            //example
            "-s ./path/to/file",

            //flags
            new String[]{"-s", "--source"}
    );

    private String expectedCaptionHelpMessage = "" +
            "The following command line arguments are supported:";


    private String expectedSourceHelpMessage = "" +
            "-s,--source      -s ./path/to/file      Description of the argument. The\n" +
            "                                        description will be automatically\n" +
            "                                        splitted if it longer then 30 chars.\n";


    private String expectedHelpMessage = "" +
            "-h,--help        -h                     Prints this help.\n";


    @Test
    public void testTransform() throws UnknownFlag {
        ArgumentsParser argsParser = new DefaultArgumentsParser(getSupportedArgs());
        String[] args = new String[]{"-s", "path"};
        Map<CommandLineArgument, String> passedArgs = argsParser.parse(args);

        assertNotNull(passedArgs);
        assertTrue(passedArgs.containsKey(someArg));
        assertNotNull(passedArgs.get(someArg));
        assertEquals(args[1], passedArgs.get(someArg));
    }

    @Test
    public void testConsistOfHelp() throws UnknownFlag {
        ArgumentsParser argsParser = new DefaultArgumentsParser(getSupportedArgs());
        String[] args = new String[]{"-h"};
        Map<CommandLineArgument, String> passedArgs = argsParser.parse(args);

        assertTrue(ArgumentsHelper.consistOfHelp(passedArgs));
    }

    @Test
    public void testHelp() throws UnknownFlag {
        ArgumentsParser argsParser = new DefaultArgumentsParser(getSupportedArgs());

        String helpMsg = argsParser.getHelpMessage();

        assertTrue(helpMsg.contains(expectedCaptionHelpMessage));
        assertTrue(helpMsg.contains(expectedSourceHelpMessage));
        assertTrue(helpMsg.contains(expectedHelpMessage));
    }

    @Test(expected = UnknownFlag.class)
    public void testUnkownArgumentException() throws UnknownFlag {
        ArgumentsParser argsParser = new DefaultArgumentsParser(getSupportedArgs());
        String[] args = new String[]{"-vava"};
        Map<CommandLineArgument, String> passedArgs = argsParser.parse(args);
    }

    public CommandLineArgument[] getSupportedArgs() {
        return new CommandLineArgument[] {someArg};
    }

}
