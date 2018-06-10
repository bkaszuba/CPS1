package cps1.Model.Operations.Transformation;

import cps1.Model.Signals.Signal;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.util.FastMath;

public class FastFourierTransformation extends Transformation {

    private int bitCount = -1;

    public FastFourierTransformation(int bitCount) {
        super();
        this.bitCount = 0;
    }

    @Override
    public Signal transformationBody(Signal signal) {

        int N;

        if(bitCount <= 0) {
            bitCount = (int)FastMath.log(2, signal.getImaginary().length);
            N = (int)Math.pow(2, bitCount);
        }
        else {
            N = (int) Math.pow(2, bitCount);
        }

        Complex[] complexValues = new Complex[N];

        System.out.println(this.toString());
        System.out.println("bitCount: " + (int) FastMath.log(2, N ));

        for (int i = 0; i < N; i++) {
            Complex currentComplexValue = signal.getImaginary()[i];
            complexValues[i] = new Complex(currentComplexValue.getReal(), currentComplexValue.getImaginary());
        }

        complexValues = inverse(complexValues);

        for (int n = 2; n <= N; n *= 2) {
            Complex[] complexesTmp = new Complex[N];

            for (int i = 0; i < N; i += n) {
                for (int b = i; b < i + n; b++) {
                    complexesTmp[b] = complexValues[b % (n / 2) + i].add(core((b % n), n).multiply(complexValues[b % (n / 2) + i + n / 2]));
                }
            }

            for (int i = 0; i < N; i++) {
                complexValues[i] = new Complex(complexesTmp[i].getReal(), complexesTmp[i].getImaginary());
            }
        }

        Complex[] result = new Complex[N];
        for (int i = 0; i < N; i++) {
            result[i] = new Complex(complexValues[i].getReal()/N, complexValues[i].getImaginary()/N);
        }

        Signal fourierTransform = new Signal();
        fourierTransform.setImaginary(result);
        fourierTransform.setFrequency(signal.getFrequency());

        return fourierTransform;
    }

    private Complex[] inverse(Complex[] input) {

        Complex[] result = new Complex[input.length];
        for (int i = 0; i < input.length; i++) {
            Complex currentComplexValue = input[bitWiseInverse(i, bitCount)];
            result[i] = new Complex(currentComplexValue.getReal(), currentComplexValue.getImaginary());
        }

        return result;
    }

    private int bitWiseInverse(int value, int bitPosition) {
        int inverseValue = 0;

        for (int i = 0; i < bitPosition; i++) {
            boolean bit = (value & (1 << i)) > 0;
            if (bit) {
                inverseValue |= (1 << bitPosition - i - 1);
            } else {
                inverseValue &= ~(1 << bitPosition - i - 1);
            }
        }
        return inverseValue;
    }

    private Complex core(int k, int N) {
        double x = 2 * Math.PI * k / N;
        return new Complex(Math.cos(x), -Math.sin(x));
    }

    @Override
    public String toString() {
        return "SzybkaTransformataFouriera (FFT)";
    }

}
