package cps1.Model.Graphs;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

/**
 * Created by madekko on 14.03.2018.
 */
public class HistogramCreator extends ApplicationFrame {

    /**
     * Method for creating histogram of signal
     */
    public HistogramCreator(String applicationTitle, String chartTitle, double[][] data, int numberOfBins) {
        super(applicationTitle);
        JFreeChart histogram = ChartFactory.createHistogram(
                chartTitle, "t", "x(t)", createDataset(data, numberOfBins), PlotOrientation.VERTICAL, false, true, false
        );


        ChartPanel chartPanel = new ChartPanel(histogram);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

    /**
     * Method for creating dataset for histogram
     */
    private HistogramDataset createDataset(double[][] data, int numberOfBins) {

        double [] d = new double[data.length];
        for (int i =0; i< data.length; i++)
        {
            d[i] = data[i][1];
        }
        HistogramDataset histogramDataset = new HistogramDataset();
        histogramDataset.setType(HistogramType.FREQUENCY);
        histogramDataset.addSeries("Hist",  d, numberOfBins);
        return histogramDataset;
    }

}
