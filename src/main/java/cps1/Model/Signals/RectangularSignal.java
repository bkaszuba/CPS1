package cps1.Model;

/**
 * Created by madekko on 28.02.2018.
 */
public class RectangularSignal extends Signal {
    public RectangularSignal(int _tMin, int _tMax, double _devide, int _amplitude, double _period, double _fillingRate) {
        super(_tMin, _tMax, _devide, _amplitude, _period, _fillingRate, 0.0);
        this.calculateValue();
    }

    /**
     * Method for calculating values on Y-Axis
     */
    public void calculateValue() {
        int k = 0;
        for (int i = tMin; i < arraySize; i++) {
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
//        dataSet[0][1] = -1;
//        dataSet[1][1] = 11;

//        super.showDataSet();
    }

    /**
     * Method calling base class method for drawing 2d graph
     */
    public void drawPlot() {
        super.createPlot();
    }
}
