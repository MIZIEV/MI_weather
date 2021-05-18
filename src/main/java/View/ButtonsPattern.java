package View;

import javafx.scene.control.Button;

public class ButtonsPattern extends Button {

    private final int height;
    private final int width;
    private final String name;

    public ButtonsPattern(int h, int w, String n) {
        this.height = h;
        this.width = w;
        this.name = n;

        this.setText(name);
        this.setPrefSize(height, width);
    }
}
