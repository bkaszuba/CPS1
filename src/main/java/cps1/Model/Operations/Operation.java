package cps1.Model.Operations;

import cps1.Model.Signals.Signal;

public class Operation {

    Signal firstSignal;
    Signal secondSignal;
    public Signal result;
    double dataSet[][];
    int arraySize;

    public Operation(Signal _firstSignal, Signal _secondSignal) {
        firstSignal = _firstSignal;
        secondSignal = _secondSignal;
        calculateTime();
    }

    public void add() {
        for (int i = 0; i < dataSet.length; i++) {

            if (isElementExists(firstSignal.dataSet, i) && isElementExists(secondSignal.dataSet, i)) {
                dataSet[i][1] = firstSignal.dataSet[i][1] + secondSignal.dataSet[i][1];
            }
        }
        result = new Signal(dataSet, arraySize);
    }

    public void subtract() {
        for (int i = 0; i < firstSignal.getArraySize(); i++) {
            if (isElementExists(firstSignal.dataSet, i) && isElementExists(secondSignal.dataSet, i)) {
                dataSet[i][1] = firstSignal.dataSet[i][1] - secondSignal.dataSet[i][1];
            }
        }
        result = new Signal(dataSet, arraySize);
    }

    public void multiply() {
        for (int i = 0; i < firstSignal.getArraySize(); i++) {
            if (isElementExists(firstSignal.dataSet, i) && isElementExists(secondSignal.dataSet, i)) {
                dataSet[i][1] = firstSignal.dataSet[i][1] * secondSignal.dataSet[i][1];
            }
        }
        result = new Signal(dataSet, arraySize);
    }

    public void divide() {
        for (int i = 0; i < firstSignal.getArraySize(); i++) {
            if (isElementExists(firstSignal.dataSet, i) && isElementExists(secondSignal.dataSet, i)) {
                if (secondSignal.dataSet[i][1] != 0) {
                    dataSet[i][1] = firstSignal.dataSet[i][1] / secondSignal.dataSet[i][1];
                } else {
                    dataSet[i][1] = firstSignal.dataSet[i][1] / 0.0001;
                }
            }
        }
        result = new Signal(dataSet, arraySize);
    }

    public void calculateTime() {
        int startX = getMin(firstSignal.gettMin(), secondSignal.gettMin());
        int endX = getMax(firstSignal.gettMax(), secondSignal.gettMax());
        int range = Math.abs(endX - startX);
        arraySize = (int) (range / 0.01);
        this.dataSet = new double[arraySize][arraySize];
        double value = startX;
        for (int i = 0; i < arraySize; i++) {
            this.dataSet[i][0] = value;
            value = value + 0.01;
        }
    }

    public static boolean isElementExists(double[][] data, int index) {
        try {
            Double d = data[index][0];
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public static int getMax(int a, int b) {
        return (a >= b ? a : b);
    }

    public static int getMin(int a, int b) {
        return (a <= b ? a : b);
    }

}
