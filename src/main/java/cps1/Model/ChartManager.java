package cps1.Model;

import cps1.Model.Operations.Transformation.CosinusTransformation;
import cps1.Model.Operations.Transformation.FastCosinusTransformation;
import cps1.Model.Operations.Transformation.FastFourierTransformation;
import cps1.Model.Operations.Transformation.FourierDiscreteTransformation;
import cps1.Model.Signals.Signal;
import org.apache.commons.math3.complex.Complex;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.ArrayList;

public class ChartManager extends ApplicationFrame {
    private Signal signal;
    public ChartManager(String title) {
        super(title);
        this.signal = getDefaultSignal();
        final ChartPanel panel = new ChartPanel(createChart());
        panel.setPreferredSize(new java.awt.Dimension(900, 900));
        setContentPane(panel);
    }

    public JFreeChart createChart() {
        final CategoryAxis domainAxis = new CategoryAxis("Sections");
        final CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);

        FourierDiscreteTransformation fourierDiscreteTransformation = new FourierDiscreteTransformation(-1);
        Signal afterDiscreteFourier = fourierDiscreteTransformation.transformationBody(this.signal);

        FastFourierTransformation fastFourierTransformation = new FastFourierTransformation(-1);
        Signal afterFastFourier = fastFourierTransformation.transformationBody(this.signal);

        CosinusTransformation cosinusTransformation = new CosinusTransformation(-1);
        Signal afterCosinus = cosinusTransformation.transformationBody(this.signal);

        FastCosinusTransformation fastCosinusTransformation = new FastCosinusTransformation(-1);
        Signal afterFastCosinus = fastCosinusTransformation.transformationBody(this.signal);

        addToPlot(plot, createRealNumbersSet(this.signal, ""));


//        addToPlot(plot, createRealNumbersSet(afterDiscreteFourier, "Real"));
//        addToPlot(plot, createImaginaryNumbersSet(afterDiscreteFourier, "Imaginary"));
//
        addToPlot(plot, createRealNumbersSet(afterFastFourier, "Real"));
        addToPlot(plot, createImaginaryNumbersSet(afterFastFourier, "Imaginary"));



//        addToPlot(plot, createRealNumbersSet(afterCosinus, "Real"));
//        addToPlot(plot, createImaginaryNumbersSet(afterCosinus, "Imaginary"));

//        addToPlot(plot, createRealNumbersSet(afterFastCosinus, "Real"));
//        addToPlot(plot, createImaginaryNumbersSet(afterFastCosinus, "Imaginary"));

//        Signal restoreCos = cosinusTransformation.restoreSignal(afterCosinus);
//        addToPlot(plot, createRealNumbersSet(restoreCos, "Real"));

//        Signal restoreDiscreteFourier = fourierDiscreteTransformation.restoreSignal(afterDiscreteFourier);
//        addToPlot(plot, createRealNumbersSet(restoreDiscreteFourier, ""));

        Signal restoreFastFourier = fastFourierTransformation.restoreSignal(afterFastFourier);
        addToPlot(plot, createRealNumbersSet(restoreFastFourier, ""));


        final JFreeChart result = new JFreeChart(
                "Signal",
                new Font("Monaco", Font.BOLD, 12),
                plot,
                true
        );
        return result;
    }

    private void addToPlot(CombinedDomainCategoryPlot plot, CategoryDataset real2) {
        CategoryDataset real = real2;
        final NumberAxis rangeAxis9 = new NumberAxis("");
        //rangeAxis9.setStandardTickUnits(NumberAxis.createStandardTickUnits());
        final LineAndShapeRenderer renderer9 = new LineAndShapeRenderer();
        renderer9.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
        renderer9.setSeriesPaint(0, Color.RED);
        renderer9.setSeriesStroke(0, new BasicStroke(1.0f));
        renderer9.setDefaultLinesVisible(true);
        renderer9.setSeriesShapesVisible(0, false);
        renderer9.setSeriesShape(0, new Rectangle(1, 1));
        final CategoryPlot subplot9 = new CategoryPlot(real, null, rangeAxis9, renderer9);
        subplot9.setDomainGridlinesVisible(true);
        plot.add(subplot9, 1);
    }

    private static CategoryDataset createRealNumbersSet(Signal signal, String name) {

        final DefaultCategoryDataset result = new DefaultCategoryDataset();

        for(int i = 0 ; i < signal.getImaginary().length ; i++){
            result.addValue(signal.getImaginary()[i].getReal(), name, String.valueOf( i));
        }

        return result;
    }

    private static CategoryDataset createImaginaryNumbersSet(Signal signal, String name) {

        final DefaultCategoryDataset result = new DefaultCategoryDataset();

        for(int i = 0 ; i < signal.getImaginary().length ; i++){
            result.addValue(signal.getImaginary()[i].getImaginary(), name, String.valueOf( i ));
        }

        return result;
    }

    private Signal getDefaultSignal(){
        int startTime = 0;
        int endTime = 10;
        int frequency = 16;

        int range = endTime-startTime;

        int n2 = (int) (frequency * range);
        double step = (double)(range - startTime) / n2;

        ArrayList<Double> values = new ArrayList<Double>();
        Complex[] complexValues = new Complex[n2];

        double[][] sampleDataSet = new double[n2+1][2];
        for (int i = 1; i <= n2; i++) {
            double t = step * i;

            double x1 = 2 * Math.sin( (2 * Math.PI * t / 2) + (Math.PI / 2) );
            double x2 = 5 * Math.sin( (2 * Math.PI * t / 2 ) + (Math.PI / 2) );
            double value =  x1 + x2;

            complexValues[i - 1] = new Complex(value);
            values.add(value);
            sampleDataSet[i - 1][0] = t;
            sampleDataSet[i - 1][1] = value;
        }

        Signal s = new Signal(sampleDataSet, sampleDataSet.length);
        s.setFrequency(frequency);
        s.settMax(endTime);
        s.settMin(startTime);
        s.setImaginary(complexValues);
        s.createPlot();
        return s;
    }
}
