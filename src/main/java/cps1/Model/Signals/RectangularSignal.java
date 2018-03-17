package cps1.Model.Signals;

/**
 * Created by madekko on 28.02.2018.
 */
public class RectangularSignal extends Signal {
    public RectangularSignal(int _tMin, int _tMax, double _devide, int _amplitude, double _period, double _fillingRate) {
        super(_tMin, _tMax, _devide, _amplitude, _period, _fillingRate, 0.0);
        this.calculateValue();
    }

    /**
     * Constructor
     *
     * @param _path - path to file with dataSet
     */
    public RectangularSignal(String _path, Type type) {
        super(_path,type);
        if(type.equals(Type.Params)) {
            this.calculateValue();
        }
    }

    /**
     * Method for calculating values on Y-Axis
     */
    public void calculateValue() {
        int k = 0;
        for (int i = 0; i < arraySize; i++) {
            if (dataSet[i][0] > period * k + period + tMin) {
                k++;
            }
//            k = (int) (dataSet[i][0] / period);
            if (dataSet[i][0] >= (k * period + tMin) && dataSet[i][0] < (fillingRate * period + k * period + tMin)) {
                dataSet[i][1] = amplitude;
            } else if (dataSet[i][0] >= (fillingRate * period + k * period + tMin) && dataSet[i][0] < (period + k * period + tMin)) {
                dataSet[i][1] = 0;
            }
        }
    }

    /**
     * Method calling base class method for drawing 2d graph
     */
    public void drawPlot() {
        super.createPlot();
    }
}
