package View;

import javafx.scene.control.Button;

public class ButtonsPattern extends Button {

    public ButtonsPattern(int height, int width, String name, String styleCLass) {

        setText(name);
        setPrefSize(height, width);
        getStyleClass().add(styleCLass);
    }
}