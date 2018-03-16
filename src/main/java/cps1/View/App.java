package cps1.View;

import cps1.Model.Operations.Operation;
import cps1.Model.Operations.ParametersCalculator;
import cps1.Model.Signals.RectangularSignal;
import cps1.Model.Signals.SinSignal;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {


        SinSignal sin = new SinSignal(0,10, 0.01, 2, 4.0);
//        SinSignal sin = new SinSignal("/Users/madekko/Developer/CPS1/signal.txt");
//        sin.readFromFile("/Users/madekko/Developer/CPS1/signal.txt");
//        sin.showDataSet();
//        sin.drawPlot();

//        SinDoubleStraightSignal sinDoub = new SinDoubleStraightSignal(0,10,0.01,10, 4.0);
//        sinDoub.drawPlot();

//        SinSingleStraightSignal sinSig = new SinSingleStraightSignal(0,10,0.01,10, 4.0);
//        sinSig.drawPlot();

//        RectangularSignal rectangularSignal = new RectangularSignal(0, 10, 0.01, 10, 4.0, 0.5);
//        rectangularSignal.drawPlot();

//        RectangularSymmetricSignal rectangularSymmetricSignal = new RectangularSymmetricSignal(0,6, 0.1,10, 4.0,0.5);
//        rectangularSymmetricSignal.drawPlot();

//        TriangularSignal triangularSignal = new TriangularSignal(0,10, 0.1,10, 4.0,0.5);
//        triangularSignal.drawPlot();

//        UnitStep unitStep = new UnitStep(0,10, 0.01,6,4);
//        unitStep.drawPlot();

//        UnitImpulse unitImpulse = new UnitImpulse(0,20,0.1, 3,10);
//        unitImpulse.drawPlot();

//        ImpulseNoise impulseNoise = new ImpulseNoise(0,20,1.0,5,0.1);
//        impulseNoise.drawPlot();



//        Operations such as adding etc
//        Operation operation = new Operation(sin, rectangularSignal);
//        operation.add();

//        Calculating extra params
//        ParametersCalculator parametersCalculator = new ParametersCalculator(sin);
//        parametersCalculator.calculateAverage();

    }

}
