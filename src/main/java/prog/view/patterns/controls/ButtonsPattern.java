package prog.view.patterns.controls;

import javafx.scene.control.Button;

public class ButtonsPattern extends Button {

    public ButtonsPattern(int width, int height, String styleCLass) {
        setPrefSize(width, height);
        getStyleClass().add(styleCLass);
    }

    public ButtonsPattern(int width, int height, String name, String styleCLass) {
        setText(name);
        setPrefSize(width, height);
        getStyleClass().add(styleCLass);
    }
}