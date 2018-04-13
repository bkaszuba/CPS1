package cps1.Model.Operations;

import cps1.Model.Signals.Signal;

public class ParametersCalculator {

    Signal signal = null;

    public ParametersCalculator(Signal signal) {
        this.signal = signal;
    }

    public void calculateAverage() {
        double average;
        double temp = 0.0;
        average = 1.0 / (signal.dataSet[signal.dataSet.length - 1][0] - signal.dataSet[0][1] + 1);

        for (int i = 0; i < signal.dataSet.length; i++) {
            temp += signal.dataSet[i][1];
        }
        average = average * temp;
        signal.setAverage(average);
    }


    public void calculateAbsoluteAverage() {
        double average;
        double temp = 0.0;
        average = 1.0 / (signal.dataSet[signal.dataSet.length - 1][0] - signal.dataSet[0][1] + 1);
        for (int i = 0; i < signal.dataSet.length; i++) {
            temp += Math.abs(signal.dataSet[i][1]);
        }
        average = average * temp;
        signal.setAbsoluteAverage(average);
    }

    public void calculatePower() {
        double power;
        double temp = 0.0;
        power = 1.0 / (signal.dataSet[signal.dataSet.length - 1][0] - signal.dataSet[0][1] + 1);
        for (int i = 0; i < signal.dataSet.length; i++) {
            temp += Math.pow(signal.dataSet[i][1], 2);
        }
        power = power * temp;
        signal.setPowerAverage(power);
    }

    public void calculateVariance() {
        double variance;
        double temp = 0.0;
        variance = 1.0 / (signal.dataSet[signal.dataSet.length - 1][0] - signal.dataSet[0][1] + 1);
        for (int i = 0; i < signal.dataSet.length; i++) {
            temp += Math.pow((signal.dataSet[i][1] - signal.getAverage()), 2);
        }
        variance = variance * temp;
        signal.setVariance(variance);
    }

    public void calculateRMS() {

        double rms;
        rms = Math.sqrt(signal.getPowerAverage());
        signal.setRms(rms);
    }
}
