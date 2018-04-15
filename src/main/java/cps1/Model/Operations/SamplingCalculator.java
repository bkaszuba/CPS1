package cps1.Model.Operations;

import cps1.Model.Graphs.MultiplePlotCreator;
import cps1.Model.Signals.Signal;
import org.jfree.ui.RefineryUtilities;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;

public class SamplingCalculator {

    private Signal signal;
    private double sampleDataSet[][];
    private int sampleTime;
    private double quantizedDataSet[][];
    private double reconstractionDataSet[][];

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
        for (double i = min; i <= max; i += range) {
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

    public Signal calculateReconstraction() {
        double range = Math.abs(quantizedDataSet[quantizedDataSet.length - 1][0] - quantizedDataSet[0][0]);
        reconstractionDataSet = new double[(int) (range / 0.01)][2];
        reconstractionDataSet[0][1] = quantizedDataSet[0][1];
        reconstractionDataSet[reconstractionDataSet.length - 1][1] = quantizedDataSet[quantizedDataSet.length - 1][1];
        double value = quantizedDataSet[0][0];
        for (int i = 0; i < reconstractionDataSet.length; i++) {
            reconstractionDataSet[i][0] = value;
            value = value + 0.01;
        }
        for (int i = 0; i < quantizedDataSet.length; i++) {
            for (int j = 0; j < reconstractionDataSet.length; j++) {
                if (reconstractionDataSet[j][0] == quantizedDataSet[i][0]) {
                    reconstractionDataSet[j][1] = quantizedDataSet[i][1];
                }
            }
        }
        double temp = quantizedDataSet[0][1];
        for (int j = 0; j < reconstractionDataSet.length - 1; j++) {
            if (reconstractionDataSet[j + 1][1] != temp && reconstractionDataSet[j + 1][1] != 0.0) {
                temp = reconstractionDataSet[j + 1][1];
                reconstractionDataSet[j][1] = temp;
            } else {
                reconstractionDataSet[j][1] = temp;
            }
        }
        return new Signal(reconstractionDataSet, sampleDataSet.length);
    }
    public void createPlot() {
        MultiplePlotCreator multiplePlotCreator = new MultiplePlotCreator("", "", reconstractionDataSet, signal.dataSet);
        multiplePlotCreator.pack();
        RefineryUtilities.centerFrameOnScreen(multiplePlotCreator);
        multiplePlotCreator.setVisible(true);
    }
}
