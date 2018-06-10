package cps1.Model.Operations.Transformation;

import cps1.Model.Signals.Signal;

public abstract class Transformation {
    private double start;
    private double transformationTime;

    public Signal transformacja(Signal signal){
        this.start = System.currentTimeMillis();
        Signal returnSignal = transformationBody(signal);
        transformationTime = System.currentTimeMillis() - start;
        return returnSignal;
    }

    public double getStart() {
        return start;
    }

    public double getTransformationTime() {
        return transformationTime;
    }

    public abstract Signal transformationBody(Signal signal);

    public abstract Signal restoreSignal(Signal signal);
}
