package hk.edu.polyu.comp.comp2021.simple.model.initialize;

import hk.edu.polyu.comp.comp2021.simple.model.myException.repeatVarDefine;

import java.util.Arrays;
import java.util.Objects;
import java.util.Vector;
import java.lang.*;

/**
 * data in block command
 * execute command
 * store command
 * */
public class blockData implements data
{
    private String lab;
    private Vector<String> commands = new Vector<>();
    /**
     * @param lab : lab
     * @param s : s
     * */
    blockData(String lab, String[] s)
    {
        this.lab = lab;
        commands.addAll(Arrays.asList(s).subList(2, s.length));
    }

    @Override
    public String getLab()
    {
        return lab;
    }

    @Override
    public String exe(String programName, boolean isDebugging)
    {
        programData prog = (programData) initialize.Memory.get(programName);
        Vector<ele> v = prog.getData();
        if(isDebugging)
            prog.tillBreakPoint(getLab());
        prog.beforeInstrument(getLab());

        int size = v.size();
        for (String command : commands)
        {
            data process = initialize.Memory.get(command);
            if (process instanceof vardefData)
            {
                for(ele x : v)
                {
                    if(Objects.equals(x.getVarName(),((vardefData)process).getVarName() ))
                        throw new repeatVarDefine();
                }
            }
            process.exe(programName,isDebugging);
        }
        prog.resetData(size);

        prog.afterInstrument(getLab());
        return "";
    }

    @Override
    public void store(Vector<String> v)
    {
        String s = "block "+lab;
        for(String c : commands)
            s = s + " " + c;
        if(v.contains(s))
            return;
        for(String command : commands)
        {
            data x = initialize.Memory.get(command);
            x.store(v);
        }
        v.add(s);
    }

}
