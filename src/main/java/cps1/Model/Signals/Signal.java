package cps1.Model.Signals;

import cps1.Model.Graphs.HistogramCreator;
import cps1.Model.Graphs.XYLineChartCreator;
import org.jfree.ui.RefineryUtilities;

import java.io.*;
import java.util.Scanner;


/**
 * Base signal Class
 */
public class Signal {
    protected int tMin;
    protected int tMax;
    protected double devide = 1;
    protected int amplitude;
    protected double period;
    protected double fillingRate;
    protected double stepTime;

    public double dataSet[][];
    protected int arraySize;

    protected double average;
    protected double absoluteAverage;
    protected double powerAverage;
    protected double variance;
    protected double rms;

    /**
     * Base class constructor
     *
     * @param data - data with X nad Y values
     */
    public Signal(double data[][]) {
        dataSet = data;
    }


    /**
     * Base class constructor
     *
     * @param _path - path to file with dataSet
     */
    public Signal(String _path) {
        try {
            readFromFile(_path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Base class constructor
     *
     * @param _tMin        - starting x value
     * @param _tMax        - ending x value
     * @param _devide      - step for how much we want to move on x-axis ex. 0.1 means 1 .. 1.1 .. 1.2 etc
     * @param _amplitude   - amplitude ( max Y value)
     * @param _period      - period of function
     * @param _fillingRate - rate of filling
     * @param _stept       - time of step
     */
    public Signal(int _tMin, int _tMax, double _devide, int _amplitude, double _period, double _fillingRate, double _stept) {
        tMin = _tMin;
        tMax = _tMax;
        devide = _devide;
        amplitude = _amplitude;
        period = _period;
        fillingRate = _fillingRate;
        stepTime = _stept;
        calculateTime();
    }

    /**
     * Base class constructor
     *
     * @param _tMin      - starting x value
     * @param _tMax      - ending x value
     * @param _devide    - step for how much we want to move on x-axis ex. 0.1 means 1 .. 1.1 .. 1.2 etc
     * @param _amplitude - amplitude ( max Y value)
     * @param _period    - period of function
     */
    public Signal(int _tMin, int _tMax, double _devide, int _amplitude, double _period) {
        tMin = _tMin;
        tMax = _tMax;
        devide = _devide;
        amplitude = _amplitude;
        period = _period;
        calculateTime();
    }


    /**
     * Method for calculating values on X-Axis
     */
    public void calculateTime() {
        int range = Math.abs(tMax - tMin);
        arraySize = (int) (range / devide);
        this.dataSet = new double[arraySize][2];
        double value = tMin;
        for (int i = 0; i < arraySize; i++) {
            dataSet[i][0] = value;
            value = value + devide;
        }
    }

    /**
     * Method for creating 2d graph
     */
    public void createPlot() {

        XYLineChartCreator XYLineChartCreator = new XYLineChartCreator("", "", dataSet);
        XYLineChartCreator.pack();
        RefineryUtilities.centerFrameOnScreen(XYLineChartCreator);
        XYLineChartCreator.setVisible(true);
    }

    /**
     * Method for creating histogram graph
     */
    public void createHistogram() {

        HistogramCreator histogramCreator = new HistogramCreator("", "", dataSet, 10);
        histogramCreator.pack();
        RefineryUtilities.centerFrameOnScreen(histogramCreator);
        histogramCreator.setVisible(true);
    }

    /**
     * Method for showing data in dataSet ( all values in x and y axis)
     */
    public void showDataSet() {
        for (int i = 0; i < dataSet.length; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(dataSet[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Method for saving data in file ( all values in x and y axis)
     */
    public void saveToFile() {

        try (PrintWriter out = new PrintWriter("signal.txt")) {
            for (int i = 0; i < arraySize; i++) {
                for (int j = 0; j < 2; j++) {
//                    out.printf("%.2f", dataSet[i][j]);
                    out.print(dataSet[i][j]);

                    out.print(" ");
                }
                out.println();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for reading data from file to dataSet ( all values in x and y axis)
     */
    public void readFromFile(String path) throws IOException {

        Scanner scanner;
        File file = new File(path);

        BufferedReader reader = new BufferedReader(new FileReader(path));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();

        dataSet = new double[lines][2];
        arraySize = lines;

        try {
            scanner = new Scanner(file);

            while (scanner.hasNext()) {
                for (int i = 0; i < arraySize; i++) {
                    for (int j = 0; j < 2; j++) {
                        dataSet[i][j] = Double.parseDouble(scanner.next().replaceAll(",", "."));
                    }
                }
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Useful getters and setters
     */
    public double getRms() {
        return rms;
    }

    public void setRms(double rms) {
        this.rms = rms;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    public double getPowerAverage() {
        return powerAverage;
    }

    public void setPowerAverage(double powerAverage) {
        this.powerAverage = powerAverage;
    }

    public double getAbsoluteAverage() {
        return absoluteAverage;
    }

    public void setAbsoluteAverage(double absoluteAverage) {
        this.absoluteAverage = absoluteAverage;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getAverage() {
        return average;
    }

    public int gettMin() {
        return tMin;
    }

    public int gettMax() {
        return tMax;
    }

    public int getArraySize() {
        return arraySize;
    }


}
