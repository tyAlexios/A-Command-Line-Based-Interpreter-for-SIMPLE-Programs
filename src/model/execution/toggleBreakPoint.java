package hk.edu.polyu.comp.comp2021.simple.model.execution;

import hk.edu.polyu.comp.comp2021.simple.model.initialize.*;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.data;

import java.util.Objects;
import java.util.Vector;

/**
 * set/remove break points
 */
public class toggleBreakPoint
{
    /**
     * compilation errors
     * @param s : s
     * @return true/false
     */
    public boolean error(String[] s)
    {
        boolean errFlag = false;
        if(s.length != 3)
        {
            System.out.println("Error: Incorrect command format. It should be\"togglebreakpoint programName statementLab\".");
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
            return true;
        }
        x.store(((programData)x).getCode());
        boolean findStatementLab = false;
        for(String oneline : ((programData)x).getCode() )
        {
            String[] parts = oneline.split(" ");
            if(Objects.equals(s[2],parts[1]))
            {
                findStatementLab = true;
                break;
            }
        }
        if(!findStatementLab)
        {
            System.out.println("Error: The statementLab does not in this program.");
            errFlag = true;
        }
        return errFlag;
    }

    /**
     * execute command
     * @param s : command
     */
    public void exe(String[] s)
    {
        data x = initialize.Memory.get(s[1]);
        Vector<String> breakPoints = ((programData)x).getBreakPoints();
        if(breakPoints.contains(s[2]))
            breakPoints.remove(s[2]);
        else
            breakPoints.add(s[2]);
    }
}
