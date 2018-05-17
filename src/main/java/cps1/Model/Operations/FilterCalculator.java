package cps1.Model.Operations;

import cps1.Model.Operations.Windows.Window;

public class FilterCalculator {

    double[] filter;

    public FilterCalculator(int filterRow, double cutOffFrequency, double originalFrequency, FilterType filterType, Window window) {

        switch (filterType) {
            case Lowpass:
                filter = calculateLowPassFilter(filterRow, cutOffFrequency, originalFrequency, window);
                break;
            case Highpass:
                filter = calculateHighPassFilter(filterRow, cutOffFrequency, originalFrequency, window);
                break;
            case Bandpass:
                filter =calculateBandPassFilter(filterRow, cutOffFrequency, originalFrequency, window);
                break;
        }
    }

    public FilterCalculator() {}

    public enum FilterType {
        Highpass,
        Lowpass,
        Bandpass
    }

    public double[] calculateLowPassFilter(int filterRow, double cutOffFrequency, double originalFrequency, Window window) {
        double[] h = new double[filterRow];
        int N = (filterRow - 1) / 2;
        double fc = cutOffFrequency;
        double sf = originalFrequency;
        int K = (int) (sf / fc);

        for (int i = 0; i < filterRow; i++) {
            h[i] = ((Math.sin((2 * Math.PI * (i - N)) / K)) / (Math.PI * (i - N))) * window.getValue(i);
        }
        h[N] = 2.0 / (K * 1.0);
        return h;
    }

    public double[] calculateBandPassFilter(int filterRow, double cutOffFrequency, double originalFrequency, Window window) {
        double[] lowPassFilter = this.calculateLowPassFilter(filterRow, cutOffFrequency, originalFrequency, window);
        double[] h = new double[filterRow];
        for (int i = 0; i < filterRow; i++) {
            h[i] = lowPassFilter[i] * 2 * Math.sin(Math.PI * i / 2);
        }
        return h;
    }

    public double[] calculateHighPassFilter(int filterRow, double cutOffFrequency, double originalFrequency, Window window) {
        double[] lowPassFilter = this.calculateLowPassFilter(filterRow, cutOffFrequency, originalFrequency, window);
        double[] h = new double[filterRow];
        for (int i = 0; i < filterRow; i++) {
            h[i] = lowPassFilter[i] * Math.pow(-1, i);
        }
        return h;
    }

    public double[] getFilter() {
        return filter;
    }
}
