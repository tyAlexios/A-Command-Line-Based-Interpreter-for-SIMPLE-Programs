package hk.edu.polyu.comp.comp2021.simple.model.execution;

import hk.edu.polyu.comp.comp2021.simple.model.initialize.initialize;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.data;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.programData;
import hk.edu.polyu.comp.comp2021.simple.model.myException.repeatVarDefine;
import hk.edu.polyu.comp.comp2021.simple.model.myException.stopAtBreakPoint;

/**
 * debug
 */
public class debugs
{
    /**
     * compilation errors
     * @param s : s
     * @return true/false
     * */
    public boolean error(String[] s)
    {
        boolean errFlag = false;
        if(s.length != 2)
        {
            System.out.println("Error: Incorrect command format. It should be\"debug programName\".");
            return true;
        }
        data x = initialize.Memory.get(s[1]);
        if(x == null)
        {
            System.out.println("Error: Undefined programName.");
            return true;
        }
        if(!(x instanceof programData))
        {
            System.out.println("Error: Invalid programName.");
            errFlag = true;
        }
        return errFlag;
    }

    /**
     * execute debug
     * @param s : s
     * */
    public void exe(String[] s)
    {
        data program = initialize.Memory.get(s[1]);
        try
        {
            ((programData)program).resetData(0);
            ((programData)program).addPoints();
            ((programData)program).resetCurPoint();
            program.exe(s[1],true);
            ((programData)program).resetPoints();
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
        catch (stopAtBreakPoint e)
        {
            System.out.print("\n");
        }
    }
}
