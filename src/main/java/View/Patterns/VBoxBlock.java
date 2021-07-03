package View.Patterns;

import Model.DataAnalyser;
import Model.IndexClass;
import Model.JSONDataParser;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class VBoxBlock extends VBoxPattern {

    private final static String STYLE_CLASS_BUTTON_INSIDE_VBOX = "Button-insideVBox";
    private final static String STYLE_CLASS_STANDARD_LABEL = "Label-standard";
    private final static byte CENTRAL_HBOX_SPICING = 10;
    private final static byte VBOX_BUTTON_WIDTH = 70;
    private final static byte VBOX_BUTTON_HEIGHT = 40;

    private ButtonsPattern Info;

    public VBoxBlock(double spacing, int width, int height, Pos position, String styleClass) {
        super(spacing, width, height, position, styleClass);
    }

    public void arrangeElements(String date, IndexClass indexClass, JSONDataParser parser, DataAnalyser analyser) {
        HBox minMaxTempHBox = new HBox(CENTRAL_HBOX_SPICING);
        minMaxTempHBox.setAlignment(Pos.CENTER);
        LabelPattern data = new LabelPattern(date, STYLE_CLASS_STANDARD_LABEL);
        LabelPattern minTemp = new LabelPattern(indexClass, "min ", parser, analyser, STYLE_CLASS_STANDARD_LABEL);
        LabelPattern maxTemp = new LabelPattern(indexClass, "max ", parser, analyser, STYLE_CLASS_STANDARD_LABEL);
        LabelPattern weather = new LabelPattern(parser.getWeatherToday(), STYLE_CLASS_STANDARD_LABEL);
        Info = new ButtonsPattern(VBOX_BUTTON_WIDTH, VBOX_BUTTON_HEIGHT, "info", STYLE_CLASS_BUTTON_INSIDE_VBOX);
        minMaxTempHBox.getChildren().addAll(minTemp, maxTemp);
        this.getChildren().addAll(data, minMaxTempHBox, weather, Info);
    }
    public ButtonsPattern getInfo() { return Info; }
}