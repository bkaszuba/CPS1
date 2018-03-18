package cps1.Model.Signals;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by madekko on 18.03.2018.
 */
public class GaussianNoise extends Signal {
    public GaussianNoise(int _tMin, int _tMax, double _devide, int _amplitude) {
        super(_tMin, _tMax, _devide, _amplitude, 0.0, 0.0, 0.0);
        this.calculateValue();
    }

    /**
     * Constructor
     *
     * @param _path - path to file with dataSet
     */
    public GaussianNoise(String _path, Signal.Type type) {
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

            Random rand = new Random();

            dataSet[i][1] = amplitude * rand.nextGaussian();
//            dataSet[i][1] =( 1/(Math.sqrt(2*Math.PI))) * Math.pow(Math.E, -(Math.pow(dataSet[i][0],2))/(2.0));
        }
    }


    /**
     * Method calling base class method for drawing 2d graph
     */
    public void drawPlot() {
        super.createPlot();
    }
}
