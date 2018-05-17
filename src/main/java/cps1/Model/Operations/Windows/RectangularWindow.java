package cps1.Model.Operations.Windows;

public class RectangularWindow extends Window {

    public RectangularWindow(int size) {
        super(size);
    }

    @Override
    void initialize() {
        for (int i = 0; i < this.values.length; i++) {
            values[i] = 1;
        }
    }
}
