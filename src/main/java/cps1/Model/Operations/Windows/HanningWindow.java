package cps1.Model.Operations.Windows;

public class HanningWindow extends Window {

    public HanningWindow(int size) {
        super(size);
    }

    @Override
    void initialize() {
        for (int i = 0; i < this.values.length; i++) {
            values[i] = 0.5 - 0.5 *  Math.cos((2 * Math.PI * i)/this.values.length);
        }
    }
}
