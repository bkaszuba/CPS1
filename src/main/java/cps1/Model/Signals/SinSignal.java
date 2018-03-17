package cps1.Model.Signals;


/**
 * Class for Sinus signal
 */
public class SinSignal extends Signal {

    /**
     * Constructor (for params description go to base class (Signal Class)
     */
    public SinSignal(int minT, int maxT, double customDevide, int amplit, double perio) {
        super(minT, maxT, customDevide, amplit, perio);
        this.calculateValue();
    }

    /**
     * Constructor
     *
     * @param _path - path to file with dataSet
     */
    public SinSignal(String _path, Type type) {
        super(_path,type);
        if(type.equals(Type.Params)) {
            this.calculateValue();
        }
    }

    /**
     * Method for calculating values on Y-Axis
     */
    public void calculateValue() {
        for (int i = 0; i < arraySize; i++) {
            dataSet[i][1] = amplitude * Math.sin(((2.0 * Math.PI / period) * (dataSet[i][0] - tMin)));
        }
    }

    /**
     * Method calling base class method for drawing 2d graph
     */
    public void drawPlot() {
        super.createPlot();
    }

    /**
     * Method calling base class method for drawing histogram graph
     */
    public void drawHistogram() {
        super.createHistogram();
    }

}
