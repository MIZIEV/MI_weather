package View;

import Controller.*;
import Controller.ControllersForDetails.FourDayInfControl;
import Controller.ControllersForDetails.SecondDayInfControl;
import Controller.ControllersForDetails.ThirdDayInfControl;
import Controller.ControllersForDiagram.*;
import Model.CityName;
import Model.DataAnalyser;
import Model.JSONDataParser;
import animatefx.animation.FadeIn;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    private final static String STYLE_CLASS_CENTRAL_VBOX = "Central-VBox";
    private final static String STYLE_CLASS_FIRST_VBOX = "First-small-VBox";
    private final static String STYLE_CLASS_SECOND_VBOX = "Second-small-VBox";
    private final static String STYLE_CLASS_THIRD_VBOX = "Third-small-VBox";
    private final static String STYLE_CLASS_FOURTH_VBOX = "Fourth-small-VBox";
    private final static String STYLE_CLASS_FIFTH_VBOX = "Fifth-small-VBox";

    private final static short VBOX_PREF_WIDTH = 150;
    private final static short VBOX_PREF_HEIGHT = 150;

    private final static short WINDOW_WIDTH = 1366;
    private final static short WINDOW_HEIGHT = 768;
    private final static short WINDOW_MIN_WIDTH = 880;
    private final static short WINDOW_MIN_HEIGHT = 550;

    public SecondWindow(CityName name, JSONDataParser jsonParser, FirstWindow firstW) {
        this.cityName = name;
        this.parser = jsonParser;
        this.firstWindow = firstW;
    }

    public void startWin() {
        String stylesheet = getClass().getResource("/SecondWindow.css").toExternalForm();
        GeneralInfo detailInfo = new GeneralInfo(parser);

        Insets margin = new Insets(20);
        DataAnalyser analyser = new DataAnalyser();
        // creating diagram
        NumberAxis XCoordinateTime = new NumberAxis(0, 24, 3);
        NumberAxis YCoordinateDegrees = new NumberAxis(0, (analyser.maxTemp(parser.getTempMap()) - 273) + 5, 5);
        AreaChart<Number, Number> diagram = new AreaChart<>(XCoordinateTime, YCoordinateDegrees);
        diagram.setTitle("All day temperature");
        // creating main panels
        BorderPane generalPane = new BorderPane();
        BorderPane topPane = new BorderPane();
        BorderPane centralPane = new BorderPane();
        VBox centerVbox = new VBox(20);

        VBox rightTopMenu = new VBox();
        VBox rightCentralMenu = new VBox();
        rightTopMenu.setPrefWidth(65);
        rightCentralMenu.setPrefWidth(65);
        rightTopMenu.getStyleClass().add("left-menu-VBox");
        rightCentralMenu.getStyleClass().add("left-menu-VBox");
        Button backButton = new Button("", new ImageView("/right_menu_back_icon.jpg"));
        backButton.getStyleClass().add("Button-left-menu");
        rightCentralMenu.setAlignment(Pos.BOTTOM_CENTER);
        rightCentralMenu.getChildren().addAll(backButton);

        VBox leftTopMenu = new VBox();
        VBox leftCentralMenu = new VBox();
        VBox leftBottomMenu = new VBox();
        leftCentralMenu.setAlignment(Pos.BOTTOM_CENTER);
        Button diagramButton = new Button("", new ImageView("/left_menu_diagram_icon.jpg"));
        Button detailButton = new Button("", new ImageView("/left_menu_info_icon.jpg"));
        Button DBInfoButton = new Button("", new ImageView("/left_menu_DBInfo_icon.jpg"));
        diagramButton.getStyleClass().add("Button-left-menu");
        detailButton.getStyleClass().add("Button-left-menu");
        DBInfoButton.getStyleClass().add("Button-left-menu");
        leftTopMenu.setPrefWidth(65);
        leftCentralMenu.setPrefWidth(65);
        leftBottomMenu.setPrefWidth(65);
        leftTopMenu.getStyleClass().add("left-menu-VBox");
        leftCentralMenu.getStyleClass().add("left-menu-VBox");
        leftBottomMenu.getStyleClass().add("left-menu-VBox");
        leftCentralMenu.getChildren().addAll(DBInfoButton, diagramButton, detailButton);

        VBox topVBox = new VBox();
        HBox centerHBox = new HBox(20);
        HBox topCentralMiddleHBox = new HBox(10);
        topVBox.setMaxWidth(830);

        VBoxPattern vbox1 = new VBoxPattern(10, VBOX_PREF_WIDTH, VBOX_PREF_HEIGHT, Pos.CENTER, STYLE_CLASS_FIRST_VBOX);
        int minTempToday = analyser.minTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getIndexList().get(0))).
                headMap(parser.getKeysForMap().get(parser.getIndexList().get(1) + 1))) - 273;
        int maxTempToday = analyser.maxTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getIndexList().get(0))).
                headMap(parser.getKeysForMap().get(parser.getIndexList().get(1) + 1))) - 273;
        HBox hBox1 = new HBox(10);
        hBox1.setAlignment(Pos.CENTER);
        LabelPattern data1 = new LabelPattern(parser.getTodayDate(), STYLE_CLASS_SMALL_LABEL);
        LabelPattern minTemp1 = new LabelPattern("min " + minTempToday + "°C;", STYLE_CLASS_SMALL_LABEL);
        LabelPattern maxTemp1 = new LabelPattern("max " + maxTempToday + "°C", STYLE_CLASS_SMALL_LABEL);
        LabelPattern clouds1 = new LabelPattern(parser.getWeatherToday(), STYLE_CLASS_SMALL_LABEL);
        ButtonsPattern todayInfo = new ButtonsPattern(70, 40, "info", STYLE_CLASS_BUTTON_INSIDE_VBOX);
        hBox1.getChildren().addAll(minTemp1, maxTemp1);
        vbox1.getChildren().addAll(data1, hBox1, clouds1, todayInfo);

        VBoxPattern vbox2 = new VBoxPattern(10, VBOX_PREF_WIDTH, VBOX_PREF_HEIGHT, Pos.CENTER, STYLE_CLASS_SECOND_VBOX);
        HBox hBox2 = new HBox(10);
        hBox2.setAlignment(Pos.CENTER);
        LabelPattern data2 = new LabelPattern(parser.getDateSecondDay(), STYLE_CLASS_SMALL_LABEL);
        LabelPattern minTemp2 = new LabelPattern("min " + (analyser.minTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getIndexList().get(1))).
                headMap(parser.getKeysForMap().get(parser.getIndexList().get(2) + 1))) - 273) + "°C;", STYLE_CLASS_SMALL_LABEL);
        LabelPattern maxTemp2 = new LabelPattern("max " + (analyser.maxTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getIndexList().get(1))).
                headMap(parser.getKeysForMap().get(parser.getIndexList().get(2) + 1))) - 273) + "°C", STYLE_CLASS_SMALL_LABEL);
        LabelPattern clouds2 = new LabelPattern(parser.getWeatherSecondDay(), STYLE_CLASS_SMALL_LABEL);
        ButtonsPattern infoTomorrow = new ButtonsPattern(70, 40, "info", STYLE_CLASS_BUTTON_INSIDE_VBOX);
        hBox2.getChildren().addAll(minTemp2, maxTemp2);
        vbox2.getChildren().addAll(data2, hBox2, clouds2, infoTomorrow);

        VBoxPattern vbox3 = new VBoxPattern(10, VBOX_PREF_WIDTH, VBOX_PREF_HEIGHT, Pos.CENTER, STYLE_CLASS_THIRD_VBOX);
        HBox hBox3 = new HBox(10);
        hBox3.setAlignment(Pos.CENTER);
        LabelPattern data3 = new LabelPattern(parser.getDateThirdDay(), STYLE_CLASS_SMALL_LABEL);
        LabelPattern minTemp3 = new LabelPattern("min " + (analyser.minTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getIndexList().get(2))).
                headMap(parser.getKeysForMap().get(parser.getIndexList().get(3) + 1))) - 273) + "°C;", STYLE_CLASS_SMALL_LABEL);
        LabelPattern maxTemp3 = new LabelPattern("max " + (analyser.maxTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getIndexList().get(2))).
                headMap(parser.getKeysForMap().get(parser.getIndexList().get(3) + 1))) - 273) + "°C", STYLE_CLASS_SMALL_LABEL);
        LabelPattern clouds3 = new LabelPattern(parser.getWeatherThirdDay(), STYLE_CLASS_SMALL_LABEL);
        ButtonsPattern afterTomorrowInfo = new ButtonsPattern(70, 40, "info", STYLE_CLASS_BUTTON_INSIDE_VBOX);
        hBox3.getChildren().addAll(minTemp3, maxTemp3);
        vbox3.getChildren().addAll(data3, hBox3, clouds3, afterTomorrowInfo);

        VBoxPattern vbox4 = new VBoxPattern(10, VBOX_PREF_WIDTH, VBOX_PREF_HEIGHT, Pos.CENTER, STYLE_CLASS_FOURTH_VBOX);
        HBox hBox4 = new HBox(10);
        hBox4.setAlignment(Pos.CENTER);
        LabelPattern data4 = new LabelPattern(parser.getDateFourDay(), STYLE_CLASS_SMALL_LABEL);
        LabelPattern minTemp4 = new LabelPattern("min " + (analyser.minTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getIndexList().get(3))).
                headMap(parser.getKeysForMap().get(parser.getIndexList().get(4) + 1))) - 273) + "°C;", STYLE_CLASS_SMALL_LABEL);
        LabelPattern maxTemp4 = new LabelPattern("max " + (analyser.maxTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getIndexList().get(3))).
                headMap(parser.getKeysForMap().get(parser.getIndexList().get(4) + 1))) - 273) + "°C", STYLE_CLASS_SMALL_LABEL);
        LabelPattern clouds4 = new LabelPattern(parser.getWeatherFourthDay(), STYLE_CLASS_SMALL_LABEL);
        ButtonsPattern fourDayInfo = new ButtonsPattern(70, 40, "info", STYLE_CLASS_BUTTON_INSIDE_VBOX);
        hBox4.getChildren().addAll(minTemp4, maxTemp4);
        vbox4.getChildren().addAll(data4, hBox4, clouds4, fourDayInfo);

        VBoxPattern vbox5 = new VBoxPattern(10, VBOX_PREF_WIDTH, VBOX_PREF_HEIGHT, Pos.CENTER, STYLE_CLASS_FIFTH_VBOX);
        HBox hBox5 = new HBox(10);
        hBox5.setAlignment(Pos.CENTER);
        LabelPattern data5 = new LabelPattern(parser.getDateFifthDay(), STYLE_CLASS_SMALL_LABEL);
        LabelPattern minTemp5 = new LabelPattern("min " + (analyser.minTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getIndexList().get(4))).
                headMap(parser.getKeysForMap().get(parser.getIndexList().get(5) + 1))) - 273) + "°C;", STYLE_CLASS_SMALL_LABEL);
        LabelPattern maxTemp5 = new LabelPattern("max " + (analyser.maxTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getIndexList().get(4))).
                headMap(parser.getKeysForMap().get(parser.getIndexList().get(5) + 1))) - 273) + "°C", STYLE_CLASS_SMALL_LABEL);
        LabelPattern clouds5 = new LabelPattern(parser.getWeatherFifthDay(), STYLE_CLASS_SMALL_LABEL);
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
        LabelPattern tempAnswer = new LabelPattern(parser.getTempNow(), STYLE_CLASS_STANDARD_LABEL);
        LabelPattern minAnswer = new LabelPattern(minTempToday + "°C;", STYLE_CLASS_STANDARD_LABEL);
        LabelPattern maxAnswer = new LabelPattern(maxTempToday + "°C", STYLE_CLASS_STANDARD_LABEL);
        LabelPattern clouds = new LabelPattern(parser.getWeatherNow(), STYLE_CLASS_STANDARD_LABEL);

        topCentralMiddleHBox.setAlignment(Pos.TOP_CENTER);
        topCentralMiddleHBox.getChildren().addAll(min, minAnswer, max, maxAnswer);
        topVBox.getChildren().addAll(cityName, temperature, tempAnswer, topCentralMiddleHBox, clouds);

        backButton.setOnAction(event -> {
            secondWindow.close();
            BackButtonController controller = new BackButtonController(parser);
            controller.deletingTwoValues();
            firstWindow.startWin();
        });

        topPane.setLeft(leftTopMenu);
        topPane.setRight(rightTopMenu);
        topPane.setCenter(topVBox);
        BorderPane.setMargin(topVBox, margin);
        generalPane.setTop(topPane);

        InfoButtonsControllers todayController = new TodayInfoController(this, parser);
        InfoButtonsControllers tomorrowController = new SecondDayInfoController(this, parser);
        InfoButtonsControllers afterTomorrowController = new ThirdDayInfoController(this, parser);
        InfoButtonsControllers fourDayController = new FourDayInfoController(this, parser);
        InfoButtonsControllers fifthDayController = new FifthDayInfoController(this, parser);

        TodayGenInfController todayGenInfController = new TodayGenInfController(detailInfo, parser, analyser);
        SecondDayInfControl secondDayInfControl = new SecondDayInfControl(detailInfo, parser, analyser);
        ThirdDayInfControl thirdDayInfControl = new ThirdDayInfControl(detailInfo, parser, analyser);
        FourDayInfControl fourDayInfControl = new FourDayInfControl(detailInfo, parser, analyser);

        todayInfo.setOnAction(event -> {
            new FadeIn(diagram).play();
            todayGenInfController.putDataToPane();
            todayController.putDataToDiagram();
        });
        infoTomorrow.setOnAction(event -> {
            new FadeIn(diagram).play();
            tomorrowController.putDataToDiagram();
            secondDayInfControl.putDataToPane();
        });
        afterTomorrowInfo.setOnAction(event -> {
            new FadeIn(diagram).play();
            afterTomorrowController.putDataToDiagram();
            thirdDayInfControl.putDataToPane();
        });
        fourDayInfo.setOnAction(event -> {
            new FadeIn(diagram).play();
            fourDayController.putDataToDiagram();
            fourDayInfControl.putDataToPane();
        });
        fifthDayInfo.setOnAction(event -> {
            new FadeIn(diagram).play();
            fifthDayController.putDataToDiagram();
        });

        temp.setName(" °C ");
        diagram.getData().addAll(temp);
        centerHBox.getChildren().addAll(vbox1, vbox2, vbox3, vbox4, vbox5);
        BorderPane paneForDiagram = new BorderPane();
        paneForDiagram.setCenter(diagram);
        paneForDiagram.getStyleClass().add("Pane-for-diagram");
        centerVbox.getChildren().addAll(centerHBox, centralPane);

        diagramButton.setOnAction(event -> {
            centralPane.getChildren().clear();
            new FadeIn(paneForDiagram).play();
            centralPane.setCenter(paneForDiagram);
        });
        detailButton.setOnAction(event -> {
            centralPane.getChildren().clear();
            centralPane.setCenter(detailInfo.showInfo());
        });

        generalPane.setCenter(centerVbox);
        BorderPane.setMargin(centerVbox, margin);
        generalPane.setLeft(leftCentralMenu);
        generalPane.setRight(rightCentralMenu);

        Scene secondWindowScene = new Scene(generalPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        secondWindowScene.getStylesheets().add(stylesheet);

        secondWindow.getIcons().add(new Image("weather_icon.png"));
        secondWindow.setMinWidth(WINDOW_MIN_WIDTH);
        secondWindow.setMinHeight(WINDOW_MIN_HEIGHT);
        secondWindow.setScene(secondWindowScene);
        secondWindow.setTitle(WINDOW_TITLE);
        new FadeIn(generalPane).play();
        secondWindow.show();
    }

    public XYChart.Series getTemp() {
        return temp;
    }
}