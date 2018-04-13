package cps1.Model.Signals;

import java.util.concurrent.ThreadLocalRandom;

public class SteadyNoise extends Signal {

    public static final double PERIOD = 0.0;
    public static final double FILLING_RATE = 0.0;
    public static final double STEPT = 0.0;

    public SteadyNoise(int _tMin, int _tMax, double _devide, int _amplitude) {
        super(_tMin, _tMax, _devide, _amplitude, PERIOD, FILLING_RATE, STEPT);
        this.calculateValue();
    }

    public SteadyNoise(String _path, Signal.Type type) {
        super(_path, type);
        if (type.equals(Signal.Type.Params)) {
            this.calculateValue();
        }
    }

    public void calculateValue() {
        for (int i = 0; i < arraySize; i++) {
            dataSet[i][1] = ThreadLocalRandom.current().nextDouble(-amplitude, amplitude);
        }
    }

    public void drawPlot() {
        super.createPlot();
    }
}
