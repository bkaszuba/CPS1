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

    public Signal calculateSignalCorelation(Signal h, Signal x) {
        int convolutionLength = h.getArraySize() + x.getArraySize() - 1;
        double[][] corelationTab = new double[convolutionLength][2];
        double t = Math.min(h.gettMin(), x.gettMin());
        double totalTime = (h.gettMax() - h.gettMin()) + (x.gettMax() - x.gettMin());
        double samplingFreq = convolutionLength / totalTime;
        double samplingPeriod = 1.0 / samplingFreq;
        for (int n = 0; n < convolutionLength; n++) {
            double sum = 0.0;
            for (int k = 0; k < h.getArraySize(); k++) {
                if (n - k < x.getArraySize() && n - k > -1) {
                    sum += h.dataSet[k][1] * x.dataSet[n - k][1];
                }
            }
            corelationTab[n][0] = t;
            corelationTab[n][1] = -sum;
            t += samplingPeriod;
        }
        return new Signal(corelationTab, corelationTab.length);
    }

    private int findFirstIndexForMaxYValueInSecondPartOfCorrelation(double[][] tab) {
        int index = (int) Math.ceil(tab.length / 2.0);
        double max = tab[index][1];
        for (int i = tab.length / 2; i < tab.length; i++) {
            if (tab[i][1] > max) {
                index = i;
                max = tab[i][1];
            }
        }
        return index;
    }

    public double calculateDistance(double v, Signal signal) {
        double t = calculateDeltaT(signal);
        System.out.println(t);
        return v * t;
    }

    private double calculateDeltaT(Signal signal) {
        int index = (int) Math.ceil(signal.getArraySize() / 2.0);
        int numberOfSamples = findFirstIndexForMaxYValueInSecondPartOfCorrelation(signal.dataSet) - index;
        double t = numberOfSamples / signal.getFrequency();
        return t;
    }

    public Signal shiftSignalValues(Signal signal, double time) {
        double[][] shiftedValues = new double[signal.getArraySize()][2];
        double[][] tempTab = signal.dataSet;
        int shiftedSamples = (int) (time * signal.getFrequency());
        for (int i = 0; i < signal.getArraySize(); i++) {
            shiftedValues[i][0] = tempTab[i][0];
            if (i < shiftedSamples) {
                shiftedValues[i][1] = tempTab[(signal.getArraySize() - 1) - shiftedSamples + i][1];
            } else {
                shiftedValues[i][1] = tempTab[i - shiftedSamples][1];
            }
        }
        return new Signal(shiftedValues, shiftedValues.length);
    }
}
