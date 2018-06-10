package cps1.Model.Operations.Transformation;

import cps1.Model.Signals.Signal;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.util.FastMath;

public class FastCosinusTransformation extends Transformation {

    private int bitCount = -1;

    public FastCosinusTransformation(int bitCount) {
        super();
        this.bitCount = 0;
    }

    @Override
    public Signal transformationBody(Signal signal) {

        int N;

        if(bitCount <= 0) {
            bitCount = (int) FastMath.log(2, signal.getImaginary().length);
            N = (int)Math.pow(2, bitCount);
        }
        else {
            N = (int) Math.pow(2, bitCount);
        }

        Complex[] input = signal.getImaginary();

        input = redefineInputSignalValues(input);

        Signal signalForFourier = new Signal();
        signalForFourier.setImaginary(input);

        double a = Math.log(N)/Math.log(2);

        input = new FastFourierTransformation((int) Math.floor(a)).transformacja(signalForFourier).getImaginary();

        Complex[] output = new Complex[N];
        System.out.println(this.toString());
        System.out.println("bitCount: " + (int) FastMath.log(2, N ));

        for (int m = 0; m < N; m++) {

            double argument = Math.PI * m / (2d * N);
            double cos = Math.cos(argument);
            double sin = Math.sin(argument);

            double X = c(m,N) * (cos * input[m].getReal() + sin * input[m].getImaginary());

            output[m] = new Complex(X, 0);

        }

        Signal result = new Signal();
        result.setFrequency(signal.getFrequency());
        result.setImaginary(output);

        return result;
    }

    @Override
    public Signal restoreSignal(Signal signal) {
        //TODO IMPLEMENT
        return new Signal();
    }

    double c(int x, int N) {

        if (x == 0) {
            return Math.sqrt(1.0 / N);
        } else {
            return Math.sqrt(2.0 / N);
        }

    }

    public Complex[] redefineInputSignalValues(Complex[] input) {

        int N = input.length;

        Complex[] result = new Complex[N];
        int i = 0;

        for (int n = 0; n < N; n += 2, i++) {
            result[i] = input[n];
        }

        for (int n = N - 1; n > 0; n -= 2, i++) {
            result[i] = input[n];
        }

        return result;
    }

    @Override
    public String toString() {
        return "SzybkaTransformacjaKosinusowa (FCT)";
    }

}
