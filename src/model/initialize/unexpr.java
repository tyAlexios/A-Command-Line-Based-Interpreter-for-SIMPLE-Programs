package hk.edu.polyu.comp.comp2021.simple.model.initialize;

import java.util.Objects;
/**
 * compilation error
 * define unexpr command
 * */
public class unexpr implements initialize
{
    private static final String[] UnExprSet = {"#", "~", "!"};

    @Override
    public boolean error(String[] s)
    {
        if(s.length != 4)
        {
            System.out.println("Error: Incorrect command format. It should be\"unexpr expName uop expRef1\".");
            return true;
        }
        String expName = s[1], uop = s[2], expRef1 = s[3];
        boolean errFlag = initialize.nameError(expName,"expression name") |
                initialize.definedError(expName,"expName") |
                initialize.undefinedError(expRef1,"expRef1");
        boolean findOp = false;
        for(String op : UnExprSet)
        {
            if(Objects.equals(uop, op))
            {
                findOp = true;
                break;
            }
        }
        if(!findOp)
        {
            errFlag = true;
            System.out.println("Error: There is no this unary operator.");
        }
        return errFlag;
    }

    @Override
    public void define(String[] s)
    {
        String expName = s[1], uop = s[2], expRef1 = s[3];
        unexprData newUnExpr = new unexprData(expName, uop, expRef1);
        Memory.put(expName,newUnExpr);
    }
}

// unexpr exp2 ~ exp1
// unexpr exp3 # exp2
// unexpr 2xx + exy1

