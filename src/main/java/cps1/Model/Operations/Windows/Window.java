package cps1.Model.Operations.Windows;

public abstract class Window {
    double[] values;

    public Window() {}

    public Window(int size) {
        values = new double[size];
        initialize();
    }
    abstract void initialize();

    public double getValue(int index) {
        return values[index];
    }
}
