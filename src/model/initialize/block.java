package hk.edu.polyu.comp.comp2021.simple.model.initialize;
/**
 * compilation error
 * define block command
 * */
public class block implements initialize
{
    @Override
    public boolean error(String[] s)
    {
        if(s.length<3)
        {
            System.out.println("Error: Invalid format. The correct format should be \"block lab statementLab1, ..., statementLabn (n > 0)\".");
            return true;
        }
        String lab = s[1];
        boolean errFlag = initialize.nameError(lab,"label") | initialize.definedError(lab,"label");
        for(int i=2;i<s.length;++i)
        {
            String key = "statementLab" + (i-1);
            errFlag |= initialize.undefinedError(s[i],key);
        }
        return errFlag;
    }

    @Override
    public void define(String[] s)
    {
        String lab = s[1];
        blockData newBlock = new blockData(lab,s);
        Memory.put(lab,newBlock);
    }
}

// block block1 vardef1 assign1