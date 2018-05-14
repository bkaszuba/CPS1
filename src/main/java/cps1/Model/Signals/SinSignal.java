package cps1.Model.Signals;

public class SinSignal extends Signal {

    public static final double STEPT = 0.0;
    public static final double FILLING_RATE = 0.0;

    public SinSignal(int minT, int maxT, int frequency, int amplit, double perio) {
        super(minT, maxT, frequency, amplit, perio, FILLING_RATE, STEPT);
        this.calculateValue();
    }

    public SinSignal(String _path, Type type) {
        super(_path, type);
        if (type.equals(Type.Params)) {
            this.calculateValue();
        }
    }

    public void calculateValue() {
        for (int i = 0; i < arraySize; i++) {
            dataSet[i][1] = amplitude * Math.sin(((2.0 * Math.PI / period) * (dataSet[i][0] - tMin)));
        }
    }

    public void drawPlot() {
        super.createPlot();
    }

    public void drawHistogram() {
        super.createHistogram();
    }

}
