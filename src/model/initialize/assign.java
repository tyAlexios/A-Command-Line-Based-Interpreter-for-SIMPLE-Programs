package hk.edu.polyu.comp.comp2021.simple.model.initialize;

/**
 * compilation error
 * define assign command
 * */
public class assign implements initialize
{

    @Override
    public boolean error(String[] s)
    {
        if (s.length != 4)
        {
            System.out.println("Error: Incorrect command format. It should be\"assign lab varName expRef\".");
            return true;
        }
        String lab = s[1], varName = s[2], expRef = s[3];
        return initialize.nameError(lab, "label") |
                initialize.definedError(lab,"label") |
                initialize.undefinedError(varName,"varName") |
                initialize.undefinedError(expRef, "expRef");
    }

    @Override
    public void define(String[] s)
    {
        String lab = s[1], varName = s[2], expRef = s[3];
        assignData newAssign = new assignData(lab, varName, expRef);
        Memory.put(lab,newAssign);
    }

}

// assign assign1 x 2
// assign assign2 x exp1