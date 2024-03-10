package prog.view.patterns.controls;

import prog.web.IndexParser;
import prog.web.JSONParser;
import javafx.scene.control.Label;

public class LabelPattern extends Label {

    private JSONParser parser;
    //private DataAnalyser analyser;
    private String labelText;
    private IndexParser indexParser;

    public LabelPattern(String text, String styleClass) {
        super(text);
        getStyleClass().add(styleClass);
    }

 /*   public LabelPattern(IndexParser indexParser, String text, JSONParser parser, DataAnalyser analyser, String styleClass) {
        this.parser = parser;
        this.analyser = analyser;
        this.labelText = text;
        this.indexParser = indexParser;
        //setText(text + this.getTemp() + "°C");
        getStyleClass().add(styleClass);
    }*/
/*
    private int getTemp() {
        int temp = 0;
        if (labelText.equals("min ")) {
            temp = analyser.minTemp(parser.getTempMap().
                    tailMap(parser.getKeysForMap().get(indexParser.getStartDayIndex())).
                    headMap(parser.getKeysForMap().get(indexParser.getEndDayIndex() + 1)));
            return temp;
        } else if (labelText.equals("max ")) {
            temp = analyser.maxTemp(parser.getTempMap().
                    tailMap(parser.getKeysForMap().get(indexParser.getStartDayIndex())).
                    headMap(parser.getKeysForMap().get(indexParser.getEndDayIndex() + 1)));
            return temp;
        } else return temp;
    }*/
}