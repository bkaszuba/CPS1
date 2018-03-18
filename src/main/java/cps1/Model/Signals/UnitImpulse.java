package cps1.Model.Signals;


/**
 * Created by madekko on 11.03.2018.
 */
public class UnitImpulse extends Signal {
    public UnitImpulse(int _tMin, int _tMax, double _divide, int _amplitude, double _timeStep) {
        super(_tMin, _tMax, _divide, _amplitude, 0.0, 0.0, _timeStep);
        this.calculateValue();
    }

    /**
     * Constructor
     *
     * @param _path - path to file with dataSet
     */
    public UnitImpulse(String _path, Type type) {
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
//            if (dataSet[i][0] == stepTime) {
            if (i == (int) stepTime) {
                dataSet[i][1] = 1;
            } else {
                dataSet[i][1] = 0;

            }
        }
    }

    /**
     * Method calling base class method for drawing 2d graph
     */
    public void drawPlot() {
        super.createScatterPlot();
    }
}
