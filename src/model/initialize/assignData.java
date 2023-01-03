package hk.edu.polyu.comp.comp2021.simple.model.initialize;

import java.util.Objects;
import java.util.Vector;

/**
 * data in assign command
 * execute command
 * store command
 * */
public class assignData implements data
{

    private String lab;
    private String varName;
    private String expRef;
    /**
     * assignDate
     * @param lab : lab
     * @param varName : varName
     * @param expRef : expRef*/
    assignData(String lab, String varName, String expRef)
    {
        this.lab = lab;
        this.varName = varName;
        this.expRef = expRef;
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

        prog.beforeInstrument(lab);

        vardefData var = (vardefData) initialize.Memory.get(varName);

        if(var.getIndex() == -1)
            throw new NullPointerException();

        if(!Objects.equals(v.get(var.getIndex()).getVarName(),varName))
            throw new NullPointerException();


        boolean isInt = vardef.isNum(expRef);
        boolean isBool = Objects.equals("true", expRef) || Objects.equals("false", expRef);
        if( (isInt && !Objects.equals(var.getTyp(),"int")) || (isBool && !Objects.equals(var.getTyp(),"bool")) )
            throw new IllegalArgumentException();

        if(isInt)
        {
            long tmp = Long.parseLong(expRef);
            if(tmp> MAX_INT_VAL || tmp< MIN_INT_VAL)
            {
                tmp%= MAX_INT_VAL;
                expRef = String.valueOf(tmp);
                System.out.println("Warning: OutOfRange value is auto-rounded.");
            }
            v.get(var.getIndex()).setVal(expRef);
        }
        else if(isBool)
        {
            v.get(var.getIndex()).setVal(expRef);
        }
        else
        {
            data ref = initialize.Memory.get(expRef);
            String val = ref.exe(programName,isDebugging);
            if( Objects.equals(var.getTyp(),"int") && (Objects.equals(val,"true") || Objects.equals(val,"false")) )
                throw new IllegalArgumentException();
            if( Objects.equals(var.getTyp(),"bool") && vardef.isNum(val))
                throw new IllegalArgumentException();
            v.get(var.getIndex()).setVal(val);
        }
        prog.afterInstrument(lab);
        return "";
    }

    @Override
    public void store(Vector<String> v)
    {
        String s = "assign "+lab+" "+varName+" "+expRef;
        if(v.contains(s))
            return;
        boolean isInt = vardef.isNum(expRef);
        boolean isBool = Objects.equals("true", expRef) || Objects.equals("false", expRef);
        if(!isInt && !isBool)
        {
            data x = initialize.Memory.get(expRef);
            x.store(v);
        }
        v.add(s);
    }


}
