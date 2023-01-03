package hk.edu.polyu.comp.comp2021.simple.model.execution;

import hk.edu.polyu.comp.comp2021.simple.model.initialize.initialize;
import hk.edu.polyu.comp.comp2021.simple.model.myException.repeatVarDefine;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.data;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.programData;

/**
 * execute
 */
public class execute
{
    /**
     * compilation errors
     * @param s : s
     * @return true/false
     */
    public boolean error(String[] s)
    {
        boolean errFlag = false;
        if(s.length != 2)
        {
            System.out.println("Error: Incorrect command format. It should be\"execute programName\".");
            return true;
        }
        data program = initialize.Memory.get(s[1]);
        if(program == null)
        {
            System.out.println("Error: There is no this program.");
            return true;
        }
        if(!(program instanceof programData))
        {
            System.out.println("Error: Invalid programName.");
            errFlag = true;
        }

        return errFlag;
    }

    /**
     * execute execute
     * @param s : s
     */
    public void exe(String[] s)
    {
        data program = initialize.Memory.get(s[1]);
        try
        {
            ((programData)program).resetData(0);
            program.exe(s[1],false);
            ((programData)program).resetData(0);
            System.out.print("\n");
        }
        catch (NullPointerException e)
        {
            System.out.println("Error: Undefined expression or variable.");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Error: Type Error");
        }
        catch (repeatVarDefine e)
        {
            System.out.println("Error: Repeated definition of variables.");
        }
    }

}
