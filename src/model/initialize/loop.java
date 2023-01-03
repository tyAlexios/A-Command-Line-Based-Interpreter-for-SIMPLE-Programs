package hk.edu.polyu.comp.comp2021.simple.model.initialize;

import java.util.Objects;
/**
 * compilation error
 * define while command
 * */
public class loop implements initialize
{

    @Override
    public boolean error(String[] s) {
        if (s.length != 4) {
            System.out.println("Error: Incorrect command format. It should be\"while lab expRef statementLab1\".");
            return true;
        }
        String lab = s[1], expRef = s[2], statementLab1 = s[3];
        boolean errFlag = initialize.nameError(lab,"label") |
                initialize.definedError(lab,"label") |
                initialize.undefinedError(statementLab1,"statementLab1");
        if(!Objects.equals(expRef,"true") && !Objects.equals(expRef,"false"))
            errFlag |= initialize.nameError(expRef,"expRef") | initialize.undefinedError(expRef,"expRef");
        return errFlag;
    }

    @Override
    public void define(String[] s)
    {
        String lab = s[1], expRef = s[2], sLab1 = s[3];
        loopData newLoop = new loopData(lab,expRef,sLab1);
        Memory.put(lab, newLoop);
    }

}
