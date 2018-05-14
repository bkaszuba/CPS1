package cps1.Model.Signals;

public class SinSingleStraightSignal extends Signal {

    public static final double FILLING_RATE = 0.0;
    public static final double STEPT = 0.0;

    public SinSingleStraightSignal(int minT, int maxT, int _frequency, int amplit, double perio) {
        super(minT, maxT, _frequency, amplit, perio, FILLING_RATE, STEPT);
        this.calculateValue();
    }

    public SinSingleStraightSignal(String _path, Type type) {
        super(_path,type);
        if(type.equals(Type.Params)) {
            this.calculateValue();
        }
    }

    public void calculateValue() {
        for (int i = 0; i < arraySize; i++) {
            dataSet[i][1] = 1 / 2.0 * amplitude * ((Math.sin(((2.0 * Math.PI / period) * (dataSet[i][0] - tMin)))) + Math.abs(Math.sin(((2.0 * Math.PI / period) * (dataSet[i][0] - tMin)))));//   Math.abs(Math.sin((( 2 * Math.PI/ period ) * (dataSet[i][0] - tMin))));

        }
    }

    public void drawPlot() {
        super.createPlot();
    }
}
