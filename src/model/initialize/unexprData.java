package hk.edu.polyu.comp.comp2021.simple.model.initialize;

import java.util.Objects;
import java.util.Vector;

/**
 * data in unexpr command
 * execute command
 * store command
 * */
public class unexprData implements data
{

    private String expName;
    private String uop;
    private String expRef1;
    /**
     * @param expName : expName
     * @param uop : uop
     * @param expRef1 : expRef1
     * */
    unexprData(String expName, String uop, String expRef1)
    {
        this.expName = expName;
        this.uop = uop;
        this.expRef1 = expRef1;
    }

    @Override
    public String getLab()
    {
        return expName;
    }

    @Override
    public String exe(String programName, boolean isDebugging)
    {
        programData prog = (programData) initialize.Memory.get(programName);
        if(isDebugging)
            prog.tillBreakPoint(getLab());
        prog.beforeInstrument(getLab());
        String x = expRef1;
        data process;
        boolean isInt = vardef.isNum(x);
        boolean isBool = Objects.equals("true",x) || Objects.equals("false",x);
        if(!isInt && !isBool)
        {
            process = initialize.Memory.get(expRef1);
            if(process instanceof vardefData && !prog.isVarDefined((vardefData) process))
                throw new NullPointerException();
            x = process.exe(programName,isDebugging);
            if(vardef.isNum(x))
                isInt = true;
            else if(Objects.equals("true",x) || Objects.equals("false",x))
                isBool = Boolean.parseBoolean(x);
            else
                throw new IllegalArgumentException();
        }

        if( (Objects.equals(uop,"#") || Objects.equals(uop,"~")) && !isInt)
            throw new IllegalArgumentException();
        if( Objects.equals(uop,"!") && !isBool)
            throw new IllegalArgumentException();

        prog.afterInstrument(getLab());

        switch (uop)
        {
            case "#":
                return String.valueOf( +(Integer.parseInt(x)% MAX_INT_VAL) );
            case "~":
                return String.valueOf( -(Integer.parseInt(x)% MAX_INT_VAL) );
            case "!":
                return String.valueOf( !(Boolean.parseBoolean(x)));
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void store(Vector<String> v)
    {
        String s = "unexpr "+expName+" "+uop+" "+expRef1;
        if(v.contains(s))
            return;
        data x;
        boolean isInt = vardef.isNum(expRef1);
        boolean isBool = Objects.equals("true",expRef1) || Objects.equals("false",expRef1);
        if(!isInt && !isBool)
        {
            x = initialize.Memory.get(expRef1);
            x.store(v);
        }
        v.add(s);
    }
}
