package hk.edu.polyu.comp.comp2021.simple.model.initialize;

import java.util.Vector;
/**
 * data interface
 * */
public interface data
{
    /**
     * set MAX POSITIVE NUMBER = 99999
     * */
    int MAX_INT_VAL = 99999;
    /**
     * set MAX NEGATIVE NUMBER = -99999
     *  */
    int MIN_INT_VAL = -99999;

    /**
     * @param programName : programName
     * @param isDebugging : isDebugging
     * @return execution result
     * */
    String exe(String programName, boolean isDebugging);
    /**
     * @return label
     * */
    String getLab();

    /**
     * store
     * @param v : v
     */
    void store(Vector<String> v);
}