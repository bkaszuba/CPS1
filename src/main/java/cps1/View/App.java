package cps1.View;

import cps1.Model.ChartManager;
import cps1.Model.Operations.*;
import cps1.Model.Operations.Transformation.FastFourierTransformation;
import cps1.Model.Operations.Transformation.FourierDiscreteTransformation;
import cps1.Model.Operations.Windows.BlackmanWindow;
import cps1.Model.Operations.Windows.HammingWindow;
import cps1.Model.Operations.Windows.HanningWindow;
import cps1.Model.Operations.Windows.RectangularWindow;
import cps1.Model.Signals.*;
import org.apache.commons.math3.complex.Complex;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    static Signal firstSignal;
    static Signal secondSignal;

    public static void main(String[] args) {
        ChartManager chartManager = new ChartManager("Signal");
        chartManager.pack( );
        RefineryUtilities.centerFrameOnScreen(chartManager);
        chartManager.setVisible( true );



//        FourierDiscreteTransformation fourierDiscreteTransformation = new FourierDiscreteTransformation(-1);
//        Signal afterTran = fourierDiscreteTransformation.transformationBody(s);
//        Complex com[] = afterTran.getImaginary();
//        double[][] data = new double[com.length][2];
//        for(int j =0; j < com.length; j++) {
//            data[j][0] = j;
//            data[j][1] = (int)com[j].getReal();
//        }
//        Signal x = new Signal(data, data.length);
//        x.createPlot();
//
//        FastFourierTransformation fastFourierTransformation = new FastFourierTransformation(-1);
//        Signal afterTran2 = fastFourierTransformation.transformationBody(s);
//        Complex com2[] = afterTran2.getImaginary();
//        double[][] data2 = new double[com2.length][2];
//        for(int j =0; j < com.length; j++) {
//            data2[j][0] = j;
//            data2[j][1] = (int)com2[j].getReal();
//        }
//        Signal y = new Signal(data2, data2.length);
//        y.createPlot();

    }

    private static void calculateTask3() {
        //Mala zmiana: 200 - samplingFrequency a 2 - signalFrequency
        SinSignal sinSignal1 = new SinSignal(0, 4, 200, 1, 2);
        SinSignal sinSignal2 = new SinSignal(0, 4, 200, 1, 20);
        Operation operation = new Operation(sinSignal1, sinSignal2);
        operation.add();
        ConvolutionCalculator convolutionCalculator = new ConvolutionCalculator();
        Signal signal =  convolutionCalculator.calculateSignalConvolution(sinSignal1, sinSignal1);
        signal.createPlot();
        System.out.println( sinSignal1.toString());
        System.out.println( sinSignal2.toString());
        Signal sum = operation.result;
        sum.createPlot();
        FilterCalculator filterCalculator = new FilterCalculator(31, 4, sinSignal1.getFrequency(), FilterCalculator.FilterType.Lowpass, new RectangularWindow(31));
        double[] filterValue = filterCalculator.getFilter();
        Signal filteredSum = convolutionCalculator.calculateFilterConvolution(filterValue, sum);
        filteredSum.createPlot();
        Signal test = convolutionCalculator.calculateSignalCorelation(sinSignal1, sinSignal1);
        test.createPlot();
        SinSignal sinSignal3 = new SinSignal(0, 4, 100, 2, 1);
        sinSignal3.createPlot();
        double velocity = 100.0;
        double distance = 50.0;
        double time = distance/velocity;
        Signal signal4 = convolutionCalculator.shiftSignalValues(sinSignal3, time);
        Signal corelationSignal = convolutionCalculator.calculateSignalCorelation(sinSignal3, signal4);
        convolutionCalculator.calculateDistance(velocity, corelationSignal);
        signal.createPlot();
    }

    public static void calculateTask1() {
        Signal firstSignal1;
        Signal secondSignal2;
        firstSignal1 = signalSwitch(showSignals(), firstSignal);
        secondSignal2 = signalSwitch(showSignals(), secondSignal);
        operations(firstSignal1, secondSignal2);
        operations(firstSignal1, secondSignal2);
        operations(firstSignal1, secondSignal2);
        operations(firstSignal1, secondSignal2);
    }

    public static void calculateTask2() {
        SinSignal sinSignal = new SinSignal(0, 4, 100, 2, 2);
        sinSignal.createPlot();
        SamplingCalculator samplingCalculator = new SamplingCalculator(sinSignal, 3);
        Signal sampledSignal = samplingCalculator.createSampleSignal();
        sampledSignal.createScatterPlot();
        Signal quantizedSignal = samplingCalculator.calculateQuantization(4);
        quantizedSignal.createScatterPlot();
        Signal reconstuctionSignal = samplingCalculator.calculateReconstraction(0, 4, 100);
        reconstuctionSignal.createPlot();
        samplingCalculator.createPlot();
        ParametersCalculator parametersCalculator = new ParametersCalculator(sinSignal);
        System.out.println("MSE: " + parametersCalculator.calculateMSE(sinSignal, reconstuctionSignal));
        System.out.println("MD: " + parametersCalculator.calculateMD(sinSignal, reconstuctionSignal));
        System.out.println("SNR: " + parametersCalculator.calculateSNR(sinSignal, reconstuctionSignal));
        System.out.println("PSNR: " + parametersCalculator.calculatePSNR(sinSignal, reconstuctionSignal));
    }

    public static int showSignals() {
        int numberOfSignal;
        System.out.println("Menu");
        System.out.println("Wybierz sygnał");
        System.out.println("1. Szum o rozkładzie jednostajnym ");
        System.out.println("2. Szum gaussowski ");
        System.out.println("3. Sygnał sinusoidalny");
        System.out.println("4. Sygnał sinusoidalny wyprostowany jednopołówkowo");
        System.out.println("5. Sygnał sinusoidalny wyprostowany dwupołówkowo");
        System.out.println("6. Sygnał prostokątny");
        System.out.println("7. Sygnał prostokątny symetryczny");
        System.out.println("8. Sygnał trójkątny");
        System.out.println("9. Skok jednostkowy ");
        System.out.println("10. Impuls jednostkowy ");
        System.out.println("11. Szum impulsowy ");
        System.out.print("Wybrany sygnał: ");
        Scanner S = new Scanner(System.in);
        numberOfSignal = S.nextInt();
        return numberOfSignal;
    }

    public static int showSignalOptions() {
        int numberOfOption;
        System.out.println("Menu");
        System.out.println("1. Wczytaj wartości z pliku");
        System.out.println("2. Wczytaj parametry z pliku");
        Scanner S = new Scanner(System.in);
        numberOfOption = S.nextInt();
        return numberOfOption;
    }

    public static int showSaveSignalOptions() {
        int numberOfOption;
        System.out.println("Menu");
        System.out.println("1. Zapisz parametry do pliku");
        System.out.println("2. Zapisz wartości do pliku");
        Scanner S = new Scanner(System.in);
        numberOfOption = S.nextInt();
        return numberOfOption;
    }

    public static int showOperations() {
        int numberOfOption;
        System.out.println("Menu");
        System.out.println("1. Dodaj sygnały");
        System.out.println("2. Odejmij sygnały");
        System.out.println("3. Pomnóż sygnały");
        System.out.println("4. Podziel sygnały");
        Scanner S = new Scanner(System.in);
        numberOfOption = S.nextInt();
        return numberOfOption;
    }

    public static void operations(Signal firstSignal, Signal secondSignal) {
        Operation operation = new Operation(firstSignal, secondSignal);
        switch (showOperations()) {
            case 1:
                operation.add();
                operation.result.createPlot();
                operation.result.createHistogram();
                break;
            case 2:
                operation.subtract();
                operation.result.createPlot();
                operation.result.createHistogram();
                break;
            case 3:
                operation.multiply();
                operation.result.createPlot();
                operation.result.createHistogram();
                break;
            case 4:
                operation.divide();
                operation.result.createPlot();
                operation.result.createHistogram();
                break;
            default:
                System.out.println("Nieprzewidziana sytuacja ");
        }
        System.out.println(operation.result.gettMax());
        calculateParams(operation.result);
        saveOptions(operation.result, "operations.txt");
    }


    public static void saveOptions(Signal signal, String fileName) {
        switch (showSaveSignalOptions()) {
            case 1:
                signal.saveParametersToFile("Results/params" + fileName);
                break;
            case 2:
                signal.saveValuesToFile("Results/values" + fileName);
                break;
            default:
                System.out.println("Nieprzewidziana sytuacja ");
        }
    }

    public static Signal signalSwitch(int x, Signal firstSignal) {
        switch (x) {
            case 1:

                switch (showSignalOptions()) {
                    case 1:
                        firstSignal = new SteadyNoise("resources/ValuesFile/signal1.txt", Signal.Type.Values);
                        break;
                    case 2:
                        firstSignal = new SteadyNoise("resources/ParamsFile/params1.txt", Signal.Type.Params);
                        break;
                    default:
                        System.out.println("Nieprzewidziana sytuacja");
                }
                firstSignal.createPlot();
                firstSignal.createHistogram();
                calculateParams(firstSignal);
                saveOptions(firstSignal, "signal1.txt");
                break;

            case 2:
                switch (showSignalOptions()) {
                    case 1:
                        firstSignal = new GaussianNoise("resources/ValuesFile/signal2.txt", Signal.Type.Values);
                        break;
                    case 2:
                        firstSignal = new GaussianNoise("resources/ParamsFile/params2.txt", Signal.Type.Params);
                        break;
                    default:
                        System.out.println("Nieprzewidziana sytuacja");
                }
                firstSignal.createPlot();
                firstSignal.createHistogram();
                calculateParams(firstSignal);
                saveOptions(firstSignal, "signal2.txt");
                break;
            case 3:
                switch (showSignalOptions()) {
                    case 1:
                        firstSignal = new SinSignal("resources/ValuesFile/signal3.txt", Signal.Type.Values);
                        break;
                    case 2:
                        firstSignal = new SinSignal("resources/ParamsFile/params3.txt", Signal.Type.Params);
                        break;
                    default:
                        System.out.println("Nieprzewidziana sytuacja");
                }
                firstSignal.createPlot();
                firstSignal.createHistogram();
                calculateParams(firstSignal);
                saveOptions(firstSignal, "signal3.txt");
                break;

            case 4:
                switch (showSignalOptions()) {
                    case 1:
                        firstSignal = new SinSingleStraightSignal("resources/ValuesFile/signal4.txt", Signal.Type.Values);
                        break;
                    case 2:
                        firstSignal = new SinSingleStraightSignal("resources/ParamsFile/params4.txt", Signal.Type.Params);
                        break;
                    default:
                        System.out.println("Nieprzewidziana sytuacja");
                }
                firstSignal.createPlot();
                firstSignal.createHistogram();
                calculateParams(firstSignal);
                saveOptions(firstSignal, "signal4.txt");

                break;
            case 5:
                switch (showSignalOptions()) {
                    case 1:
                        firstSignal = new SinDoubleStraightSignal("resources/ValuesFile/signal5.txt", Signal.Type.Values);
                        break;
                    case 2:
                        firstSignal = new SinDoubleStraightSignal("resources/ParamsFile/params5.txt", Signal.Type.Params);
                        break;

                    default:
                        System.out.println("Nieprzewidziana sytuacja");
                }
                firstSignal.createPlot();
                firstSignal.createHistogram();
                calculateParams(firstSignal);
                saveOptions(firstSignal, "signal5.txt");

                break;

            case 6:
                switch (showSignalOptions()) {
                    case 1:
                        firstSignal = new RectangularSignal("resources/ValuesFile/signal6.txt", Signal.Type.Values);
                        break;
                    case 2:
                        firstSignal = new RectangularSignal("resources/ParamsFile/params6.txt", Signal.Type.Params);
                        break;

                    default:
                        System.out.println("Nieprzewidziana sytuacja");
                }
                firstSignal.createPlot();
                firstSignal.createHistogram();
                calculateParams(firstSignal);
                saveOptions(firstSignal, "signal6.txt");

                break;
            case 7:
                switch (showSignalOptions()) {
                    case 1:
                        firstSignal = new RectangularSymmetricSignal("resources/ValuesFile/signal7.txt", Signal.Type.Values);
                        break;
                    case 2:
                        firstSignal = new RectangularSymmetricSignal("resources/ParamsFile/params7.txt", Signal.Type.Params);
                        break;

                    default:
                        System.out.println("Nieprzewidziana sytuacja");
                }
                firstSignal.createPlot();
                firstSignal.createHistogram();
                calculateParams(firstSignal);
                saveOptions(firstSignal, "signal7.txt");

                break;

            case 8:
                switch (showSignalOptions()) {
                    case 1:
                        firstSignal = new TriangularSignal("resources/ValuesFile/signal8.txt", Signal.Type.Values);
                        break;
                    case 2:
                        firstSignal = new TriangularSignal("resources/ParamsFile/params8.txt", Signal.Type.Params);
                        break;

                    default:
                        System.out.println("Nieprzewidziana sytuacja");
                }
                firstSignal.createPlot();
                firstSignal.createHistogram();
                calculateParams(firstSignal);
                saveOptions(firstSignal, "signal8.txt");

                break;
            case 9:
                switch (showSignalOptions()) {
                    case 1:
                        firstSignal = new UnitStep("resources/ValuesFile/signal9.txt", Signal.Type.Values);
                        break;
                    case 2:
                        firstSignal = new UnitStep("resources/ParamsFile/params9.txt", Signal.Type.Params);
                        break;

                    default:
                        System.out.println("Nieprzewidziana sytuacja");
                }
                firstSignal.createPlot();
                firstSignal.createHistogram();
                calculateParams(firstSignal);
                saveOptions(firstSignal, "signal9.txt");

                break;

            case 10:
                switch (showSignalOptions()) {
                    case 1:
                        firstSignal = new UnitImpulse("resources/ValuesFile/signal10.txt", Signal.Type.Values);
                        break;
                    case 2:
                        firstSignal = new UnitImpulse("resources/ParamsFile/params10.txt", Signal.Type.Params);
                        break;

                    default:
                        System.out.println("Nieprzewidziana sytuacja");
                }
                firstSignal.createScatterPlot();
                firstSignal.createHistogram();
                calculateParams(firstSignal);
                saveOptions(firstSignal, "signal10.txt");

                break;
            case 11:
                switch (showSignalOptions()) {
                    case 1:
                        firstSignal = new ImpulseNoise("resources/ValuesFile/signal11.txt", Signal.Type.Values);
                        break;
                    case 2:
                        firstSignal = new ImpulseNoise("resources/ParamsFile/params11.txt", Signal.Type.Params);
                        break;

                    default:
                        System.out.println("Nieprzewidziana sytuacja");
                }
                firstSignal.createScatterPlot();
                firstSignal.createHistogram();
                calculateParams(firstSignal);
                saveOptions(firstSignal, "signal11.txt");

                break;
            default:
                System.out.println("nieprzewidziana sytuacja");
        }
        return firstSignal;
    }

    public static void calculateParams(Signal signal) {
        ParametersCalculator parametersCalculator = new ParametersCalculator(signal);
        parametersCalculator.calculateAverage();
        parametersCalculator.calculateAbsoluteAverage();
        parametersCalculator.calculatePower();
        parametersCalculator.calculateVariance();
        parametersCalculator.calculateRMS();
    }



}
