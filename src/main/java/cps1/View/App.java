package cps1.View;
import cps1.Model.SinDoubleStraightSignal;
import cps1.Model.SinSignal;
import cps1.Model.SinSingleStraightSignal;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        SinSignal sin = new SinSignal(0,10, 0.1, 5);
        sin.drawPlot();
        SinDoubleStraightSignal sinDoub = new SinDoubleStraightSignal(0,10,0.01,1);
        sinDoub.drawPlot();
        SinSingleStraightSignal sinSig = new SinSingleStraightSignal(0,10,0.01,3);
        sinSig.drawPlot();

    }
}
