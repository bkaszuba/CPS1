package cps1.Model.Signals;

/**
 * Created by madekko on 11.03.2018.
 */
public class ImpulseNoise extends Signal {
    public ImpulseNoise(int _tMin, int _tMax, double _divide, int _amplitude, double _timeStep) {
        super(_tMin, _tMax, _divide, _amplitude, 0.0, 0.0, _timeStep);
        this.calculateValue();
    }

    /**
     * Constructor
     *
     * @param _path - path to file with dataSet
     */
    public ImpulseNoise(String _path) {
        super(_path);
    }

    /**
     * Method for calculating values on Y-Axis
     */
    public void calculateValue() {
        for (int i = tMin; i < arraySize; i++) {

            if (Math.random() <= stepTime) {
                dataSet[i][1] = amplitude;
            } else {
                dataSet[i][1] = 0;

            }
        }
        super.showDataSet();
    }

    /**
     * Method calling base class method for drawing 2d graph
     */
    public void drawPlot() {
        super.createPlot();
    }
}

