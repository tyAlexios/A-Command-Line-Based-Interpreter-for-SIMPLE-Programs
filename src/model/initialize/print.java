package hk.edu.polyu.comp.comp2021.simple.model.initialize;

import java.util.Objects;
/**
 * compilation error
 * define print command
 * */
public class print implements initialize
{

    @Override
    public boolean error(String[] s)
    {
        if (s.length != 3) {
            System.out.println("Error: Incorrect command format. It should be\"print lab expRef\".");
            return true;
        }
        String lab = s[1], expRef = s[2];
        boolean errFlag = initialize.nameError(lab,"label") |
                initialize.definedError(lab,"label");
        if(!vardef.isNum(expRef) && !Objects.equals("true",expRef) && !Objects.equals("false",expRef))
            errFlag |= initialize.undefinedError(expRef,"expRef");
        return errFlag;
    }

    @Override
    public void define(String[] s)
    {
        String lab = s[1], expRef = s[2];
        printData newPrint = new printData(lab,expRef);
        Memory.put(lab,newPrint);
    }

}
