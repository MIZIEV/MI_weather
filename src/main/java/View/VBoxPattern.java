package View;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class VBoxPattern extends VBox {

    public VBoxPattern(double spacing, int width, int height, Pos position,String styleClass) {
        super(spacing);
        setPrefSize(width, height);
        setAlignment(position);
        getStyleClass().add(styleClass);
    }
}