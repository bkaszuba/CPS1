package cps1.Model;

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

    protected double dataSet[][];
    protected int arraySize;



    public Signal(double data[][]){
        dataSet = data;
    }
    /**
     * Base class constructor
     * @param _tMin  - starting x value
     * @param _tMax  - ending x value
     * @param _devide - step for how much we want to move on x-axis ex. 0.1 means 1 .. 1.1 .. 1.2 etc
     * @param _amplitude - amplitude ( max Y value)
     * @param _period - period of function
     * @param _fillingRate - rate of filling
     */
    public Signal(int _tMin, int _tMax, double _devide, int _amplitude,double _period, double _fillingRate, double stept){
        tMin = _tMin;
        tMax = _tMax;
        devide = _devide;
        amplitude = _amplitude;
        period = _period;
        fillingRate = _fillingRate;
        stepTime = stept;
        calculateTime();
    }
    /**
     * Base class constructor
     * @param _tMin  - starting x value
     * @param _tMax  - ending x value
     * @param _devide - step for how much we want to move on x-axis ex. 0.1 means 1 .. 1.1 .. 1.2 etc
     * @param _amplitude - amplitude ( max Y value)
     * @param _period - period of function
     */
    public Signal(int _tMin, int _tMax, double _devide, int _amplitude, double _period){
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
    public void calculateTime(){
        int range = Math.abs( tMax - tMin);
        arraySize = (int) (range/devide);
        this.dataSet = new double [arraySize][arraySize];
        double value = tMin;
        for(int i=tMin; i<arraySize; i++){
            dataSet[i][0] = value;
            value = value + devide;
        }
    }

    /**
     * Method for creating 2d graph
     */
    public void createPlot() {

        XYLineChartCreator XYLineChartCreator = new XYLineChartCreator("", "", dataSet);
        XYLineChartCreator.pack( );
        RefineryUtilities.centerFrameOnScreen(XYLineChartCreator);
        XYLineChartCreator.setVisible( true );
    }

    /**
     * Method for showing data in dataSet ( all values in x and y axis)
     */
    public void showDataSet(){
        for(int i=0; i < dataSet.length; i++){
            for(int j=0; j < 2; j++){
                System.out.print(dataSet[i][j] + " ");
            }
            System.out.println();
        }
    }
    /**
     * Method for saving data in file ( all values in x and y axis)
     */
    public void saveToFile(){

        try (PrintWriter out = new PrintWriter("signal.txt")) {
            for(int i=0; i < arraySize; i++){
                for(int j=0; j < 2; j++){
                    out.printf("%.2f",dataSet[i][j]);
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
    public void readFromFile() throws IOException {

        Scanner scanner = new Scanner("signal.txt");
        while (scanner.hasNext()) {
            if (scanner.hasNextDouble()) {
                for (int i = 0; i < arraySize; i++) {
                    for (int j = 0; j < 2; j++) {
                        dataSet[i][j] = scanner.nextDouble();
                        System.out.println(scanner.nextDouble());
                    }
                }
            } else {
                scanner.next();
            }
        }

    }

}
