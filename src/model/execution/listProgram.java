package hk.edu.polyu.comp.comp2021.simple.model.execution;

import hk.edu.polyu.comp.comp2021.simple.model.initialize.initialize;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.data;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.programData;

/**
 * list program
 */
public class listProgram
{
    /**
     * compilation errors
     * @param s : s
     * @return true/false
     */
    public boolean error(String[] s)
    {
        boolean errFlag = false;
        if (s.length != 2)
        {
            System.out.println("Error: Incorrect command format. It should be\"list programName\".");
            return true;
        }
        data prog= initialize.Memory.get(s[1]);
        if(prog == null)
        {
            System.out.println("Error: There is no this program.");
            return true;
        }
        if(!(prog instanceof programData))
        {
            System.out.println("Error: Invalid programName.");
            errFlag = true;
        }

        return errFlag;
    }

    /**
     * execute list program
     * @param s : s
     */
    public void exe(String[] s)
    {
        data prog= initialize.Memory.get(s[1]);
        prog.store(((programData)prog).getCode());
        for(String codeline : ((programData)prog).getCode())
            System.out.println(codeline);
    }
}
