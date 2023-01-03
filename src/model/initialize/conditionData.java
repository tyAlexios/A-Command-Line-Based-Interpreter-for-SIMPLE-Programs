package hk.edu.polyu.comp.comp2021.simple.model.initialize;

import java.util.Objects;
import java.util.Vector;

/**
 * data in if command
 * execute command
 * store command
 * */
public class conditionData implements data
{
    private String lab;
    private String expRef;
    private String sLab1;
    private String sLab2;
    /**
     * @param lab : lab
     * @param expRef : expRef
     * @param sLab1 : sLab1
     * @param sLab2 : sLab2
     * */
    conditionData(String lab, String expRef, String sLab1, String sLab2)
    {
        this.lab = lab;
        this.expRef = expRef;
        this.sLab1 = sLab1;
        this.sLab2 = sLab2;
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

    /**
     * @return statementLab1
     */
    public String getsLab1() {return sLab1;}

    /**
     * @return statementLab2
     */
    public String getsLab2() {return sLab2;}

    @Override
    public String exe(String programName, boolean isDebugging)
    {
        programData prog = (programData) initialize.Memory.get(programName);
        if(isDebugging)
            prog.tillBreakPoint(getLab());
        prog.beforeInstrument(getLab());

        boolean flag;
        data process;
        if(Objects.equals("true",getExpRef()))
            flag = true;
        else if(Objects.equals("false",getExpRef()))
            flag = false;
        else
        {
            process = initialize.Memory.get(getExpRef());
            String tmp = process.exe(programName,isDebugging);
            if(!Objects.equals("true",tmp) && !Objects.equals("false",tmp))
                throw new IllegalArgumentException();
            flag = Boolean.parseBoolean(process.exe(programName,isDebugging));
        }

        if(flag)
        {
            process = initialize.Memory.get(getsLab1());
            process.exe(programName,isDebugging);
        }
        else
        {
            process = initialize.Memory.get(getsLab2());
            process.exe(programName,isDebugging);
        }

        prog.afterInstrument(getLab());

        return "";
    }

    @Override
    public void store(Vector<String> v)
    {
        String s = "if "+lab+" "+expRef+" "+sLab1+" "+sLab2;
        if(v.contains(s))
            return;
        data x;
        if(!Objects.equals("true",getExpRef()) && !Objects.equals("false",getExpRef()))
        {
            x = initialize.Memory.get(getExpRef());
            x.store(v);
        }
        x = initialize.Memory.get(getsLab1());
        x.store(v);
        x = initialize.Memory.get(getsLab2());
        x.store(v);
        v.add(s);
    }

}
