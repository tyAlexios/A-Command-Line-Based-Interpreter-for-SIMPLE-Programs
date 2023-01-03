package hk.edu.polyu.comp.comp2021.simple.model.initialize;

import hk.edu.polyu.comp.comp2021.simple.model.myException.stopAtBreakPoint;

import java.util.Objects;
import java.util.Vector;

/**
 * data in program command
 * execute command
 * store command
 * */
public class programData implements data
{
    private int totalPoints = 0; // The number of break points this program should meet
    private int curPoint = 0;
    private boolean storeFlag = false;
    private String programName, statementLab;
    private Vector<ele> progVar = new Vector<>();
    private Vector<String> code = new Vector<>();
    private Vector<String> breakPoints = new Vector<>();
    private Vector<String> beforeIns = new Vector<>();
    private Vector<String> afterIns = new Vector<>();
    /**
     * @param programName : programName
     * @param statementLab : statementLab
     * */
    public programData(String programName, String statementLab)
    {
        this.programName = programName;
        this.statementLab = statementLab;
    }

    @Override
    public String exe(String programName, boolean isDebugging)
    {
        data x = initialize.Memory.get(getStatementLab());
        x.exe(programName,isDebugging);
        return "";
    }

    @Override
    public void store(Vector<String> v)
    {
        if(storeFlag)
            return;
        storeFlag = true;
        String s = "program "+programName+" "+statementLab;
        data x = initialize.Memory.get(getStatementLab());
        x.store(v);
        v.add(s);
    }

    /**
     * Set the storeFlag to true
     */
    public void setStoreFlagTrue() {storeFlag = true;}
    /**
     * @return variable vector
     */
    public Vector<ele> getData() {return progVar;}

    /**
     * @return the codes in this program
     */
    public Vector<String> getCode() {return code;}

    /**
     * @return break points
     */
    public Vector<String> getBreakPoints() {return breakPoints;}

    @Override
    public String getLab()
    {
        return programName;
    }

    /**
     * @return statementLab
     */
    public String getStatementLab()
    {
        return statementLab;
    }

    /**
     * Add one on the number of break points this program should meet
     */
    public void addPoints(){totalPoints++;}

    /**
     * Add one on the number of current meeting break points
     */
    public void addCurPoint(){curPoint++;}

    /**
     * reset the number of current meeting break points to 0
     */
    public void resetCurPoint(){curPoint = 0;}

    /**
     * reset the number of total break points this program should meet
     */
    public void resetPoints(){totalPoints = 0;}

    /**
     * reset the number of variables in the vector to size
     * @param size : size
     */
    public void resetData(int size)
    {
        Vector<ele> v = getData();
        while(v.size()>size)
        {
            ele lastEle = v.get(v.size()-1);
            String varName = lastEle.getVarName();
            data x = initialize.Memory.get(varName);
            ((vardefData)x).setIndex(-1);
            v.remove(v.size()-1);
        }
    }

    /**
     * @return beforeInstrument labels
     */
    public Vector<String> getBeforeIns()
    {
        return beforeIns;
    }
    /**
     * @return afterInstrument labels
     */
    public Vector<String> getAfterIns()
    {
        return afterIns;
    }

    /**
     * judge whether is variable define
     * @param x : x
     * @return true/false
     */
    public boolean isVarDefined(vardefData x)
    {
        for(ele element : progVar)
        {
            if(Objects.equals(element.getVarName(),x.getVarName()))
                return true;
        }
        return false;
    }

    /**
     * test if the label is a break point
     * @param lab : label
     */
    void tillBreakPoint(String lab)
    {
        Vector<String> points = getBreakPoints();
        for(String point : points)
        {
            if(Objects.equals(point,lab))
            {
                addCurPoint();
                if(curPoint == totalPoints)
                    throw new stopAtBreakPoint();
                else
                    return;
            }
        }
    }

    /**
     * test if there are before-instruments for label
     * @param lab : label
     */
    void beforeInstrument(String lab)
    {
        if(!beforeIns.isEmpty())
        {
            for(String ins : beforeIns)
            {
                String[] tmp = ins.split(" ");
                if(Objects.equals(tmp[0],lab))
                {
                    System.out.print("{"+tmp[1]+"} ");
                }
            }
        }
    }
    /**
     * test if there are after-instruments for label
     * @param lab : label
     */
    void afterInstrument(String lab)
    {
        if(!afterIns.isEmpty())
        {
            for(String ins : afterIns)
            {
                String[] tmp = ins.split(" ");
                if(Objects.equals(tmp[0],lab))
                {
                    System.out.print("{"+tmp[1]+"} ");
                }
            }
        }
    }
}

