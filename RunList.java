import java.util.ArrayList;
import java.util.Collections;
import dataHistographs.*;

public class RunList
{
    
    ArrayList <HistData> allRuns;
    MeanRun mean;
    
    public RunList()
    {
        mean = new MeanRun();
        allRuns = new ArrayList<HistData>();
    }
    public RunList(int initCapacity)
    {
        mean = new MeanRun();
        allRuns = new ArrayList<HistData>(initCapacity);
    }
    /**
     * Adds the given run to the this RunList.
     * 
     * @param run the CO2Run to be added
     * @return {<code>true} if this list changed as a result of the call
     */
    public boolean add(HistData run)
    {
        boolean rAdd = allRuns.add(run);
        if (rAdd==false)
            return rAdd;
        
        boolean mAdd = mean.include(run);
        if (mAdd==false)
        {
            allRuns.remove(run);
            return false;
        }
        return true;
    }
    
    public int size()
    {
        return allRuns.size();
    }
    public CommonData selectPercentileRun(double p)
    {
        CommonData c = new CommonData();
        DataComparator comp = new DataComparator();
        int timeLen = allRuns.get(0).size();
        for(int i = 0; i<timeLen; i++)
        {
            comp.setIndex(i);
            Collections.sort(allRuns, comp);
            c.nextPointFrom(allRuns.get( (int)(allRuns.size()*p)) );
        }
        return c;
    }
    public CommonData[] selectManyPercentileRuns(double[] p)
    {
        CommonData[] cRuns = new CommonData[p.length];
        for(int k=0; k<p.length; k++)
            cRuns[k]=new CommonData();
        DataComparator comp = new DataComparator();
        int timeLen = allRuns.get(0).size();
        for(int i = 0; i<timeLen; i++)
        {
            Collections.sort(allRuns, comp);
            System.out.println(allRuns);
            for(int j=0; j<p.length; j++)
            {
                cRuns[j].nextPointFrom(allRuns.get( (int)(allRuns.size()*p[j])) );
            }
        }
        return cRuns;
    }
    public HistData get(int i)
    {
        HistData r = allRuns.get(i);
        return r;
    }
    public MeanRun getMean()
    {
        return mean;
    }
}
