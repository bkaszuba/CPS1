package cps1.Model.Operations.Windows;

public class BlackmanWindow extends Window {

    public BlackmanWindow(int size) {
        super(size);
    }

    @Override
    void initialize() {
        for (int i = 0; i < this.values.length; i++) {
            values[i] = (0.42-0.5 * Math.cos((2 * Math.PI * i)/this.values.length)) +
                    (0.08 * Math.cos((4 * Math.PI * i)/this.values.length)) ;
        }
    }
}
