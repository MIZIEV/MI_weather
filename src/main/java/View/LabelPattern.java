package View;

import javafx.scene.control.Label;

public class LabelPattern extends Label {

    public LabelPattern(String text, String styleClass) {
        super(text);
        getStyleClass().add(styleClass);
    }
}