package cps1.Model.Signals;

public class TriangularSignal extends Signal {

    public static final double STEPT = 0.0;

    public TriangularSignal(int _tMin, int _tMax, double _devide, int _amplitude, double _period, double _fillingRate) {
        super(_tMin, _tMax, _devide, _amplitude, _period, _fillingRate, STEPT);
        this.calculateValue();
    }

    public TriangularSignal(String _path, Type type) {
        super(_path, type);
        if (type.equals(Type.Params)) {
            this.calculateValue();
        }
    }

    public void calculateValue() {
        int k = 0;
        for (int i = 0; i < arraySize; i++) {
            if (dataSet[i][0] >= k * period + tMin && dataSet[i][0] < fillingRate * period + k * period + tMin) {
                if (dataSet[i][0] <= (tMin + (period + k * period))) {
                    dataSet[i][1] = dataSet[i][0];
                    dataSet[i][1] = (amplitude / (fillingRate * period)) * (dataSet[i][0] - k * period - tMin);
                } else {
                    k++;
                }
            } else {
                if (dataSet[i][0] <= (tMin + (period + k * period))) {
                    dataSet[i][1] = dataSet[i][0];
                    dataSet[i][1] = (-amplitude / (period * (1 - fillingRate))) * (dataSet[i][0] - k * period - tMin) + (amplitude / (1 - fillingRate));
                } else {
                    k++;
                }
            }
        }
    }

    public void drawPlot() {
        super.createPlot();
    }

}
