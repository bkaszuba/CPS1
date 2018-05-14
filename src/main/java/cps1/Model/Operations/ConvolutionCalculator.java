package cps1.Model.Operations;


import cps1.Model.Signals.Signal;

public class ConvolutionCalculator {

    public Signal calculateConvolution(Signal h, Signal x) {
        int arraySize = h.getArraySize() + x.getArraySize() - 1;
        double[][] sampleDataSet = new double[arraySize][2];
        double temp = 0.0;
        int range = Math.abs(h.gettMax() - h.gettMin());
        double step = (double) range / h.getArraySize();
        double value = 0;
        for (int n = 0; n < arraySize; n++) {
            for (int k = 0; k < h.getArraySize() - 5; k++) {
                if( n-k > 0 && n-k < 400) {
                    temp += h.dataSet[k][1] * x.dataSet[n - k][1];
                } else {
                    temp += h.dataSet[k][1] * x.dataSet[0][1];
                }
            }
            sampleDataSet[n][1] = temp;
            sampleDataSet[n][0] = value;
            value += step;
        }
        return new Signal(sampleDataSet, sampleDataSet.length);
    }
}
