package cps1.Model.Operations;


import cps1.Model.Signals.Signal;

public class ConvolutionCalculator {

    public Signal calculateSignalConvolution(Signal h, Signal x) {
        int M = h.getArraySize();
        int N = x.getArraySize();
        int totalLength = M + N - 1;
        double[][] sampleDataSet = new double[totalLength][2];
        int tMin = Math.min(h.gettMin(), x.gettMin());
        double t = tMin;
        int totalTime = (h.gettMax() - h.gettMin()) + (x.gettMax() - x.gettMin());
        double samplingFreq = totalLength / totalTime;
        double samplingPerio = 1.0 / samplingFreq;
        for (int n = 0; n < totalLength; n++) {
            double temp = 0.0;
            for (int k = 0; k < h.dataSet.length; k++) {
                if (n - k < x.dataSet.length && n - k > -1)
                    temp += h.dataSet[k][1] * x.dataSet[n - k][1];
            }
            sampleDataSet[n][1] = temp;
            sampleDataSet[n][0] = t;
            t += samplingPerio;
        }
        Signal convolution = new Signal(sampleDataSet, sampleDataSet.length);
        convolution.settMin(tMin);
        convolution.settMax(totalTime);
        return convolution;
    }

    public Signal calculateFilterConvolution(double[] h, Signal x) {
        int M = h.length;
        int N = x.getArraySize();
        int totalLength = M + N - 1;

        double[][] sampleDataSet = new double[totalLength][2];
        int range = Math.abs(x.gettMax() - x.gettMin());
        double step = (double) range / x.getArraySize();
        double value = x.gettMin();
        for (int n = 0; n < totalLength; n++) {
            double sum = 0.0;
            for (int k = 0; k < M; k++) {
                if (n - k < N && n - k > -1) {
                    sum += h[k] * x.dataSet[n - k][1];
                }
            }
            sampleDataSet[n][1] = sum;
            sampleDataSet[n][0] = value;
            value += step;
        }
        return new Signal(sampleDataSet, sampleDataSet.length);
    }
}
