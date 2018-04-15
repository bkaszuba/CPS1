package cps1.Model.Graphs;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class MultiplePlotCreator extends ApplicationFrame {
    public MultiplePlotCreator(String applicationTitle, String chartTitle, double[][] firstData, double[][] secondData) {
        super(applicationTitle);
        JFreeChart xyLineChart = ChartFactory
                .createXYLineChart(
                        chartTitle,
                        "t",
                        "x(t)",
                        createDataset(firstData),
                        PlotOrientation.VERTICAL,
                        false,
                        true,
                        false
                );
//        TODO Problem with width of line with 2 different colors
//        BasicStroke stroke = new BasicStroke(3);
//        XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
//        renderer1.setSeriesPaint(0, Color.BLUE);
//        renderer1.setSeriesStroke(0, stroke);
//        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
//        renderer2.setSeriesPaint(0, Color.GREEN);
//        renderer2.setSeriesStroke(0, stroke);
//        plot.setRenderer(0, renderer1);
//        plot.setRenderer(1, renderer2);
        XYPlot plot = xyLineChart.getXYPlot();
        plot.setDataset(1, createDataset(secondData));
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
