package hk.edu.polyu.comp.comp2021.simple.model.execution;

import hk.edu.polyu.comp.comp2021.simple.model.initialize.initialize;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.data;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.programData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 * load program from a path
 */
public class loadProgram
{
    /**
     * compilation errors
     * @param s : s
     * @return true/false
     */
    public boolean error(String[] s)
    {
        boolean errFlag = false;
        if (s.length != 3)
        {
            System.out.println("Error: Incorrect command format. It should be\"load path program1\".");
            return true;
        }
        String loadPath = s[1], loadProg = s[2];
        if(loadPath.length()<10)
        {
            System.out.println("Error: Invalid path.");
            System.out.println("A possible format of path: d:\\prog1.simple");
            errFlag = true;
        }
        data prog = initialize.Memory.get(loadProg);
        if(prog != null)
        {
            System.out.println("Error: The programName has been defined.");
            errFlag = true;
        }
        return errFlag;
    }

    /**
     * execute load program
     * @param s : s
     */
    public void exe(String[] s)
    {
        String loadPath = s[1], loadProg = s[2];
        try
        {
            File loadName = new File(loadPath);
            if (!loadName.exists())
            {
                System.out.println("The simple file does NOT exist.");
                return;
            }
            BufferedReader reader = new BufferedReader(new FileReader(loadName));
            String oneLine;
            programData newProgram = new programData(loadProg,"load");
            newProgram.setStoreFlagTrue();
            boolean findProgram = false;
            while( (oneLine = reader.readLine()) != null )
            {
                if(oneLine.charAt(oneLine.length()-1) == '\n')
                    oneLine = oneLine.substring(0,oneLine.length()-1);
                String[] command = oneLine.split(" ");
                if(Objects.equals(command[0],"program"))
                    findProgram = true;
            }
            if(!findProgram)
            {
                System.out.println("Error: There is no program command in "+loadPath+" file.");
                return;
            }
            while( (oneLine = reader.readLine()) != null )
            {
                if(oneLine.charAt(oneLine.length()-1) == '\n')
                    oneLine = oneLine.substring(0,oneLine.length()-1);
                String[] command = oneLine.split(" ");
                newProgram.getCode().add(oneLine);
                Simple.run(oneLine);
                if(Objects.equals(command[0],"program"))
                    Simple.run("program "+loadProg+" "+command[2]);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
