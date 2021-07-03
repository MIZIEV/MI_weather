package View.Patterns;

import Model.DataAnalyser;
import Model.IndexClass;
import Model.JSONDataParser;
import javafx.scene.control.Label;

public class LabelPattern extends Label {

    private JSONDataParser parser;
    private DataAnalyser analyser;
    private String labelText;
    private IndexClass indexClass;

    public LabelPattern(String text, String styleClass) {
        super(text);
        getStyleClass().add(styleClass);
    }

    public LabelPattern(IndexClass indexClass, String text, JSONDataParser parser, DataAnalyser analyser, String styleClass) {
        this.parser = parser;
        this.analyser = analyser;
        this.labelText = text;
        this.indexClass = indexClass;
        setText(text + this.getTemp() + "°C");
        getStyleClass().add(styleClass);
    }

    private int getTemp() {
        int temp = 0;
        if (labelText.equals("min ")) {
            temp = analyser.minTemp(parser.getTempMap().
                    tailMap(parser.getKeysForMap().get(indexClass.getStartDayIndex())).
                    headMap(parser.getKeysForMap().get(indexClass.getEndDayIndex() + 1)));
            return temp;
        } else if (labelText.equals("max ")) {
            temp = analyser.maxTemp(parser.getTempMap().
                    tailMap(parser.getKeysForMap().get(indexClass.getStartDayIndex())).
                    headMap(parser.getKeysForMap().get(indexClass.getEndDayIndex() + 1)));
            return temp;
        } else return temp;
    }
}