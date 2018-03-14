package cps1.Model;

/**
 * Class for Sinus single straight signal
 */
public class SinSingleStraightSignal extends Signal {

    /**
     * Constructor (for params description go to base class (Signal Class)
     */
    public SinSingleStraightSignal(int minT, int maxT, double customDevide, int amplit, double perio) {
        super(minT,maxT,customDevide,amplit,perio);
        this.calculateValue();
    }

    /**
     * Method for calculating values on Y-Axis
     */
    public void calculateValue(){
        for(int i=tMin; i<arraySize; i++){
            dataSet[i][1] = 1/2.0 * amplitude * ((Math.sin((( 2.0 * Math.PI/ period ) * (dataSet[i][0] - tMin)))) + Math.abs(Math.sin((( 2.0 * Math.PI/ period ) * (dataSet[i][0] - tMin))) ));//   Math.abs(Math.sin((( 2 * Math.PI/ period ) * (dataSet[i][0] - tMin))));

        }
    }

    /**
     * Method calling base class method for drawing 2d graph
     */
    public void drawPlot(){
        super.createPlot();
    }
}
