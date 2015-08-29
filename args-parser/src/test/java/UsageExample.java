import com.beolnix.trpg.cmdargs.ArgumentsHelper;
import com.beolnix.trpg.cmdargs.ArgumentsParser;
import com.beolnix.trpg.cmdargs.error.UnknownFlag;
import com.beolnix.trpg.cmdargs.impl.DefaultArgumentsParser;
import com.beolnix.trpg.cmdargs.model.CommandLineArgument;
import com.beolnix.trpg.cmdargs.model.PassedArgument;

import java.util.Map;

/**
 * Created by beolnix on 29/08/15.
 */
public class UsageExample {

    static CommandLineArgument someArg = new CommandLineArgument(
            //description
            "Description of the argument. " +
                    "The description will be automatically splitted if it longer then 30 chars.",

            //example
            "-s ./path/to/file",

            //flags
            new String[]{"-s", "--source"}
    );

    public static void main(String[] args) {
        ArgumentsParser argsParser = new DefaultArgumentsParser(getSupportedArgs());

        try {

            Map<CommandLineArgument, PassedArgument> passedArgs = argsParser.transform(args);
            if (ArgumentsHelper.consistOf(passedArgs.values(), someArg)) {
                //process some arg passed to the program here
                System.out.println("you passed " + someArg.getFlags()[0] + " " +
                        "with " + passedArgs.get(someArg).getValue() + " value");
            }

            if (ArgumentsHelper.consistOfHelp(passedArgs.values())) {
                //print help message here
                System.out.println(argsParser.getHelpMessage());
            }


        } catch (UnknownFlag e) {
            // process here situation of unknown argument. pring help for example.
            System.out.println(e.getUnknownFlag() + " - argument is not supported. Use --help to see supported arguments.");
        }


    }

    public static CommandLineArgument[] getSupportedArgs() {
        return new CommandLineArgument[] {someArg};
    }
}
