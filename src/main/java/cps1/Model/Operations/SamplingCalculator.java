package cps1.Model.Operations;

import cps1.Model.Signals.Signal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.*;

public class SamplingCalculator {

    private Signal signal;
    private double sampleDataSet[][];
    private int sampleTime;
    private double quantizedDataSet[][];

    public SamplingCalculator(Signal sig, int samTime) {
        this.signal = sig;
        this.sampleTime = samTime;
    }

    public Signal createSampleSignal() {
        sampleDataSet = new double[signal.dataSet.length / sampleTime][2];
        int k = 0;
        for (int i = 0; i < signal.dataSet.length; i += sampleTime) {
            for (int j = 0; j < 2; j++) {
                sampleDataSet[k][j] = signal.dataSet[i][j];
            }
            k++;
        }
        return new Signal(sampleDataSet, sampleDataSet.length);
    }

    public Signal calculateQuantization(int n) {
        List<Double> values = new ArrayList<>();
        for (int i = 0; i < sampleDataSet.length; i++) {
            values.add(sampleDataSet[i][1]);
        }
        List<Double> rangeValues = new ArrayList<>();
        Double min = values.stream().min(comparing(Double::valueOf)).get();
        Double max = values.stream().max(comparing(Double::valueOf)).get();
        Double range = (max - min) / Math.pow(2, n);
        for (double i = min; i < max; i += range) {
            rangeValues.add(i);
        }
        quantizedDataSet = new double[sampleDataSet.length][2];
        for (int i = 0; i < sampleDataSet.length; i++) {
            quantizedDataSet[i][0] = sampleDataSet[i][0];
            double smallDistance = max;
            double value = 0.0;
            for (Double d : rangeValues) {
                double distance = Math.abs(d - sampleDataSet[i][1]);
                if (distance < smallDistance) {
                    smallDistance = distance;
                    value = d;
                }
            }
            quantizedDataSet[i][1] = value;
        }
        return new Signal(quantizedDataSet, sampleDataSet.length);
    }
}
