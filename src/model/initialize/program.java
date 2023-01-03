package hk.edu.polyu.comp.comp2021.simple.model.initialize;
/**
 * compilation error
 * define program command
 * */
public class program implements initialize
{

    @Override
    public boolean error(String[] s)
    {
        if(s.length != 3)
        {
            System.out.println("Error: Incorrect command format. It should be\"program programName statementLab\".");
            return true;
        }
        String programName = s[1] , sLab = s[2];
        return initialize.nameError(programName,"programName") |
                initialize.definedError(programName,"programName") |
                initialize.undefinedError(sLab,"statementLab");
    }

    @Override
    public void define(String[] s)
    {
        String programName = s[1], statementLab = s[2];
        programData newProgram = new programData(programName,statementLab);
        Memory.put(programName,newProgram);
    }

}

// program program1 block1
