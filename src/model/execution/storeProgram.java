package hk.edu.polyu.comp.comp2021.simple.model.execution;

import hk.edu.polyu.comp.comp2021.simple.model.initialize.initialize;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.data;
import hk.edu.polyu.comp.comp2021.simple.model.initialize.programData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * store program to a path
 */
public class storeProgram
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
            System.out.println("Error: Incorrect command format. It should be\"store programName path\".");
            return true;
        }
        String progName = s[1], path = s[2];
        if(path.length()<10 || !Objects.equals(".simple",path.substring(path.length()-7)))
        {
            System.out.println("Error: Invalid path.");
            System.out.println("A possible format of path: d:\\prog1.simple");
            errFlag = true;
        }
        data prog = initialize.Memory.get(progName);
        if(prog == null)
        {
            System.out.println("Error: Undefined programName.");
            return true;
        }
        if(!(prog instanceof programData))
        {
            System.out.println("Error: Invalid programName.");
            return true;
        }
        return errFlag;
    }

    /**
     * execute command
     * @param s : command
     */
    public void exe(String[] s)
    {
        String progName = s[1], path = s[2];
        try
        {
            File writeName = new File(path);
            if (writeName.exists())
            {
                System.out.println("The simple file already exists.\nThe simple file has been overwritten.");
                writeName.createNewFile();
            }
            FileWriter writer = new FileWriter(writeName);
            BufferedWriter out = new BufferedWriter(writer);
            data prog = initialize.Memory.get(progName);
            prog.store(((programData)prog).getCode());
            for (String value : ((programData)prog).getCode())
            {
                out.write(value+"\n");
                out.flush();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
