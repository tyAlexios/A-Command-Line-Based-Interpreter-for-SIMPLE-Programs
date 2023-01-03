package hk.edu.polyu.comp.comp2021.simple.model.initialize;

/**
 *  element
 *  */
public class ele
{
    private String varName, val;
    /**
     * @param varName : varName
     * @param val : val
     * */
    ele(String varName,String val)
    {
        this.varName = varName;
        this.val = val;
    }
    /**
     * get varName
     * @return varName
     * */
    public String getVarName()
    {
        return varName;
    }
    /**
     * get val
     * @return val
     * */
    public String getVal()
    {
        return val;
    }

    /**
     * set val
     * @param x : x
     */
    public void setVal(String x)
    {
        val = x;
    }
}
