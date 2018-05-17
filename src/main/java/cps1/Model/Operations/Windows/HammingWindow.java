package cps1.Model.Operations.Windows;

public class HammingWindow extends Window{

    public HammingWindow(int size) {
        super(size);
    }

    @Override
    void initialize() {
        for (int i = 0; i < this.values.length; i++) {
            values[i] = 0.53836-0.46164 * Math.cos((2 * Math.PI * i)/this.values.length);
        }
    }
}
