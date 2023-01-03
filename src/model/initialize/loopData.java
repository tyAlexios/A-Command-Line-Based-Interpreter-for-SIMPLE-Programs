package hk.edu.polyu.comp.comp2021.simple.model.initialize;

import java.util.Objects;
import java.util.Vector;

/**
 * data in while command
 * execute command
 * store command
 * */
public class loopData implements data
{
    private String lab;
    private String expRef;
    private String sLab1;
    /**
     * @param lab : lab
     * @param expRef : expRef
     * @param sLab1 : sLab1
     * */
    loopData(String lab, String expRef, String sLab1)
    {
        this.lab = lab;
        this.expRef = expRef;
        this.sLab1 = sLab1;
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
        data process;
        if(Objects.equals("true",expRef))
        {
            System.out.println("Warning: Infinite loop statement.");
            while(true)
            {
                process = initialize.Memory.get(sLab1);
                process.exe(programName,isDebugging);
            }
        }
        else if(Objects.equals("false",expRef))
        {
            System.out.println("Warning: Loop that never takes place.");
        }
        else
        {
            data flagProcess = initialize.Memory.get(expRef);
            String tmp = flagProcess.exe(programName,isDebugging);
            if(!Objects.equals("true",tmp) && !Objects.equals("false",tmp))
                throw new IllegalArgumentException();
            boolean flag = Boolean.parseBoolean(tmp);
            while(flag)
            {
                process = initialize.Memory.get(sLab1);
                process.exe(programName,isDebugging);
                flag = Boolean.parseBoolean(flagProcess.exe(programName,isDebugging));
            }
        }
        prog.resetData(size);
        prog.afterInstrument(getLab());

        return "";
    }

    @Override
    public void store(Vector<String> v)
    {
        String s = "while "+lab+" "+expRef+" "+sLab1;
        if(v.contains(s))
            return;
        data x;
        if(!Objects.equals("true",expRef) && !Objects.equals("false",expRef))
        {
            x = initialize.Memory.get(expRef);
            x.store(v);
        }
        x = initialize.Memory.get(sLab1);
        x.store(v);
        v.add(s);
    }
}
