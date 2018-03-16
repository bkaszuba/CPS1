package cps1.Model.Graphs;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by madekko on 14.03.2018.
 */
public class XYLineChartCreator extends ApplicationFrame {

    /**
     * Method for creating XY graph of signal
     */
    public XYLineChartCreator(String applicationTitle, String chartTitle, double[][] data) {
        super(applicationTitle);
        JFreeChart xyLineChart = ChartFactory.createXYLineChart(
                chartTitle, "t", "x(t)", createDataset(data), PlotOrientation.VERTICAL, false, true, false
        );


        ChartPanel chartPanel = new ChartPanel(xyLineChart);
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
            xySeries.add(data[i][0],data[i][1]);
        }
        dataset.addSeries(xySeries);
        return dataset;
    }


}