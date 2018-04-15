package cps1.View;

import cps1.Model.Operations.Operation;
import cps1.Model.Operations.ParametersCalculator;
import cps1.Model.Operations.SamplingCalculator;
import cps1.Model.Signals.*;

import java.io.IOException;
import java.util.Scanner;

public class App {


    static Signal firstSignal;
    static Signal secondSignal;

    public static void main(String[] args) throws IOException {
        //Only for testing
        SinSignal sinSignal = new SinSignal(0, 4, 0.01, 2, 2);
        SamplingCalculator samplingCalculator = new SamplingCalculator(sinSignal, 10);
        Signal sampledSignal = samplingCalculator.createSampleSignal();
//        sampledSignal.createScatterPlot();
        Signal quantizedSignal = samplingCalculator.calculateQuantization(2);
//        quantizedSignal.createScatterPlot();
        quantizedSignal.createPlot();
        Signal reconstuctionSignal = samplingCalculator.calculateReconstraction();
        reconstuctionSignal.createPlot();
        samplingCalculator.createPlot();
//        Signal firstSignal1;
//        Signal secondSignal2;
//        firstSignal1 = signalSwitch(showSignals(), firstSignal);
//        secondSignal2 = signalSwitch(showSignals(), secondSignal);
//        operations(firstSignal1, secondSignal2);
//        operations(firstSignal1, secondSignal2);
//        operations(firstSignal1, secondSignal2);
//        operations(firstSignal1, secondSignal2);

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
