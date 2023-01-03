package hk.edu.polyu.comp.comp2021.simple.model.initialize;

import java.util.Objects;
/**
 * compilation error
 * define if command
 * */
public class condition implements initialize
{

    @Override
    public boolean error(String[] s)
    {
        if (s.length != 5)
        {
            System.out.println("Error: Incorrect command format. It should be\"if lab expRef statementLab1 statementLab2\".");
            return true;
        }
        String lab = s[1], expRef = s[2], statementLab1 = s[3], statementLab2 = s[4];
        boolean errFlag =  initialize.nameError(lab,"label") |
                initialize.definedError(lab,"label") |
                initialize.undefinedError(statementLab1,"statementLab1") |
                initialize.undefinedError(statementLab2,"statementLab2");
        if(!Objects.equals(expRef,"true") && !Objects.equals(expRef,"false"))
            errFlag |= initialize.nameError(expRef,"expRef") | initialize.undefinedError(expRef,"expRef");
        return errFlag;
    }


    @Override
    public void define(String[] s)
    {
        String lab = s[1], expRef = s[2], sLab1 = s[3], sLab2 = s[4];
        conditionData newCondition = new conditionData(lab,expRef,sLab1,sLab2);
        Memory.put(lab,newCondition);
    }

}
