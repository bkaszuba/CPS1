package cps1.Model.Graphs;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class XYLineChartCreator extends ApplicationFrame {

    public XYLineChartCreator(String applicationTitle, String chartTitle, double[][] data) {
        super(applicationTitle);
        JFreeChart xyLineChart = ChartFactory.createXYLineChart(
                chartTitle, "t", "x(t)", createDataset(data), PlotOrientation.VERTICAL, false, true, false
        );
        ChartPanel chartPanel = new ChartPanel(xyLineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

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
