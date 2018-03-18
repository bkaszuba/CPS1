package cps1.Model.Graphs;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

/**
 * Created by madekko on 18.03.2018.
 */
public class ScatterPlotCreator extends ApplicationFrame {

    /**
     * Method for creating XY graph of signal
     */
    public ScatterPlotCreator(String applicationTitle, String chartTitle, double[][] data) {
        super(applicationTitle);
        JFreeChart scatterPlot = ChartFactory.createScatterPlot(
                chartTitle, "t", "x(t)", createDataset(data), PlotOrientation.VERTICAL, false, true, false
        );


        ChartPanel chartPanel = new ChartPanel(scatterPlot);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

    /**
     * Method for creating dataset for graph
     */
    private XYDataset createDataset(double[][] data) {

        final XYSeriesCollection dataset =
                new XYSeriesCollection();
        final XYSeries xySeries = new XYSeries("Signal");

        for (int i = 0; i < data.length; i++) {
            xySeries.add(data[i][0], data[i][1]);
        }
        dataset.addSeries(xySeries);
        return dataset;
    }

}
