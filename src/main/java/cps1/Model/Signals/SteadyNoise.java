package cps1.Model.Signals;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by madekko on 18.03.2018.
 */
public class SteadyNoise extends  Signal{
    public SteadyNoise(int _tMin, int _tMax, double _devide, int _amplitude) {
        super(_tMin, _tMax, _devide, _amplitude, 0.0, 0.0, 0.0);
        this.calculateValue();
    }

    /**
     * Constructor
     *
     * @param _path - path to file with dataSet
     */
    public SteadyNoise(String _path, Signal.Type type) {
        super(_path, type);
        if (type.equals(Signal.Type.Params)) {
            this.calculateValue();
        }
    }

    /**
     * Method for calculating values on Y-Axis
     */

    public void calculateValue() {

        for (int i = 0; i < arraySize; i++) {
        dataSet[i][1] = ThreadLocalRandom.current().nextDouble(-amplitude, amplitude + 1);

        }
    }


    /**
     * Method calling base class method for drawing 2d graph
     */
    public void drawPlot() {
        super.createPlot();
    }
}
