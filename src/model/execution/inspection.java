package hk.edu.polyu.comp.comp2021.simple.model.execution;

import hk.edu.polyu.comp.comp2021.simple.model.initialize.initialize;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.data;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.programData;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.ele;
import java.util.Objects;

/**
 * inspection
 */
public class inspection
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
            System.out.println("Error: Incorrect command format. It should be\"inspect programName varName\".");
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

        boolean findVar = false;
        for(ele element : ((programData)x).getData() )
        {
            if(Objects.equals(element.getVarName(),s[2]))
            {
                findVar = true;
                break;
            }
        }
        if(!findVar)
        {
            System.out.println("Error: This variable is not in scope.");
            errFlag = true;
        }
        return errFlag;
    }

    /**
     * execute inspection
     * @param s : s
     */
    public void exe(String[] s)
    {
        data x = initialize.Memory.get(s[1]);
        for(ele element : ((programData)x).getData() )
        {
            if(Objects.equals(element.getVarName(),s[2]))
            {
                System.out.println("<"+element.getVal()+">");
                return;
            }
        }
    }

}
