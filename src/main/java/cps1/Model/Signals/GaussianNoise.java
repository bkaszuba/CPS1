package cps1.Model.Signals;

import java.util.Random;

public class GaussianNoise extends Signal {

    public static final double PERIOD = 0.0;
    public static final double FILLING_RATE = 0.0;
    public static final double STEPT = 0.0;
    public GaussianNoise(int _tMin, int _tMax, double _devide, int _amplitude) {
        super(_tMin, _tMax, _devide, _amplitude, PERIOD, FILLING_RATE, STEPT);
        this.calculateValue();
    }

    public GaussianNoise(String _path, Signal.Type type) {
        super(_path, type);
        if (type.equals(Signal.Type.Params)) {
            this.calculateValue();
        }
    }

    public void calculateValue() {
        for (int i = 0; i < arraySize; i++) {
            Random rand = new Random();
            dataSet[i][1] = amplitude * rand.nextGaussian();
//            dataSet[i][1] =( 1/(Math.sqrt(2*Math.PI))) * Math.pow(Math.E, -(Math.pow(dataSet[i][0],2))/(2.0));
        }
    }

    public void drawPlot() {
        super.createPlot();
    }
}
