package hk.edu.polyu.comp.comp2021.simple.model.initialize;

import java.util.Objects;

/**
 * compilation error
 * define variable command
 * */
public class vardef implements initialize
{
    private final int MAX_POSITIVE_NUMBER = 99999;
    private final int MAX_NEGATIVE_NUMBER = -99999;

    @Override
    public boolean error(String[] s)
    {
        if(s.length != 5)
        {
            System.out.println("Error: Incorrect command format. It should be\"vardef lab typ varName expRef\".");
            return true;
        }
        String lab = s[1], typ = s[2], varName = s[3], expRef = s[4];
        boolean errFlag = initialize.nameError(lab,"label") |
                initialize.nameError(varName,"variable name") |
                initialize.definedError(lab,"label") |
                initialize.definedError(varName,"varName") |
                initialize.undefinedError(expRef,"expRef");

        boolean isInt = isNum(expRef);
        boolean isBool = Objects.equals(expRef,"true") || Objects.equals(expRef,"false");

        if(Objects.equals(typ,"int")) // int range error
        {
            if(isBool)
            {
                System.out.println("Error: Type error.");
                return true;
            }
            if(isInt && (Long.parseLong(expRef)< MAX_NEGATIVE_NUMBER || Long.parseLong(expRef)>MAX_POSITIVE_NUMBER))
            {
                s[4] = String.valueOf(Long.parseLong(expRef)%MAX_POSITIVE_NUMBER);
                System.out.println("Warning: The int value is out of range [-99999,99999]. The value has been auto-rounded.");
            }
        }
        else if(Objects.equals(typ,"bool")) // bool value error
        {
            if(isInt)
            {
                System.out.println("Error: Type error.");
                return true;
            }
        }
        else
        {
            errFlag = true;
            System.out.println("Error: The data type must be int or bool.");
        } // invalid type error

        return errFlag;
    }

    /**
     * @param s : s
     * @return true/false
     */
    public static boolean isNum(String s)
    {
        int start = 0;
        if(s.charAt(0) == '-')
            start = 1;
        for(int i=start;i<s.length();++i)
        {
            if(!Character.isDigit(s.charAt(i)))
                return false;
        }
        return true;
    }

    @Override
    public void define(String[] s)
    {
        String lab = s[1], typ = s[2], varName = s[3], expRef = s[4];
        vardefData newVar = new vardefData(lab,typ,varName,expRef);
        Memory.put(lab,newVar);
        Memory.put(varName,newVar);
    }

}

// vardef vardef1 int x 100
// vardef vardef2 long x 100000000
// vardef vardef3 int y 100000000
// vardef vardef4 bool flag true
// vardef vardef5 bool flag2 lll
// vardef vardef int int 100000000
// vardef vardef int 22x 9999999999
// vardef 2ss int s#$5 1000
// No command