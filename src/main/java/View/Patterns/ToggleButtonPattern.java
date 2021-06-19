package View.Patterns;

import javafx.scene.control.ToggleButton;

public class ToggleButtonPattern extends ToggleButton {

    public ToggleButtonPattern(int width, int height, String styleCLass) {
        setPrefSize(width, height);
        getStyleClass().add(styleCLass);
    }
}