package hk.edu.polyu.comp.comp2021.simple.model.initialize;

import java.util.Vector;

/**
 * data in skip command
 * execute command
 * store command
 * */
public class skipData implements data
{
    private String lab;
    /**
     * @param lab :lab
     * */
    skipData(String lab)
    {
        this.lab = lab;
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
        if(isDebugging)
            prog.tillBreakPoint(getLab());
        prog.beforeInstrument(getLab());
        prog.afterInstrument(getLab());
        return "";
    }

    @Override
    public void store(Vector<String> v)
    {
        String s= "skip "+lab;
        if(v.contains(s))
            return;
        v.add(s);
    }

}
