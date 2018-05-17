package cps1.Model.Operations;


import cps1.Model.Signals.Signal;

public class ConvolutionCalculator {

    public Signal calculateConvolution(Signal h, Signal x) {
        int arraySize = h.getArraySize() + x.getArraySize() - 1;
        double[][] sampleDataSet = new double[arraySize][2];
        int range = Math.abs(h.gettMax() - h.gettMin());
        double step = (double) range / h.getArraySize();
        double value = 0;
        for (int n = 0; n < arraySize; n++) {
            double temp = 0.0;
            int kMin = (n >= x.getArraySize() - 1) ? n - (x.getArraySize() - 1) : 0;
            int kMax = (n < h.getArraySize() - 1) ? n : h.getArraySize() - 1;
            for (int k = kMin; k <= kMax; k++) {
                temp += h.dataSet[k][1] * x.dataSet[n - k][1];
            }
            sampleDataSet[n][1] = temp;
            sampleDataSet[n][0] = value;
            value += step;
        }
        return new Signal(sampleDataSet, sampleDataSet.length);
    }

    public Signal calculateConvolutionForFilter(Signal h, double[] x) {
        int arraySize = h.getArraySize() + x.length - 1;
        double[][] sampleDataSet = new double[arraySize][2];
        int range = Math.abs(h.gettMax() - h.gettMin());
        double step = (double) range / h.getArraySize();
        double value = 0;
        for (int n = 0; n < arraySize; n++) {
            double temp = 0.0;
            int kMin = (n >= x.length - 1) ? n - (x.length - 1) : 0;
            int kMax = (n < h.getArraySize() - 1) ? n : h.getArraySize() - 1;
            for (int k = kMin; k <= kMax; k++) {
                temp += h.dataSet[k][1] * x[n - k];
            }
            sampleDataSet[n][1] = temp;
            sampleDataSet[n][0] = value;
            value += step;
        }
        return new Signal(sampleDataSet, sampleDataSet.length);
    }
}
