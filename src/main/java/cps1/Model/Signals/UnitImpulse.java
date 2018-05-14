package cps1.Model.Signals;

public class UnitImpulse extends Signal {

    public static final double PERIOD = 0.0;
    public static final double FILLING_RATE = 0.0;

    public UnitImpulse(int _tMin, int _tMax, int _frequency, int _amplitude, double _timeStep) {
        super(_tMin, _tMax, _frequency, _amplitude, PERIOD, FILLING_RATE, _timeStep);
        this.calculateValue();
    }

    public UnitImpulse(String _path, Type type) {
        super(_path,type);
        if(type.equals(Type.Params)) {
            this.calculateValue();
        }
    }

    public void calculateValue() {
        for (int i = 0; i < arraySize; i++) {
//            if (dataSet[i][0] == stepTime) {
            if (i == (int) stepTime) {
                dataSet[i][1] = 1;
            } else {
                dataSet[i][1] = 0;

            }
        }
    }

    public void drawPlot() {
        super.createScatterPlot();
    }
}
