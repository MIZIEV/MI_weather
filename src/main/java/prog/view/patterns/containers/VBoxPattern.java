package prog.view.patterns.containers;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class VBoxPattern extends VBox {

    public VBoxPattern(int width, String styleClass) {
        setPrefWidth(width);
        getStyleClass().add(styleClass);
    }

    public VBoxPattern(double spacing, int width, int height, Pos position, String styleClass) {
        super(spacing);
        setPrefSize(width, height);
        setAlignment(position);
        getStyleClass().add(styleClass);
    }
}