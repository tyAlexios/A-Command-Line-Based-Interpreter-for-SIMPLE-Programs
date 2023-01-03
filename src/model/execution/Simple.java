package hk.edu.polyu.comp.comp2021.simple.model.execution;

import hk.edu.polyu.comp.comp2021.simple.model.initialize.*;
import hk.edu.polyu.comp.comp2021.simple.view.menu;

/**
 *  A Command-Line-Based Interpreter for Simple Programs
 */
public class Simple
{
    /**
     * run command
     * @param line : command
     */
    public static void run(String line)
    {
        String[] s = line.split(" ");
        initialize newCommand;
        String command = s[0];
        switch (command)
        {
            case "vardef":
                newCommand = new vardef();
                if(!newCommand.error(s))
                    newCommand.define(s);
                break;

            case "binexpr":
                newCommand = new binexpr();
                if(!newCommand.error(s))
                    newCommand.define(s);
                break;

            case "unexpr":
                newCommand = new unexpr();
                if(!newCommand.error(s))
                    newCommand.define(s);
                break;

            case "print":
                newCommand = new print();
                if(!newCommand.error(s))
                    newCommand.define(s);
                break;

            case "assign":
                newCommand = new assign();
                if(!newCommand.error(s))
                    newCommand.define(s);
                break;

            case "skip":
                newCommand = new skip();
                if(!newCommand.error(s))
                    newCommand.define(s);
                break;

            case "block":
                newCommand = new block();
                if(!newCommand.error(s))
                    newCommand.define(s);
                break;

            case "if":
                newCommand = new condition();
                if(!newCommand.error(s))
                    newCommand.define(s);
                break;

            case "while":
                newCommand = new loop();
                if(!newCommand.error(s))
                    newCommand.define(s);
                break;

            case "program":
                newCommand = new program();
                if(!newCommand.error(s))
                    newCommand.define(s);
                break;

            case "execute":
                execute execution = new execute();
                if(!execution.error(s))
                    execution.exe(s);
                break;

            case "list":
                listProgram list = new listProgram();
                if(!list.error(s))
                    list.exe(s);
                break;

            case "store":
                storeProgram store = new storeProgram();
                if(!store.error(s))
                    store.exe(s);
                break;

            case "load":
                loadProgram load = new loadProgram();
                if(!load.error(s))
                    load.exe(s);
                break;

            case "togglebreakpoint":
                toggleBreakPoint point = new toggleBreakPoint();
                if(!point.error(s))
                    point.exe(s);
                break;

            case "debug":
                debugs debug = new debugs();
                if(!debug.error(s))
                    debug.exe(s);
                break;

            case "inspect":
                inspection inspect = new inspection();
                if(!inspect.error(s))
                    inspect.exe(s);
                break;

            case "instrument":
                instrument inst = new instrument();
                if(!inst.error(s))
                    inst.exe(s);
                break;

            case "help":
                menu.mainHelpPage();
                break;
            default:
                System.out.println("Error: This command does NOT exist.");
                break;
        }

    }
}

