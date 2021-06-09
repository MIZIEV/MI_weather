package View;

import Controller.*;
import Model.CityName;
import Model.DataAnalyser;
import Model.JSONDataParser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class SecondWindow {

    private final Stage secondWindow = new Stage();
    private final CityName cityName;
    private final FirstWindow firstWindow;
    private final JSONDataParser parser;
    private final XYChart.Series temp = new XYChart.Series();

    private final static String WINDOW_TITLE = "MI weather";
    private final static String STYLE_CLASS_SMALL_LABEL = "Label-small";
    private final static String STYLE_CLASS_STANDARD_LABEL = "Label-standard";
    private final static String STYLE_CLASS_BUTTON_INSIDE_VBOX = "Button-insideVBox";
    private final static String STYLE_CLASS_BACK_BUTTON = "Button";
    private final static String STYLE_CLASS_CENTRAL_VBOX = "Central-VBox";
    private final static String STYLE_CLASS_FIRST_VBOX = "First-small-VBox";
    private final static String STYLE_CLASS_SECOND_VBOX = "Second-small-VBox";
    private final static String STYLE_CLASS_THIRD_VBOX = "Third-small-VBox";
    private final static String STYLE_CLASS_FOURTH_VBOX = "Fourth-small-VBox";
    private final static String STYLE_CLASS_FIFTH_VBOX = "Fifth-small-VBox";

    private final static short VBOX_PREF_WIDTH = 150;
    private final static short VBOX_PREF_HEIGHT = 150;

    private final static short WINDOW_WIDTH = 1024;
    private final static short WINDOW_HEIGHT = 768;
    private final static short WINDOW_MIN_WIDTH = 880;
    private final static short WINDOW_MIN_HEIGHT = 550;

    public SecondWindow(CityName name, JSONDataParser jsonParser, FirstWindow firstW) {
        this.cityName = name;
        this.parser = jsonParser;
        this.firstWindow = firstW;
    }

    public void startWin() {
        Insets margin = new Insets(20);
        DecimalFormat format = new DecimalFormat("#");
        DataAnalyser analyser = new DataAnalyser();

        NumberAxis XCoordinateTime = new NumberAxis(0, 24, 3);
        NumberAxis YCoordinateDegrees = new NumberAxis(0, 50, 5);
        AreaChart<Number, Number> diagram = new AreaChart<>(XCoordinateTime, YCoordinateDegrees);
        diagram.setTitle("Temperature");

        String stylesheet = getClass().getResource("/SecondWindow.css").toExternalForm();

        BorderPane generalPane = new BorderPane();
        BorderPane bottomPane = new BorderPane();
        BorderPane topPane = new BorderPane();
        VBox centerVbox = new VBox(20);
        Pane topLeftInsert = new Pane();
        Pane topRightInsert = new Pane();
        topLeftInsert.setPrefWidth(350);
        topRightInsert.setPrefWidth(350);

        VBox topVBox = new VBox();
        HBox centerHBox = new HBox(20);
        HBox topCentralMiddleHBox = new HBox(10);

        VBoxPattern vbox1 = new VBoxPattern(10, VBOX_PREF_WIDTH, VBOX_PREF_HEIGHT, Pos.CENTER, STYLE_CLASS_FIRST_VBOX);
        HBox hBox1 = new HBox(10);
        hBox1.setAlignment(Pos.CENTER);
        LabelPattern data1 = new LabelPattern(parser.getToday(), STYLE_CLASS_SMALL_LABEL);
        LabelPattern minTemp1 = new LabelPattern("min " + format.format(parser.getMinTempToday() - 273.15) + " C;", STYLE_CLASS_SMALL_LABEL);
        LabelPattern maxTemp1 = new LabelPattern("max " + format.format(parser.getMaxTempToday() - 273.15) + " C", STYLE_CLASS_SMALL_LABEL);
        LabelPattern clouds1 = new LabelPattern(parser.getCloudsToday(), STYLE_CLASS_SMALL_LABEL);
        ButtonsPattern todayInfo = new ButtonsPattern(70, 40, "info", STYLE_CLASS_BUTTON_INSIDE_VBOX);
        hBox1.getChildren().addAll(minTemp1, maxTemp1);
        vbox1.getChildren().addAll(data1, hBox1, clouds1, todayInfo);

        VBoxPattern vbox2 = new VBoxPattern(10, VBOX_PREF_WIDTH, VBOX_PREF_HEIGHT, Pos.CENTER, STYLE_CLASS_SECOND_VBOX);
        HBox hBox2 = new HBox(10);
        hBox2.setAlignment(Pos.CENTER);
        LabelPattern data2 = new LabelPattern(parser.getTomorrowData(), STYLE_CLASS_SMALL_LABEL);
        LabelPattern minTemp2 = new LabelPattern("min " + format.format(analyser.minTemp(parser.getTempMap().
                tailMap(parser.getMapKeys().get(parser.getIndexList().get(1))).
                headMap(parser.getMapKeys().get(parser.getIndexList().get(2) + 1))) - 273.15) + " C;", STYLE_CLASS_SMALL_LABEL);
        LabelPattern maxTemp2 = new LabelPattern("max " + format.format(analyser.maxTemp(parser.getTempMap().
                tailMap(parser.getMapKeys().get(parser.getIndexList().get(1))).
                headMap(parser.getMapKeys().get(parser.getIndexList().get(2) + 1))) - 273.15) + " C", STYLE_CLASS_SMALL_LABEL);
        LabelPattern clouds2 = new LabelPattern(parser.getCloudsTomorrow(), STYLE_CLASS_SMALL_LABEL);
        ButtonsPattern infoTomorrow = new ButtonsPattern(70, 40, "info", STYLE_CLASS_BUTTON_INSIDE_VBOX);
        hBox2.getChildren().addAll(minTemp2, maxTemp2);
        vbox2.getChildren().addAll(data2, hBox2, clouds2, infoTomorrow);

        VBoxPattern vbox3 = new VBoxPattern(10, VBOX_PREF_WIDTH, VBOX_PREF_HEIGHT, Pos.CENTER, STYLE_CLASS_THIRD_VBOX);
        HBox hBox3 = new HBox(10);
        hBox3.setAlignment(Pos.CENTER);
        LabelPattern data3 = new LabelPattern(parser.getDayAfterTomorrow(), STYLE_CLASS_SMALL_LABEL);
        LabelPattern minTemp3 = new LabelPattern("min " + format.format(analyser.minTemp(parser.getTempMap().
                tailMap(parser.getMapKeys().get(parser.getIndexList().get(2))).
                headMap(parser.getMapKeys().get(parser.getIndexList().get(3) + 1))) - 273.15) + " C;", STYLE_CLASS_SMALL_LABEL);
        LabelPattern maxTemp3 = new LabelPattern("max " + format.format(analyser.maxTemp(parser.getTempMap().
                tailMap(parser.getMapKeys().get(parser.getIndexList().get(2))).
                headMap(parser.getMapKeys().get(parser.getIndexList().get(3) + 1))) - 273.15) + " C", STYLE_CLASS_SMALL_LABEL);
        LabelPattern clouds3 = new LabelPattern(parser.getCloudsAfterTomorrow(), STYLE_CLASS_SMALL_LABEL);
        ButtonsPattern afterTomorrowInfo = new ButtonsPattern(70, 40, "info", STYLE_CLASS_BUTTON_INSIDE_VBOX);
        hBox3.getChildren().addAll(minTemp3, maxTemp3);
        vbox3.getChildren().addAll(data3, hBox3, clouds3, afterTomorrowInfo);

        VBoxPattern vbox4 = new VBoxPattern(10, VBOX_PREF_WIDTH, VBOX_PREF_HEIGHT, Pos.CENTER, STYLE_CLASS_FOURTH_VBOX);
        HBox hBox4 = new HBox(10);
        hBox4.setAlignment(Pos.CENTER);
        LabelPattern data4 = new LabelPattern(parser.getFourthDay(), STYLE_CLASS_SMALL_LABEL);
        LabelPattern minTemp4 = new LabelPattern("max " + format.format(analyser.minTemp(parser.getTempMap().
                tailMap(parser.getMapKeys().get(parser.getIndexList().get(3))).
                headMap(parser.getMapKeys().get(parser.getIndexList().get(4) + 1))) - 273.15) + " C;", STYLE_CLASS_SMALL_LABEL);
        LabelPattern maxTemp4 = new LabelPattern("max " + format.format(analyser.maxTemp(parser.getTempMap().
                tailMap(parser.getMapKeys().get(parser.getIndexList().get(3))).
                headMap(parser.getMapKeys().get(parser.getIndexList().get(4) + 1))) - 273.15) + " C", STYLE_CLASS_SMALL_LABEL);
        LabelPattern clouds4 = new LabelPattern(parser.getCloudsFourthDay(), STYLE_CLASS_SMALL_LABEL);
        ButtonsPattern fourDayInfo = new ButtonsPattern(70, 40, "info", STYLE_CLASS_BUTTON_INSIDE_VBOX);
        hBox4.getChildren().addAll(minTemp4, maxTemp4);
        vbox4.getChildren().addAll(data4, hBox4, clouds4, fourDayInfo);

        VBoxPattern vbox5 = new VBoxPattern(10, VBOX_PREF_WIDTH, VBOX_PREF_HEIGHT, Pos.CENTER, STYLE_CLASS_FIFTH_VBOX);
        HBox hBox5 = new HBox(10);
        hBox5.setAlignment(Pos.CENTER);
        LabelPattern data5 = new LabelPattern(parser.getFifthData(), STYLE_CLASS_SMALL_LABEL);
        LabelPattern minTemp5 = new LabelPattern("min " + format.format(analyser.minTemp(parser.getTempMap().
                tailMap(parser.getMapKeys().get(parser.getIndexList().get(4))).
                headMap(parser.getMapKeys().get(parser.getIndexList().get(5) + 1))) - 273.15) + " C;", STYLE_CLASS_SMALL_LABEL);
        LabelPattern maxTemp5 = new LabelPattern("max " + format.format(analyser.maxTemp(parser.getTempMap().
                tailMap(parser.getMapKeys().get(parser.getIndexList().get(4))).
                headMap(parser.getMapKeys().get(parser.getIndexList().get(5) + 1))) - 273.15) + " C", STYLE_CLASS_SMALL_LABEL);
        LabelPattern clouds5 = new LabelPattern(parser.getCloudsFifthDay(), STYLE_CLASS_SMALL_LABEL);
        ButtonsPattern fifthDayInfo = new ButtonsPattern(70, 40, "info", STYLE_CLASS_BUTTON_INSIDE_VBOX);
        hBox5.getChildren().addAll(minTemp5, maxTemp5);
        vbox5.getChildren().addAll(data5, hBox5, clouds5, fifthDayInfo);

        centerHBox.setAlignment(Pos.CENTER);
        generalPane.getStyleClass().add("pane");
        topVBox.getStyleClass().add(STYLE_CLASS_CENTRAL_VBOX);
        String cityN = cityName.getCityName();

        LabelPattern cityName = new LabelPattern(cityN, STYLE_CLASS_STANDARD_LABEL);
        topVBox.setAlignment(Pos.TOP_CENTER);

        LabelPattern temperature = new LabelPattern("Temperature", STYLE_CLASS_STANDARD_LABEL);
        LabelPattern min = new LabelPattern("min:", STYLE_CLASS_STANDARD_LABEL);
        LabelPattern max = new LabelPattern("max:", STYLE_CLASS_STANDARD_LABEL);
        LabelPattern tempAnswer = new LabelPattern(parser.getTemperature(), STYLE_CLASS_STANDARD_LABEL);
        LabelPattern minAnswer = new LabelPattern(parser.getMinTemp(), STYLE_CLASS_STANDARD_LABEL);
        LabelPattern maxAnswer = new LabelPattern(parser.getMaxTemp(), STYLE_CLASS_STANDARD_LABEL);
        LabelPattern clouds = new LabelPattern(parser.getClouds(), STYLE_CLASS_STANDARD_LABEL);

        topCentralMiddleHBox.setAlignment(Pos.TOP_CENTER);
        topCentralMiddleHBox.getChildren().addAll(min, minAnswer, max, maxAnswer);
        topVBox.getChildren().addAll(cityName, temperature, tempAnswer, topCentralMiddleHBox, clouds);

        ButtonsPattern backButton = new ButtonsPattern(150, 50, "Back", STYLE_CLASS_BACK_BUTTON);
        bottomPane.setPrefSize(400, 150);

        backButton.setOnAction(event -> {
            secondWindow.close();
            BackButtonController controller = new BackButtonController(parser);
            controller.deletingTwoValues();
            firstWindow.startWin();
        });

        topPane.setLeft(topLeftInsert);
        topPane.setRight(topRightInsert);
        topPane.setCenter(topVBox);
        BorderPane.setMargin(topVBox, margin);
        generalPane.setTop(topPane);

        InfoButtonsControllers todayController = new TodayInfoController(this, parser);
        InfoButtonsControllers tomorrowController = new TomorrowInfo(this, parser);
        InfoButtonsControllers afterTomorrowController = new AfterTomorrowInfoController(this, parser);
        InfoButtonsControllers fourDayController = new FourInfoController(this, parser);
        InfoButtonsControllers fifthDayController = new FifthInfoController(this, parser);

        todayInfo.setOnAction(event -> todayController.putDataToDiagram());
        infoTomorrow.setOnAction(event -> tomorrowController.putDataToDiagram());
        afterTomorrowInfo.setOnAction(event -> afterTomorrowController.putDataToDiagram());
        fourDayInfo.setOnAction(event -> fourDayController.putDataToDiagram());
        fifthDayInfo.setOnAction(event -> fifthDayController.putDataToDiagram());

        temp.setName(" °C ");
        diagram.getData().addAll(temp);
        centerHBox.getChildren().addAll(vbox1, vbox2, vbox3, vbox4, vbox5);
        centerVbox.getChildren().addAll(centerHBox, diagram);

        bottomPane.setCenter(backButton);
        generalPane.setCenter(centerVbox);
        BorderPane.setMargin(centerVbox, margin);
        generalPane.setBottom(bottomPane);

        Scene secondWindowScene = new Scene(generalPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        secondWindowScene.getStylesheets().add(stylesheet);

        secondWindow.getIcons().add(new Image("weather_icon.png"));
        secondWindow.setMinWidth(WINDOW_MIN_WIDTH);
        secondWindow.setMinHeight(WINDOW_MIN_HEIGHT);
        secondWindow.setScene(secondWindowScene);
        secondWindow.setTitle(WINDOW_TITLE);
        secondWindow.show();
    }

    public XYChart.Series getTemp() { return temp; }
}