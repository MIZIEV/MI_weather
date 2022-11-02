package prog.view;

import prog.models.DataAnalyser;
import prog.web.JSONParser;
import animatefx.animation.FadeIn;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

public class Diagram {

    private final JSONParser parser;
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

    public Diagram(JSONParser jsonParser, DataAnalyser dataAnalyser) {
        this.parser = jsonParser;
        this.analyser = dataAnalyser;
    }

    public BorderPane showDiagram(String stylesheet) {

        String paneStylesheet = getClass().getResource(stylesheet + "DiagramPaneStyle.css").toExternalForm();
        String diagramStylesheet = getClass().getResource(stylesheet + "DiagramStyle.css").toExternalForm();
        BorderPane diagramPane = new BorderPane();
        diagramPane.getStyleClass().add(PANE_STYLE);

        NumberAxis XCoordinateTime = new NumberAxis(LOWER_BOUND_X, UPPER_BOUND_X, TICK_UNIT_X);
        NumberAxis YCoordinateDegrees = new NumberAxis(LOWER_BOUND_Y,
                (analyser.maxTemp(parser.getTempMap())) + UPPER_BOUND_Y, TICK_UNIT_Y);
        AreaChart<Number, Number> diagram = new AreaChart<>(XCoordinateTime, YCoordinateDegrees);

        temp.setName(" °C ");
        diagram.getData().addAll(temp);
        diagram.setTitle(DIAGRAM_TITLE);
        diagram.getStylesheets().add(diagramStylesheet);
        diagramPane.setCenter(diagram);
        diagramPane.getStylesheets().add(paneStylesheet);
        diagramPane.setPrefSize(PANE_WIDTH, PANE_HEIGHT);
        new FadeIn(diagramPane).play();
        return diagramPane;
    }
    public XYChart.Series getTemp() { return temp; }
}