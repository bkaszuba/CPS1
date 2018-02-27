package cps1.Model;

/**
 * Class for Sinus double straight signal
 */
public class SinDoubleStraightSignal extends Signal {

    /**
     * Constructor (for params description go to base class (Signal Class)
     */
    public SinDoubleStraightSignal(int minT, int maxT, double customDevide, int amplit) {
        super(minT,maxT,customDevide,amplit);
        this.calculateValue();
    }

    /**
     * Method for calculating values on Y-Axis
     */
    public void calculateValue(){
        for(int i=tMin; i<arraySize; i++){
            dataSet[i][1] = Math.abs(Math.sin(dataSet[i][0]) * amplitude);
        }
    }

    /**
     * Method calling base class method for drawing 2d graph
     */
    public void drawPlot(){
        super.createPlot();
    }
}
