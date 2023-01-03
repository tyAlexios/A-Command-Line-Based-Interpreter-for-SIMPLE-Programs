package hk.edu.polyu.comp.comp2021.simple.model.initialize;

import java.util.Objects;
import java.util.Vector;

/**
 * data in binexpr command
 * execute command
 * store command
 * */
public class binexprData implements data
{
    private String expName;
    private String expRef1;
    private String bop;
    private String expRef2;
    /**
     * @param expName : expName
     * @param expRef1 : expRef1
     * @param bop : bop
     * @param expRef2 : expRef2*/
    binexprData(String expName, String expRef1, String bop, String expRef2)
    {
        this.expName = expName;
        this.expRef1 = expRef1;
        this.bop = bop;
        this.expRef2 = expRef2;
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

        String x1 = expRef1;
        String x2 = expRef2;
        if(!vardef.isNum(expRef1) && !Objects.equals("true",expRef1) && !Objects.equals("false",expRef1))
        {
            data x = initialize.Memory.get(expRef1);
            if(x instanceof vardefData && !prog.isVarDefined((vardefData)x))
                throw new NullPointerException();
            x1 = x.exe(programName,isDebugging);
        }

        if(!vardef.isNum(expRef2) && !Objects.equals("true",expRef2) && !Objects.equals("false",expRef2))
        {
            data x = initialize.Memory.get(expRef2);
            if(x instanceof vardefData && !prog.isVarDefined((vardefData)x))
                throw new NullPointerException();
            x2 = x.exe(programName,isDebugging);
        }


        boolean isInt1 = vardef.isNum(x1);
        boolean isInt2 = vardef.isNum(x2);
        boolean isBool1 = Objects.equals("true",x1) || Objects.equals("false",x1);
        boolean isBool2 = Objects.equals("true",x2) || Objects.equals("false",x2);
        if((isInt1^isInt2) || (isBool1^isBool2))
            throw new IllegalArgumentException();

        if((Objects.equals("&&",bop) || Objects.equals("||",bop)) && !isBool1)
            throw new IllegalArgumentException();

        prog.afterInstrument(getLab());

        switch (bop)
        {
            case "%":
                return String.valueOf((Integer.parseInt(x1) % Integer.parseInt(x2))% MAX_INT_VAL);

            case "+":
                return String.valueOf((Integer.parseInt(x1) + Integer.parseInt(x2))% MAX_INT_VAL);

            case "-":
                return String.valueOf((Integer.parseInt(x1) - Integer.parseInt(x2))% MAX_INT_VAL);

            case "*":
                return String.valueOf((Integer.parseInt(x1) * Integer.parseInt(x2))% MAX_INT_VAL);

            case "/":
                return String.valueOf((Integer.parseInt(x1) / Integer.parseInt(x2))% MAX_INT_VAL);

            case ">":
                return String.valueOf(Integer.parseInt(x1) > Integer.parseInt(x2));

            case ">=":
                return String.valueOf(Integer.parseInt(x1) >= Integer.parseInt(x2));

            case "<":
                return String.valueOf(Integer.parseInt(x1) < Integer.parseInt(x2));

            case "<=":
                return String.valueOf(Integer.parseInt(x1) <= Integer.parseInt(x2));

            case "==":
                return String.valueOf(Objects.equals(x1,x2));

            case "!=":
                return String.valueOf(!Objects.equals(x1,x2));

            case "&&":
                return String.valueOf( Boolean.parseBoolean(x1) && Boolean.parseBoolean(x2) );

            case "||":
                return String.valueOf( Boolean.parseBoolean(x1) || Boolean.parseBoolean(x2) );

            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void store(Vector<String> v)
    {
        String command = "binexpr "+expName+" "+expRef1+" "+bop+" "+expRef2;
        if(v.contains(command))
            return;
        if(!vardef.isNum(expRef1) && !Objects.equals("true",expRef1) && !Objects.equals("false",expRef1))
        {
            data x = initialize.Memory.get(expRef1);
            x.store(v);
        }

        if(!vardef.isNum(expRef2) && !Objects.equals("true",expRef2) && !Objects.equals("false",expRef2))
        {
            data x = initialize.Memory.get(expRef2);
            x.store(v);
        }
        v.add(command);
    }


}
