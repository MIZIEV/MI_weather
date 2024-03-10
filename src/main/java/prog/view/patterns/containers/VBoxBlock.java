package prog.view.patterns.containers;

import prog.web.IndexParser;
import prog.web.JSONParser;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import prog.view.patterns.controls.ButtonsPattern;
import prog.view.patterns.controls.LabelPattern;

public class VBoxBlock extends VBoxPattern {
    public VBoxBlock(int width, String styleClass) {
        super(width, styleClass);
    }

    public VBoxBlock(double spacing, int width, int height, Pos position, String styleClass) {
        super(spacing, width, height, position, styleClass);
    }
/*
    private final static String STYLE_CLASS_BUTTON_INSIDE_VBOX = "Button-insideVBox";
    private final static String SC_LABEL = "Label";
    private final static byte CENTRAL_HBOX_SPICING = 10;
    private final static byte VBOX_BUTTON_WIDTH = 70;
    private final static byte VBOX_BUTTON_HEIGHT = 40;

    private ButtonsPattern Info;

    public VBoxBlock(double spacing, int width, int height, Pos position, String styleClass) {
        super(spacing, width, height, position, styleClass);
    }

    public void arrangeElements(String date, String clouds, IndexParser indexParser, JSONParser parser, DataAnalyser analyser) {
        HBox minMaxTempHBox = new HBox(CENTRAL_HBOX_SPICING);
        minMaxTempHBox.setAlignment(Pos.CENTER);
        LabelPattern data = new LabelPattern(date, SC_LABEL);
        LabelPattern minTemp = new LabelPattern(indexParser, "min ", parser, analyser, SC_LABEL);
        LabelPattern maxTemp = new LabelPattern(indexParser, "max ", parser, analyser, SC_LABEL);
        LabelPattern weather = new LabelPattern(clouds, SC_LABEL);
        Info = new ButtonsPattern(VBOX_BUTTON_WIDTH, VBOX_BUTTON_HEIGHT, "info", STYLE_CLASS_BUTTON_INSIDE_VBOX);
        minMaxTempHBox.getChildren().addAll(minTemp, maxTemp);
        this.getChildren().addAll(data, minMaxTempHBox, weather, Info);
    }
    public ButtonsPattern getInfo() { return Info; }*/
}