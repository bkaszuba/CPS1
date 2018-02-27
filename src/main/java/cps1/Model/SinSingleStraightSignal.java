package cps1.Model;

/**
 * Class for Sinus single straight signal
 */
public class SinSingleStraightSignal extends Signal {

    /**
     * Constructor (for params description go to base class (Signal Class)
     */
    public SinSingleStraightSignal(int minT, int maxT, double customDevide, int amplit){
        super(minT,maxT,customDevide,amplit);
        this.calculateValue();
    }

    /**
     * Method for calculating values on Y-Axis
     */
    public void calculateValue(){
        for(int i=tMin; i<arraySize; i++){
            dataSet[i][1] = Math.sin(dataSet[i][0]) * amplitude;
            if(dataSet[i][1] < 0){
                dataSet[i][1] = 0;
            }
        }
    }

    /**
     * Method calling base class method for drawing 2d graph
     */
    public void drawPlot(){
        super.createPlot();
    }
}
