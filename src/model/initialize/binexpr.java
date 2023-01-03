package hk.edu.polyu.comp.comp2021.simple.model.initialize;

import java.util.Objects;
/**
 * compilation error
 * define binexpr command
 * */
public class binexpr implements initialize
{
    private static final String[] BinExprSet = {"%", "+", "-", "*", "/", ">", ">=", "<", "<=", "==", "!=", "&&", "||"};

    @Override
    public boolean error(String[] s)
    {
        if(s.length != 5)
        {
            System.out.println("Error: Incorrect command format. It should be\"binexpr expName expRef1 bop expRef2\".");
            return true;
        }
        String expName = s[1], expRef1 = s[2], bop = s[3], expRef2 = s[4];
        boolean errFlag = initialize.nameError(expName,"expression name") |
                initialize.definedError(expName,"expName") |
                initialize.undefinedError(expRef1,"expRef1") |
                initialize.undefinedError(expRef2,"expRef2");

        boolean findOp = false;
        for(String op : BinExprSet)
        {
            if(Objects.equals(bop, op))
            {
                findOp = true;
                break;
            }
        }
        if(!findOp)
        {
            errFlag = true;
            System.out.println("Error: There is no this binary operators.");
        }
        return errFlag;
    }

    @Override
    public void define(String[] s)
    {
        String expName = s[1], expRef1 = s[2], bop = s[3], expRef2 = s[4];
        binexprData newBinExpr = new binexprData(expName, expRef1, bop, expRef2);
        Memory.put(expName, newBinExpr);
    }
}

//binexpr exp1 x * 20
//binexpr exp1 y * 3
//binexpr exp2 x * y
//binexpr exp3 x | y
//binexpr exp4 1 * 2
//binexpr exp5 1 && 2
//binexpr exp6 true && false
//binexpr exp7 true && 1
//binexpr exp8 x * 100000000