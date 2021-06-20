package View;

import Controller.ControllersForDetails.*;
import Controller.ControllersForDiagram.*;
import Model.CityName;
import Model.DataAnalyser;
import Model.JSONDataParser;
import View.Patterns.ButtonsPattern;
import View.Patterns.LabelPattern;
import View.Patterns.ToggleButtonPattern;
import View.Patterns.VBoxPattern;
import animatefx.animation.FadeIn;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SecondWindow {

    private final Stage secondWindow = new Stage();
    private final CityName cityName;
    private final FirstWindow firstWindow;
    private final JSONDataParser parser;

    private final static String WINDOW_TITLE = "MI weather";
    private final static String SIDE_BAR_STYLE = "left-menu-VBox";
    private final static String STYLE_CLASS_SMALL_LABEL = "Label-small";
    private final static String STYLE_CLASS_STANDARD_LABEL = "Label-standard";
    private final static String STYLE_CLASS_BUTTON_INSIDE_VBOX = "Button-insideVBox";
    private final static String STYLE_CLASS_CENTRAL_VBOX = "Central-VBox";
    private final static String STYLE_CLASS_FIRST_VBOX = "First-small-VBox";
    private final static String STYLE_CLASS_SECOND_VBOX = "Second-small-VBox";
    private final static String STYLE_CLASS_THIRD_VBOX = "Third-small-VBox";
    private final static String STYLE_CLASS_FOURTH_VBOX = "Fourth-small-VBox";
    private final static String STYLE_CLASS_FIFTH_VBOX = "Fifth-small-VBox";

    private final static String TOG_BUT_DIAGRAM_STYLE = "Toggle-Button-diagram";
    private final static String TOG_BUT_INFO_STYLE = "Toggle-Button-info";
    private final static String TOG_BUT_DB_INFO_STYLE = "Toggle-Button-DB-info";
    private final static String BACK_BUTTON_STYLE = "Back-Button";

    private final static short VBOX_PREF_WIDTH = 150;
    private final static short VBOX_PREF_HEIGHT = 150;

    private final static short WINDOW_WIDTH = 1366;
    private final static short WINDOW_HEIGHT = 768;
    private final static short WINDOW_MIN_WIDTH = 880;
    private final static short WINDOW_MIN_HEIGHT = 550;

    private final static byte CENTRAL_HBOX_SPICING = 10;
    private final static byte CENTRAL_VBOX_SPICING = 10;
    private final static byte VBOX_BUTTON_WIDTH = 70;
    private final static byte VBOX_BUTTON_HEIGHT = 40;
    private final static byte SIDEBAR_WIDTH = 65;
    private final static byte MARGIN = 20;

    private final static byte TOG_BUT_WIDTH = 65;
    private final static byte TOG_BUT_HEIGHT = 90;

    public SecondWindow(CityName name, JSONDataParser jsonParser, FirstWindow firstW) {
        this.cityName = name;
        this.parser = jsonParser;
        this.firstWindow = firstW;
    }

    public void startWin() {
        String stylesheet = getClass().getResource("/VisualStyles/SecondWindow.css").toExternalForm();
        Insets margin = new Insets(MARGIN);
        DataAnalyser analyser = new DataAnalyser();
        GeneralInfo detailInfo = new GeneralInfo(parser);
        Diagram diagram = new Diagram(parser, analyser);
        DBInfo dbInfo = new DBInfo();
        //____________________________________________________creating the main window components
        BorderPane mainPane = new BorderPane();
        BorderPane topPane = new BorderPane();
        BorderPane centralPane = new BorderPane();
        VBox mainCentralVbox = new VBox(20);
        HBox centralHBox = new HBox(20);
        centralHBox.setAlignment(Pos.CENTER);

        VBox topVBox = new VBox();
        HBox topCentralMiddleHBox = new HBox(10);
        topVBox.getStyleClass().add(STYLE_CLASS_CENTRAL_VBOX);
        String cityN = cityName.getCityName();
        topVBox.setMaxWidth(830);
        LabelPattern cityName = new LabelPattern(cityN, STYLE_CLASS_STANDARD_LABEL);
        topVBox.setAlignment(Pos.TOP_CENTER);
        LabelPattern temperature = new LabelPattern("Temperature", STYLE_CLASS_STANDARD_LABEL);
        LabelPattern min = new LabelPattern(parser.getTodayIndex(), "min ", parser, analyser, STYLE_CLASS_STANDARD_LABEL);
        LabelPattern max = new LabelPattern(parser.getTodayIndex(), "max ", parser, analyser, STYLE_CLASS_STANDARD_LABEL);
        LabelPattern tempAnswer = new LabelPattern(parser.getTempNow(), STYLE_CLASS_STANDARD_LABEL);
        LabelPattern clouds = new LabelPattern(parser.getWeatherNow(), STYLE_CLASS_STANDARD_LABEL);
        topCentralMiddleHBox.setAlignment(Pos.TOP_CENTER);
        topCentralMiddleHBox.getChildren().addAll(min, max);
        topVBox.getChildren().addAll(cityName, temperature, tempAnswer, topCentralMiddleHBox, clouds);

        VBoxPattern vbox1 = new VBoxPattern(CENTRAL_VBOX_SPICING, VBOX_PREF_WIDTH, VBOX_PREF_HEIGHT, Pos.CENTER, STYLE_CLASS_FIRST_VBOX);
        HBox hBox1 = new HBox(CENTRAL_HBOX_SPICING);
        hBox1.setAlignment(Pos.CENTER);
        LabelPattern data1 = new LabelPattern(parser.getTodayDate(), STYLE_CLASS_SMALL_LABEL);
        LabelPattern minTemp1 = new LabelPattern(parser.getTodayIndex(), "min ", parser, analyser, STYLE_CLASS_SMALL_LABEL);
        LabelPattern maxTemp1 = new LabelPattern(parser.getTodayIndex(), "max ", parser, analyser, STYLE_CLASS_SMALL_LABEL);
        LabelPattern clouds1 = new LabelPattern(parser.getWeatherToday(), STYLE_CLASS_SMALL_LABEL);
        ButtonsPattern todayInfo = new ButtonsPattern(VBOX_BUTTON_WIDTH, VBOX_BUTTON_HEIGHT, "info", STYLE_CLASS_BUTTON_INSIDE_VBOX);
        hBox1.getChildren().addAll(minTemp1, maxTemp1);
        vbox1.getChildren().addAll(data1, hBox1, clouds1, todayInfo);

        VBoxPattern vbox2 = new VBoxPattern(CENTRAL_VBOX_SPICING, VBOX_PREF_WIDTH, VBOX_PREF_HEIGHT, Pos.CENTER, STYLE_CLASS_SECOND_VBOX);
        HBox hBox2 = new HBox(CENTRAL_HBOX_SPICING);
        hBox2.setAlignment(Pos.CENTER);
        LabelPattern data2 = new LabelPattern(parser.getDateSecondDay(), STYLE_CLASS_SMALL_LABEL);
        LabelPattern minTemp2 = new LabelPattern(parser.getSecondDayIndex(), "min ", parser, analyser, STYLE_CLASS_SMALL_LABEL);
        LabelPattern maxTemp2 = new LabelPattern(parser.getSecondDayIndex(), "max ", parser, analyser, STYLE_CLASS_SMALL_LABEL);
        LabelPattern clouds2 = new LabelPattern(parser.getWeatherSecondDay(), STYLE_CLASS_SMALL_LABEL);
        ButtonsPattern infoTomorrow = new ButtonsPattern(VBOX_BUTTON_WIDTH, VBOX_BUTTON_HEIGHT, "info", STYLE_CLASS_BUTTON_INSIDE_VBOX);
        hBox2.getChildren().addAll(minTemp2, maxTemp2);
        vbox2.getChildren().addAll(data2, hBox2, clouds2, infoTomorrow);

        VBoxPattern vbox3 = new VBoxPattern(CENTRAL_VBOX_SPICING, VBOX_PREF_WIDTH, VBOX_PREF_HEIGHT, Pos.CENTER, STYLE_CLASS_THIRD_VBOX);
        HBox hBox3 = new HBox(CENTRAL_HBOX_SPICING);
        hBox3.setAlignment(Pos.CENTER);
        LabelPattern data3 = new LabelPattern(parser.getDateThirdDay(), STYLE_CLASS_SMALL_LABEL);
        LabelPattern minTemp3 = new LabelPattern(parser.getFifthDayIndex(), "min ", parser, analyser, STYLE_CLASS_SMALL_LABEL);
        LabelPattern maxTemp3 = new LabelPattern(parser.getThirdDayIndex(), "max ", parser, analyser, STYLE_CLASS_SMALL_LABEL);
        LabelPattern clouds3 = new LabelPattern(parser.getWeatherThirdDay(), STYLE_CLASS_SMALL_LABEL);
        ButtonsPattern afterTomorrowInfo = new ButtonsPattern(VBOX_BUTTON_WIDTH, VBOX_BUTTON_HEIGHT, "info", STYLE_CLASS_BUTTON_INSIDE_VBOX);
        hBox3.getChildren().addAll(minTemp3, maxTemp3);
        vbox3.getChildren().addAll(data3, hBox3, clouds3, afterTomorrowInfo);

        VBoxPattern vbox4 = new VBoxPattern(CENTRAL_VBOX_SPICING, VBOX_PREF_WIDTH, VBOX_PREF_HEIGHT, Pos.CENTER, STYLE_CLASS_FOURTH_VBOX);
        HBox hBox4 = new HBox(CENTRAL_HBOX_SPICING);
        hBox4.setAlignment(Pos.CENTER);
        LabelPattern data4 = new LabelPattern(parser.getDateFourDay(), STYLE_CLASS_SMALL_LABEL);
        LabelPattern minTemp4 = new LabelPattern(parser.getFourDayIndex(), "min ", parser, analyser, STYLE_CLASS_SMALL_LABEL);
        LabelPattern maxTemp4 = new LabelPattern(parser.getFourDayIndex(), "max ", parser, analyser, STYLE_CLASS_SMALL_LABEL);
        LabelPattern clouds4 = new LabelPattern(parser.getWeatherFourthDay(), STYLE_CLASS_SMALL_LABEL);
        ButtonsPattern fourDayInfo = new ButtonsPattern(VBOX_BUTTON_WIDTH, VBOX_BUTTON_HEIGHT, "info", STYLE_CLASS_BUTTON_INSIDE_VBOX);
        hBox4.getChildren().addAll(minTemp4, maxTemp4);
        vbox4.getChildren().addAll(data4, hBox4, clouds4, fourDayInfo);

        VBoxPattern vbox5 = new VBoxPattern(CENTRAL_VBOX_SPICING, VBOX_PREF_WIDTH, VBOX_PREF_HEIGHT, Pos.CENTER, STYLE_CLASS_FIFTH_VBOX);
        HBox hBox5 = new HBox(CENTRAL_HBOX_SPICING);
        hBox5.setAlignment(Pos.CENTER);
        LabelPattern data5 = new LabelPattern(parser.getDateFifthDay(), STYLE_CLASS_SMALL_LABEL);
        LabelPattern minTemp5 = new LabelPattern(parser.getFifthDayIndex(), "min ", parser, analyser, STYLE_CLASS_SMALL_LABEL);
        LabelPattern maxTemp5 = new LabelPattern(parser.getFifthDayIndex(), "max ", parser, analyser, STYLE_CLASS_SMALL_LABEL);
        LabelPattern clouds5 = new LabelPattern(parser.getWeatherFifthDay(), STYLE_CLASS_SMALL_LABEL);
        ButtonsPattern fifthDayInfo = new ButtonsPattern(VBOX_BUTTON_WIDTH, VBOX_BUTTON_HEIGHT, "info", STYLE_CLASS_BUTTON_INSIDE_VBOX);
        hBox5.getChildren().addAll(minTemp5, maxTemp5);
        vbox5.getChildren().addAll(data5, hBox5, clouds5, fifthDayInfo);

        mainPane.getStyleClass().add("pane");
        //____________________________________________________creating right side bar with back button
        VBoxPattern rightTopSideBar = new VBoxPattern(SIDEBAR_WIDTH, SIDE_BAR_STYLE);
        VBoxPattern rightCentralSideBar = new VBoxPattern(SIDEBAR_WIDTH, SIDE_BAR_STYLE);
        ButtonsPattern backButton = new ButtonsPattern(65, 90, BACK_BUTTON_STYLE);
        rightCentralSideBar.setAlignment(Pos.BOTTOM_CENTER);
        rightCentralSideBar.getChildren().addAll(backButton);
        //____________________________________________________creating left side bar with toggle buttons
        VBoxPattern leftTopMenu = new VBoxPattern(SIDEBAR_WIDTH, SIDE_BAR_STYLE);
        VBoxPattern leftCentralMenu = new VBoxPattern(SIDEBAR_WIDTH, SIDE_BAR_STYLE);
        leftCentralMenu.setAlignment(Pos.BOTTOM_CENTER);

        ToggleGroup leftButtonsGroup = new ToggleGroup();
        ToggleButtonPattern diagramButton = new ToggleButtonPattern(TOG_BUT_WIDTH, TOG_BUT_HEIGHT, TOG_BUT_DIAGRAM_STYLE);
        ToggleButtonPattern detailButton = new ToggleButtonPattern(TOG_BUT_WIDTH, TOG_BUT_HEIGHT, TOG_BUT_INFO_STYLE);
        ToggleButtonPattern DBInfoButton = new ToggleButtonPattern(TOG_BUT_WIDTH, TOG_BUT_HEIGHT, TOG_BUT_DB_INFO_STYLE);

        diagramButton.setToggleGroup(leftButtonsGroup);
        detailButton.setToggleGroup(leftButtonsGroup);
        DBInfoButton.setToggleGroup(leftButtonsGroup);

        leftCentralMenu.getChildren().addAll(DBInfoButton, diagramButton, detailButton);

        topPane.setLeft(leftTopMenu);
        topPane.setRight(rightTopSideBar);
        topPane.setCenter(topVBox);
        BorderPane.setMargin(topVBox, margin);
        mainPane.setTop(topPane);
        //____________________________________________________creating all controllers
        InfoButtonsControllers todayController = new TodayInfoController(diagram, parser);
        InfoButtonsControllers tomorrowController = new SecondDayInfoController(diagram, parser);
        InfoButtonsControllers afterTomorrowController = new ThirdDayInfoController(diagram, parser);
        InfoButtonsControllers fourDayController = new FourDayInfoController(diagram, parser);
        InfoButtonsControllers fifthDayController = new FifthDayInfoController(diagram, parser);

        TodayGenInfController todayGenInfController = new TodayGenInfController(detailInfo, parser, analyser);
        SecondDayInfControl secondDayInfControl = new SecondDayInfControl(detailInfo, parser, analyser);
        ThirdDayInfControl thirdDayInfControl = new ThirdDayInfControl(detailInfo, parser, analyser);
        FourDayInfControl fourDayInfControl = new FourDayInfControl(detailInfo, parser, analyser);
        FifthDayControl fifthDayControl = new FifthDayControl(detailInfo, parser, analyser);
        //____________________________________________________inside VBox buttons listeners
        todayInfo.setOnAction(event -> {
            if (diagramButton.isSelected()) todayController.putDataToDiagram();
            else if (detailButton.isSelected()) {
                todayGenInfController.putDataToPane();
                centralPane.getChildren().clear();
                todayController.clearDataFromDiagram(diagram);
                centralPane.setCenter(detailInfo.showInfo());
            }
        });
        infoTomorrow.setOnAction(event -> {
            if (diagramButton.isSelected()) tomorrowController.putDataToDiagram();
            else if (detailButton.isSelected()) {
                secondDayInfControl.putDataToPane();
                centralPane.getChildren().clear();
                tomorrowController.clearDataFromDiagram(diagram);
                centralPane.setCenter(detailInfo.showInfo());
            }
        });
        afterTomorrowInfo.setOnAction(event -> {
            if (diagramButton.isSelected()) afterTomorrowController.putDataToDiagram();
            else if (detailButton.isSelected()) {
                thirdDayInfControl.putDataToPane();
                centralPane.getChildren().clear();
                afterTomorrowController.clearDataFromDiagram(diagram);
                centralPane.setCenter(detailInfo.showInfo());
            }
        });
        fourDayInfo.setOnAction(event -> {
            if (diagramButton.isSelected()) fourDayController.putDataToDiagram();
            else if (detailButton.isSelected()) {
                fourDayInfControl.putDataToPane();
                centralPane.getChildren().clear();
                fourDayController.clearDataFromDiagram(diagram);
                centralPane.setCenter(detailInfo.showInfo());
            }
        });
        fifthDayInfo.setOnAction(event -> {
            if (diagramButton.isSelected()) fifthDayController.putDataToDiagram();
            else if (detailButton.isSelected()) {
                fifthDayControl.putDataToPane();
                centralPane.getChildren().clear();
                fifthDayController.clearDataFromDiagram(diagram);
                centralPane.setCenter(detailInfo.showInfo());
            }
        });
        //____________________________________________________toggle buttons listeners
        diagramButton.setOnAction(event -> {
            if (diagramButton.isSelected()) {
                centralPane.getChildren().clear();
                centralPane.setCenter(diagram.showDiagram());
            }
        });
        detailButton.setOnAction(event -> {
            if (detailButton.isSelected()) {
                todayGenInfController.putDataToPane();
                centralPane.getChildren().clear();
                centralPane.setCenter(detailInfo.showInfo());
            }
        });

        DBInfoButton.setOnAction(event -> {
            centralPane.getChildren().clear();
            centralPane.setCenter(dbInfo.showInfo());
        });

        backButton.setOnAction(event -> {
            secondWindow.close();
            BackButtonController controller = new BackButtonController(parser);
            controller.deletingTwoValues();
            firstWindow.startWin();
        });
        diagramButton.fire();   //<--- selected button by default

        centralHBox.getChildren().addAll(vbox1, vbox2, vbox3, vbox4, vbox5);
        mainCentralVbox.getChildren().addAll(centralHBox, centralPane);

        mainPane.setCenter(mainCentralVbox);
        BorderPane.setMargin(mainCentralVbox, margin);
        mainPane.setLeft(leftCentralMenu);
        mainPane.setRight(rightCentralSideBar);

        Scene secondWindowScene = new Scene(mainPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        secondWindowScene.getStylesheets().add(stylesheet);

        secondWindow.getIcons().add(new Image("weather_icon.png"));
        secondWindow.setMinWidth(WINDOW_MIN_WIDTH);
        secondWindow.setMinHeight(WINDOW_MIN_HEIGHT);
        secondWindow.setScene(secondWindowScene);
        secondWindow.setTitle(WINDOW_TITLE);
        new FadeIn(mainPane).play();
        secondWindow.show();
    }
}