package hk.edu.polyu.comp.comp2021.simple.model.initialize;

import java.util.Objects;
import java.util.Vector;

/**
 * data in binexpr command
 * execute command
 * store command
 * */
public class vardefData implements data
{
    private String lab, typ, varName, expRef;
    private int index = -1;
    /**
     * @param lab : lab
     * @param typ : typ
     * @param varName : varName
     * @param expRef : expRef
     * */
    vardefData(String lab, String typ, String varName, String expRef)
    {
        this.lab = lab;
        this.typ = typ;
        this.varName = varName;
        this.expRef = expRef;
    }

    /**
     * @return varName
     */
    public String getVarName()
    {
        return varName;
    }

    /**
     * @return data type
     */
    public String getTyp()
    {
        return typ;
    }
    @Override
    public String getLab()
    {
        return lab;
    }

    /**
     * set index to x
     * @param x : x
     */
    public void setIndex(int x)
    {
        index = x;
    }

    /**
     * @return index
     */
    public int getIndex()
    {
        return index;
    }

    @Override
    public String exe(String programName, boolean isDebugging)
    {
        programData prog = (programData) initialize.Memory.get(programName);
        Vector<ele> v = prog.getData();
        if(isDebugging)
            prog.tillBreakPoint(getLab());
        prog.beforeInstrument(getLab());
        if(getIndex()==-1)
        {
            String result = expRef;
            boolean isInt = vardef.isNum(expRef);
            if(isInt)
            {
                long tmp = Long.parseLong(result);
                if(tmp> MAX_INT_VAL || tmp< MIN_INT_VAL)
                {
                    tmp%= MAX_INT_VAL;
                    result = String.valueOf(tmp);
                    System.out.println("Warning: OutOfRange value is auto-rounded.");
                }
            }
            boolean isBool = Objects.equals(expRef,"true") || Objects.equals(expRef,"false");
            if(!isInt && !isBool)
            {
                data x = initialize.Memory.get(expRef);
                result = x.exe(programName,isDebugging);
            }
            if(Objects.equals(typ,"int") && !vardef.isNum(result))
                throw new IllegalArgumentException();
            if(Objects.equals(typ,"bool") && !Objects.equals("true",result) && !Objects.equals("false",result))
                throw new IllegalArgumentException();
            ele newEle = new ele(getVarName(),result);
            v.addElement(newEle);
            setIndex(v.indexOf(newEle));
        }
        prog.afterInstrument(getLab());
        return v.get(getIndex()).getVal();
    }

    @Override
    public void store(Vector<String> v)
    {
        String s = "vardef "+lab+" "+typ+" "+varName+" "+expRef;
        if(v.contains(s))
            return;
        v.add(s);
    }
}
