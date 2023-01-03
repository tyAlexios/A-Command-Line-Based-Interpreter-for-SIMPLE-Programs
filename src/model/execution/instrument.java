package hk.edu.polyu.comp.comp2021.simple.model.execution;

import hk.edu.polyu.comp.comp2021.simple.model.initialize.initialize;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.data;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.programData;

import java.util.Objects;

/**
 * instrument
 */
public class instrument
{
    /**
     * compilation errors
     * @param s : s
     * @return true/false
     */
    public boolean error(String[] s)
    {
        boolean errFlag = false;
        if(s.length != 5)
        {
            System.out.println("Error: Incorrect command format. It should be\"instrument programName statementLab pos expRef\".");
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

        if(!Objects.equals(s[3],"before") && !Objects.equals(s[3],"after"))
        {
            System.out.println("Error: The pos must be before or after.");
            errFlag = true;
        }
        return errFlag;
    }

    /**
     * execute instrument
     * @param s : s
     */
    public void exe(String[] s)
    {
        data prog = initialize.Memory.get(s[1]);
        String tmp = s[2]+" "+s[4];
        if(Objects.equals(s[3],"before"))
            ((programData)prog).getBeforeIns().add(tmp);
        else
            ((programData)prog).getAfterIns().add(tmp);
    }
}
