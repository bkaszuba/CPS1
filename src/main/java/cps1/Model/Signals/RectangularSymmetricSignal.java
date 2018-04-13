package cps1.Model.Signals;

public class RectangularSymmetricSignal extends Signal {

    public static final double STEPT = 0.0;

    public RectangularSymmetricSignal(int _tMin, int _tMax, double _devide, int _amplitude, double _period, double _fillingRate) {
        super(_tMin, _tMax, _devide, _amplitude, _period, _fillingRate, STEPT);
        this.calculateValue();
    }

    public RectangularSymmetricSignal(String _path, Type type) {
        super(_path,type);
        if(type.equals(Type.Params)) {
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
                dataSet[i][1] = -amplitude;
            }

        }
    }

    public void drawPlot() {
        super.createPlot();
    }
}
