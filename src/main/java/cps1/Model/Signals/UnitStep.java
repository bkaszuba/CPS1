package cps1.Model.Signals;

public class UnitStep extends Signal {

    public static final double PERIOD = 0.0;
    public static final double FILLING_RATE = 0.0;

    public UnitStep(int _tMin, int _tMax, double _divide, int _amplitude, double _timeStep) {
        super(_tMin, _tMax, _divide, _amplitude, PERIOD, FILLING_RATE, _timeStep);
        this.calculateValue();
    }

    public UnitStep(String _path, Type type) {
        super(_path, type);
        if (type.equals(Type.Params)) {
            this.calculateValue();
        }
    }

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

    public void drawPlot() {
        super.createPlot();
    }
}
