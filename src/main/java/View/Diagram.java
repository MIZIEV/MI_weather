package View;

import Model.DataAnalyser;
import Model.JSONDataParser;
import animatefx.animation.FadeIn;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

public class Diagram {

    private final JSONDataParser parser;
    private final DataAnalyser analyser;
    private final XYChart.Series temp = new XYChart.Series();

    private final static String PANE_STYLE = "Pane-for-diagram";
    private final static String DIAGRAM_TITLE = "All day temperature";
    private final static short PANE_WIDTH = 1200;
    private final static short PANE_HEIGHT = 400;

    private final static byte LOWER_BOUND_X = 0;
    private final static byte UPPER_BOUND_X = 24;
    private final static byte TICK_UNIT_X = 3;

    private final static byte LOWER_BOUND_Y = 0;
    private final static byte UPPER_BOUND_Y = 5;
    private final static byte TICK_UNIT_Y = 5;

    public Diagram(JSONDataParser jsonParser, DataAnalyser dataAnalyser) {
        this.parser = jsonParser;
        this.analyser = dataAnalyser;
    }

    public BorderPane showDiagram() {

        String stylesheet = getClass().getResource("/DiagramPaneStyle.css").toExternalForm();
        BorderPane diagramPane = new BorderPane();
        diagramPane.getStyleClass().add(PANE_STYLE);

        NumberAxis XCoordinateTime = new NumberAxis(LOWER_BOUND_X, UPPER_BOUND_X, TICK_UNIT_X);
        NumberAxis YCoordinateDegrees = new NumberAxis(LOWER_BOUND_Y,
                (analyser.maxTemp(parser.getTempMap()) - 273) + UPPER_BOUND_Y, TICK_UNIT_Y);
        AreaChart<Number, Number> diagram = new AreaChart<>(XCoordinateTime, YCoordinateDegrees);

        temp.setName(" °C ");
        diagram.getData().addAll(temp);
        diagram.setTitle(DIAGRAM_TITLE);
        diagramPane.setCenter(diagram);
        diagramPane.getStylesheets().add(stylesheet);
        diagramPane.setPrefSize(PANE_WIDTH, PANE_HEIGHT);
        new FadeIn(diagramPane).play();
        return diagramPane;
    }
    public XYChart.Series getTemp() { return temp; }
}