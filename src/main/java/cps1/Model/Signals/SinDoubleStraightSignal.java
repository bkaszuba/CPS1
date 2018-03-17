package cps1.Model.Signals;

/**
 * Class for Sinus double straight signal
 */
public class SinDoubleStraightSignal extends Signal {

    /**
     * Constructor (for params description go to base class (Signal Class)
     */
    public SinDoubleStraightSignal(int minT, int maxT, double customDevide, int amplit, double perio) {
        super(minT, maxT, customDevide, amplit, perio);
        this.calculateValue();
    }

    /**
     * Constructor
     *
     * @param _path - path to file with dataSet
     */
    public SinDoubleStraightSignal(String _path, Type type) {
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
            dataSet[i][1] = amplitude * Math.abs(Math.sin(((2 * Math.PI / period) * (dataSet[i][0] - tMin))));
        }
    }

    /**
     * Method calling base class method for drawing 2d graph
     */
    public void drawPlot() {
        super.createPlot();
    }
}
