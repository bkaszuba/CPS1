package cps1.Model.Graphs;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.ui.ApplicationFrame;

public class HistogramCreator extends ApplicationFrame {
    public HistogramCreator(String applicationTitle, String chartTitle, double[][] data, int numberOfBins) {
        super(applicationTitle);
        JFreeChart histogram = ChartFactory.createHistogram(
                chartTitle, "", "", createDataset(data, numberOfBins), PlotOrientation.VERTICAL, false, true, false
        );
        ChartPanel chartPanel = new ChartPanel(histogram);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

    private HistogramDataset createDataset(double[][] data, int numberOfBins) {

        double[] d = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            d[i] = data[i][1];
        }
        HistogramDataset histogramDataset = new HistogramDataset();
        histogramDataset.setType(HistogramType.FREQUENCY);
        histogramDataset.addSeries("Hist", d, numberOfBins);
        return histogramDataset;
    }

}
