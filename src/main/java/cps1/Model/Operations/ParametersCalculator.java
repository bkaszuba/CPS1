package cps1.Model.Operations;

import cps1.Model.Signals.Signal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParametersCalculator {

    Signal firstSignal;

    public ParametersCalculator(Signal firstSignal) {
        this.firstSignal = firstSignal;
    }

    public void calculateAverage() {
        double average;
        double temp = 0.0;
        average = 1.0 / (firstSignal.dataSet[firstSignal.dataSet.length - 1][0] - firstSignal.dataSet[0][1] + 1);

        for (int i = 0; i < firstSignal.dataSet.length; i++) {
            temp += firstSignal.dataSet[i][1];
        }
        average = average * temp;
        firstSignal.setAverage(average);
    }


    public void calculateAbsoluteAverage() {
        double average;
        double temp = 0.0;
        average = 1.0 / (firstSignal.dataSet[firstSignal.dataSet.length - 1][0] - firstSignal.dataSet[0][1] + 1);
        for (int i = 0; i < firstSignal.dataSet.length; i++) {
            temp += Math.abs(firstSignal.dataSet[i][1]);
        }
        average = average * temp;
        firstSignal.setAbsoluteAverage(average);
    }

    public void calculatePower() {
        double power;
        double temp = 0.0;
        power = 1.0 / (firstSignal.dataSet[firstSignal.dataSet.length - 1][0] - firstSignal.dataSet[0][1] + 1);
        for (int i = 0; i < firstSignal.dataSet.length; i++) {
            temp += Math.pow(firstSignal.dataSet[i][1], 2);
        }
        power = power * temp;
        firstSignal.setPowerAverage(power);
    }

    //Wiem, że duplicate ale nie będę tworzył nowej instancji tej całej klasy, żeby ustawić power
    private void calculatePower(Signal signal) {
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
        variance = 1.0 / (firstSignal.dataSet[firstSignal.dataSet.length - 1][0] - firstSignal.dataSet[0][1] + 1);
        for (int i = 0; i < firstSignal.dataSet.length; i++) {
            temp += Math.pow((firstSignal.dataSet[i][1] - firstSignal.getAverage()), 2);
        }
        variance = variance * temp;
        firstSignal.setVariance(variance);
    }

    public void calculateRMS() {
        double rms;
        rms = Math.sqrt(firstSignal.getPowerAverage());
        firstSignal.setRms(rms);
    }

    public void calculateRMS(Signal signal) {
        double rms;
        rms = Math.sqrt(signal.getPowerAverage());
        signal.setRms(rms);
    }

    public double calculateMSE(Signal firstSignal, Signal secondSignal) {
        double sum_sq = 0;
        double mse;
        int length = secondSignal.dataSet.length;
        for (int i = 0; i < secondSignal.dataSet.length; i++) {
            for(int j=0; j<firstSignal.dataSet.length; j++) {
                if (secondSignal.dataSet[i][0] == firstSignal.dataSet[j][0]) {
                    double p1 = firstSignal.dataSet[j][1];
                    double p2 = secondSignal.dataSet[i][1];
                    double err = p2 - p1;
                    sum_sq += (err * err);
                }
            }
        }
        mse = sum_sq / length * length;
        return mse;
    }

    public double calculateSNR(Signal firstSignal, Signal secondSignal) {
        calculatePower(firstSignal);
        calculatePower(secondSignal);
        calculateRMS(firstSignal);
        calculateRMS(secondSignal);
        double noiseRMS = secondSignal.getRms();
        double signalRMS = firstSignal.getRms();
        return Math.pow(signalRMS / noiseRMS, 2);
    }

    public double calculatePSNR(Signal signal, Signal secondSignal) {
        double max = signal.dataSet[0][1];
        for(int i=1; i<signal.dataSet.length; i++){
            if(signal.dataSet[i][1] > max) {
                max = signal.dataSet[i][1];
            }
        }
        double psnr = (max*max)/calculateMSE(signal, secondSignal);
        return 10 * Math.log10(psnr);
    }

    public double calculateMD(Signal firstSignal, Signal secondSignal) {
        List<Double> difference = new ArrayList<>();
        for (int i = 0; i < secondSignal.dataSet.length; i++) {
            for(int j=0; j<firstSignal.dataSet.length; j++) {
                if (secondSignal.dataSet[i][0] == firstSignal.dataSet[j][0]) {
                    double p1 = firstSignal.dataSet[j][1];
                    double p2 = secondSignal.dataSet[i][1];
                    difference.add(Math.abs(p1-p2));
                }
            }
        }
        return Collections.max(difference);
    }
}
