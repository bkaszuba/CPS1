package cps1.Model.Signals;

/**
 * Created by madekko on 11.03.2018.
 */
public class UnitStep extends Signal {
    public UnitStep(int _tMin, int _tMax, double _divide, int _amplitude, double _timeStep) {
        super(_tMin, _tMax, _divide, _amplitude, 0.0, 0.0, _timeStep);
        this.calculateValue();
    }

    /**
     * Constructor
     *
     * @param _path - path to file with dataSet
     */
    public UnitStep(String _path, Type type) {
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

            if (dataSet[i][0] > stepTime) {
                dataSet[i][1] = amplitude;
            } else if (dataSet[i][0] == stepTime) {
                dataSet[i][1] = amplitude / 2.0;
            } else if (dataSet[i][0] < stepTime) {
                dataSet[i][1] = 0.0;
            }

        }

    }

    /**
     * Method calling base class method for drawing 2d graph
     */
    public void drawPlot() {
        super.createPlot();
    }
}
