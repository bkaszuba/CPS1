package cps1.Model;

import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;
/**
 * Base signal Class
 */
public class Signal {
    protected int tMin;
    protected int tMax;
    protected double devide;
    protected int amplitude;
    protected int period;
    protected int fillingRate;
    protected double dataSet[][];
    protected int arraySize;

    /**
     * Base class constructor
     * @param minT  - starting x value
     * @param maxT  - ending x value
     * @param customDevide - step for how much we want to move on x-axis ex. 0.1 means 1 .. 1.1 .. 1.2 etc
     * @param amplit - amplitude ( max Y value)
     */
    public Signal(int minT, int maxT, double customDevide, int amplit){
        tMin = minT;
        tMax = maxT;
        devide = customDevide;
        amplitude = amplit;
        calculateTime();
    }

    /**
     * Method for calculating values on X-Axis
     */
    public void calculateTime(){
        int range = tMax - tMin;
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
        JavaPlot p = new JavaPlot();
        PlotStyle myPlotStyle = new PlotStyle();
        myPlotStyle.setStyle(Style.LINES);
        DataSetPlot s = new DataSetPlot(dataSet);
        myPlotStyle.setLineWidth(1);
        s.setPlotStyle(myPlotStyle);
        p.addPlot(s);
        p.newGraph();
        p.plot();
    }

    /**
     * Method for showing data in dataSet ( all values in x and y axis)
     */
    public void showDataSet(){
        for(int i=0; i < arraySize; i++){
            for(int j=0; j < 2; j++){
                System.out.print(dataSet[i][j] + " ");
            }
            System.out.println();
        }
    }
}
