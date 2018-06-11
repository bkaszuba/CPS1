package cps1.Model.Operations.Transformation;

import cps1.Model.Signals.Signal;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.util.FastMath;


public class CosinusTransformation extends Transformation {

    private int bitCount = -1;

    public CosinusTransformation(int bitCount) {
        super();
        this.bitCount = 0;
    }

    @Override
    public Signal transformationBody(Signal signal) {
        double start = System.currentTimeMillis();

        int N;

        if(bitCount <= 0) {
            bitCount = (int) FastMath.log(2, signal.getImaginary().length);
            N = (int)Math.pow(2, bitCount);
        }
        else {
            N = (int) Math.pow(2, bitCount);
        }

        Complex[] input = signal.getImaginary();
        Complex[] output = new Complex[N];

        System.out.println(this.toString());
        System.out.println("bitCount: " + (int) FastMath.log(2, N ));

        for (int m = 0; m < N; m++) {

            double X = 0;

            for (int n = 0; n < N; n++) {

                X += input[n].getReal() * Math.cos(Math.PI * (2d * n + 1) * m / (2d * N));

            }

            output[m] = new Complex(c(m, N) * X, 0);

        }

        Signal result = new Signal();
        result.setFrequency(signal.getFrequency());
        result.setImaginary(output);

        double transformationTime = System.currentTimeMillis() - start;
        System.out.println(transformationTime);
        return result;
    }

    @Override
    public Signal restoreSignal(Signal signal) {

        int N;

        if(bitCount <= 0) {
            bitCount = (int) FastMath.log(2, signal.getImaginary().length);
            N = (int)Math.pow(2, bitCount);
        }
        else {
            N = (int) Math.pow(2, bitCount);
        }

        Complex[] input = signal.getImaginary();
        Complex[] output = new Complex[N];

        System.out.println(this.toString());
        System.out.println("bitCount: " + (int) FastMath.log(2, N ));

        for (int m = 0; m < N; m++) {

            double X = 0;

            for (int n = 0; n < N; n++) {

                X += c(m, N) *  input[n].getReal() * Math.cos(Math.PI * (2d * n + 1) * m / (2d * N));

            }

            output[m] = new Complex(X, 0);

        }

        Signal result = new Signal();
        result.setFrequency(signal.getFrequency());
        result.setImaginary(output);

        return result;
    }

    double c(int x, int N) {

        if (x == 0) {
            return Math.sqrt(1.0 / N);
        } else {
            return Math.sqrt(2.0 / N);
        }
    }

    @Override
    public String toString() {
        return "TransformacjaKosinusowa (DCT)";
    }

}
