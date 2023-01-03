package hk.edu.polyu.comp.comp2021.simple.model.initialize;

import java.util.HashMap;
import java.util.Objects;

/**
 * initialize the data and commands for the program
 */
public interface initialize
{
    /**
     * The keywords that can not be used as variable/expression names or statement labels
     */
    String[] unNameSet = {"null","int","bool","true","false","vardef", "binexpr", "unexpr", "assign", "print", "skip", "block", "if", "while", "program", "execute", "list", "store", "load", "quit", "debug", "togglebreakpoint", "inspect", "instrument"};
    /**
     * The Memory stored all the commands
     */
    HashMap<String, data> Memory = new HashMap<>();

    /**
     * compilation errors
     * @param s : command
     * @return true/false
     */
    boolean error(String[] s);

    /**
     * define command
     * @param s : command
     */
    void define(String[] s);

    /**
     * compilation errors
     * @param name : name
     * @param key : key
     * @return true/false
     */
    static boolean nameError(String name,String key) //Name format error
    {
        boolean nameFlag = false;
        if(name.length()>8)
        {
            nameFlag = true;
            System.out.printf("Error: The %s may contain at most 8 characters.\n",key);
        }
        for (String x : unNameSet)
        {
            if(Objects.equals(x, name))
            {
                nameFlag = true;
                System.out.printf("Error: The %s can NOT be \"int\", \"bool\", \"true\", \"false\" and all command names.\n",key);
                break;
            }
        }
        for(int i=0;i<name.length();++i)
        {
            char c = name.charAt(i);
            if(i == 0 && c-'0'>=0 && c-'9'<=0)
            {
                nameFlag = true;
                System.out.printf("Error: The %s can NOT start with digits.\n",key);
                continue;
            }
            if( !((c-'0'>=0 && c-'9'<=0) || (c-'a'>=0 && c-'z'<=0) || (c-'A'>=0 && c-'Z'<=0)) )
            {
                nameFlag = true;
                System.out.printf("Error: The %s can only contain English letters and digits.\n",key);
                break;
            }
        }

        return nameFlag;
    }

    /**
     * test if the label is defined
     * @param lab : label
     * @param key : the type of label
     * @return true/false
     */
    static boolean definedError(String lab, String key)
    {
        data x = Memory.get(lab);
        if(x != null)
        {
            System.out.printf("Error: The %s has been defined.\n",key);
            return true;
        }
        return false;
    }

    /**
     * test if the label is undefined
     * @param lab : label
     * @param key : the type of label
     * @return true/false
     */
    static boolean undefinedError(String lab, String key)
    {
        data x = Memory.get(lab);
        if(x == null && !vardef.isNum(lab) && !Objects.equals(lab,"true") && !Objects.equals(lab,"false"))
        {
            System.out.printf("Error: Undefined %s.\n",key);
            return true;
        }
        return false;
    }

}
