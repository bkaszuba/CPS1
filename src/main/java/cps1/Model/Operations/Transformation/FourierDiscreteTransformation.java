package cps1.Model.Operations.Transformation;

import cps1.Model.Signals.Signal;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.util.FastMath;

public class FourierDiscreteTransformation extends Transformation {

    private int bitCount = -1;

    public FourierDiscreteTransformation(int bitCount) {
        super();
        this.bitCount = bitCount;
    }

    @Override
    public Signal transformationBody(Signal signal) {
        double start = System.currentTimeMillis();

        int N;

        if(bitCount <= 0) {
            bitCount = (int)FastMath.log(2, signal.getImaginary().length);
            N = (int)Math.pow(2, bitCount);
        }
        else {
            N = (int) Math.pow(2, bitCount);
        }

        Complex[] input = new Complex[N];

        System.out.println(this.toString());
        System.out.println("bitCount: " + (int)FastMath.log(2, N ));

        for (int i = 0; i < N; i++) {
            Complex currentComplexValue = signal.getImaginary()[i];
            input[i] = new Complex(currentComplexValue.getReal(), currentComplexValue.getImaginary());
        }

        Complex[] output = new Complex[N];

        for (int k = 0; k < N; k++) {

            double re = 0;
            double im = 0;

            for (int t = 0; t < N; t++) {

                double x = 2 * Math.PI * t * k / N;
                double sin = Math.sin(x);
                double cos = Math.cos(x);

                re += input[t].getReal() * cos + input[t].getImaginary() * sin;
                im += -input[t].getReal() * sin + input[t].getImaginary() * cos;

            }

            output[k] = new Complex(re / N, im / N);
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
            bitCount = (int)FastMath.log(2, signal.getImaginary().length);
            N = (int)Math.pow(2, bitCount);
        }
        else {
            N = (int) Math.pow(2, bitCount);
        }

        Complex[] input = new Complex[N];

        System.out.println(this.toString());
        System.out.println("bitCount: " + (int)FastMath.log(2, N ));

        for (int i = 0; i < N; i++) {
            Complex currentComplexValue = signal.getImaginary()[i];
            input[i] = new Complex(currentComplexValue.getReal(), currentComplexValue.getImaginary());
        }

        Complex[] output = new Complex[N];

        for (int k = 0; k < N; k++) {

            double re = 0;
            double im = 0;

            for (int t = 0; t < N; t++) {

                double x = 2 * Math.PI * t * k / N;
                double sin = Math.sin(x);
                double cos = Math.cos(x);

                re += input[t].getReal() * cos + input[t].getImaginary() * sin;
                im += input[t].getReal() * sin + input[t].getImaginary() * cos;

            }

            output[k] = new Complex(re, im);
        }

        Signal result = new Signal();
        result.setFrequency(signal.getFrequency());
        result.setImaginary(output);

        return result;
    }

    @Override
    public String toString() {
        return "TransformataFouriereNaZbiorzeDyskretnym (DFT)";
    }
}
