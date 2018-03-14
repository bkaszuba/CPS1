package cps1.Model;

import cps1.Model.Signals.Signal;

/**
 * Created by madekko on 13.03.2018.
 */
public class Operation {

    Signal firstSignal;
    Signal secondSignal;
    Signal result;
    double dataSet[][];
    int arraySize;

    public Operation(Signal _firstSignal, Signal _secondSignal) {
        firstSignal = _firstSignal;
        secondSignal = _secondSignal;
        calculateTime();
    }

    public void add() {
        dataSet = new double[firstSignal.getArraySize()][firstSignal.getArraySize()];
        for (int i = 0; i < firstSignal.getArraySize(); i++) {
            dataSet[i][1] = firstSignal.dataSet[i][1] + secondSignal.dataSet[i][1];
            dataSet[i][0] = firstSignal.dataSet[i][0];
        }
        result = new Signal(dataSet);
        result.showDataSet();
        result.createPlot();
    }

    public void subtract() {
        dataSet = new double[firstSignal.getArraySize()][firstSignal.getArraySize()];
        for (int i = 0; i < firstSignal.getArraySize(); i++) {
            dataSet[i][1] = firstSignal.dataSet[i][1] - secondSignal.dataSet[i][1];
            dataSet[i][0] = firstSignal.dataSet[i][0];
        }
        result = new Signal(dataSet);
        result.showDataSet();
        result.createPlot();

    }

    public void multiply() {
        dataSet = new double[firstSignal.getArraySize()][firstSignal.getArraySize()];
        for (int i = 0; i < firstSignal.getArraySize(); i++) {
            dataSet[i][1] = firstSignal.dataSet[i][1] * secondSignal.dataSet[i][1];
            dataSet[i][0] = firstSignal.dataSet[i][0];
        }
        result = new Signal(dataSet);
        result.showDataSet();
        result.createPlot();

    }

    public void divide() {
        dataSet = new double[firstSignal.getArraySize()][firstSignal.getArraySize()];
        for (int i = 0; i < firstSignal.getArraySize(); i++) {
            dataSet[i][1] = firstSignal.dataSet[i][1] / secondSignal.dataSet[i][1];
            dataSet[i][0] = firstSignal.dataSet[i][0];
        }
        result = new Signal(dataSet);
        result.showDataSet();
        result.createPlot();

    }

    /**
     * Method for calculating values on X-Axis
     */
    public void calculateTime() {
        int startX = getMin(firstSignal.gettMin(), secondSignal.gettMin());
        int endX =  getMax(firstSignal.gettMax(), secondSignal.gettMax());
        int range = Math.abs(endX - startX);
        arraySize = (int) (range / 0.01);
        this.dataSet = new double[arraySize][arraySize];
    }

    public static int getMax(int a, int b) {
        return (a >= b ? a : b);
    }

    public static int getMin(int a, int b) {
        return (a <= b ? a : b);
    }

}
