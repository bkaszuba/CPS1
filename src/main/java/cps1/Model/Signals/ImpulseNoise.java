package cps1.Model.Signals;

public class ImpulseNoise extends Signal {

    public static final double FILLING_RATE = 0.0;
    public static final double PERIOD = FILLING_RATE;

    public ImpulseNoise(int _tMin, int _tMax, int _frequency, int _amplitude, double _timeStep) {
        super(_tMin, _tMax, _frequency, _amplitude, PERIOD, FILLING_RATE, _timeStep);
        this.calculateValue();
    }

    public ImpulseNoise(String _path, Type type) {
        super(_path, type);
        if (type.equals(Type.Params)) {
            this.calculateValue();
        }
    }

    public void calculateValue() {
        for (int i = 0; i < arraySize; i++) {

            if (Math.random() <= stepTime) {
                dataSet[i][1] = amplitude;
            } else {
                dataSet[i][1] = 0;

            }
        }
    }

    public void drawPlot() {
        super.createScatterPlot();
    }
}

