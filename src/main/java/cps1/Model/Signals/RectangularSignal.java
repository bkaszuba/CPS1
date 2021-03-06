package cps1.Model.Signals;

public class RectangularSignal extends Signal {

    public static final double STEPT = 0.0;

    public RectangularSignal(int _tMin, int _tMax, int _frequency, int _amplitude, double _period, double _fillingRate) {
        super(_tMin, _tMax, _frequency, _amplitude, _period, _fillingRate, STEPT);
        this.calculateValue();
    }

    public RectangularSignal(String _path, Type type) {
        super(_path, type);
        if (type.equals(Type.Params)) {
            this.calculateValue();
        }
    }

    public void calculateValue() {
        int k = 0;
        for (int i = 0; i < arraySize; i++) {
            if (dataSet[i][0] > period * k + period + tMin) {
                k++;
            }
            if (dataSet[i][0] >= (k * period + tMin) && dataSet[i][0] < (fillingRate * period + k * period + tMin)) {
                dataSet[i][1] = amplitude;
            } else if (dataSet[i][0] >= (fillingRate * period + k * period + tMin) && dataSet[i][0] < (period + k * period + tMin)) {
                dataSet[i][1] = 0;
            }
        }
    }

    public void drawPlot() {
        super.createPlot();
    }
}
