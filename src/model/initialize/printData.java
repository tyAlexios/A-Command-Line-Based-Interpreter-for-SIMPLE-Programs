package hk.edu.polyu.comp.comp2021.simple.model.initialize;

import java.util.Objects;
import java.util.Vector;

/**
 * data in print command
 * execute command
 * store command
 * */
public class printData implements data
{
    private String lab;
    private String expRef;
    /**
     * @param lab : lab
     * @param expRef : expRef
     * */
    printData(String lab, String expRef)
    {
        this.lab = lab;
        this.expRef = expRef;
    }

    @Override
    public String getLab()
    {
        return lab;
    }

    /**
     * @return expRef
     */
    public String getExpRef() {return expRef;}

    @Override
    public String exe(String programName, boolean isDebugging)
    {
        programData prog = (programData) initialize.Memory.get(programName);
        if(isDebugging)
            prog.tillBreakPoint(getLab());
        prog.beforeInstrument(getLab());
        String expRef = getExpRef();
        String output = expRef;
        if(!vardef.isNum(expRef) && !Objects.equals("true",expRef) && !Objects.equals("false",expRef))
        {
            data x = initialize.Memory.get(expRef);
            if(x instanceof vardefData && !prog.isVarDefined((vardefData) x))
                throw new NullPointerException();
            output = x.exe(programName,isDebugging);
        }
        System.out.print("["+output+"] ");
        prog.afterInstrument(getLab());
        return "";
    }

    @Override
    public void store(Vector<String> v)
    {
        String s = "print "+lab+" "+expRef;
        if(v.contains(s))
            return;
        data x;
        if(!vardef.isNum(expRef) && !Objects.equals("true",expRef) && !Objects.equals("false",expRef))
        {
            x = initialize.Memory.get(expRef);
            x.store(v);
        }
        v.add(s);
    }
}
