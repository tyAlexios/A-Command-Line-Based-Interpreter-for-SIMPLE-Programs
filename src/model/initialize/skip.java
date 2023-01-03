package hk.edu.polyu.comp.comp2021.simple.model.initialize;
/**
 * compilation error
 * define skip command
 * */
public class skip implements initialize
{

    @Override
    public boolean error(String[] s)
    {
        if (s.length != 2) {
            System.out.println("Error: Incorrect command format. It should be\"skip lab\".");
            return true;
        }
        String lab = s[1];
        return initialize.nameError(lab,"label") | initialize.definedError(lab,"label");
    }
    @Override
    public void define(String[] s)
    {
        String lab = s[1];
        skipData newSkip = new skipData(lab);
        Memory.put(lab,newSkip);
    }

}
