package prog.view;

import prog.controller.WindDirectControl;
import prog.web.JSONParser;
import prog.view.patterns.controls.LabelPattern;
import animatefx.animation.FadeIn;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DetailInfo {

    private final JSONParser parser;

    private int minTempInt;
    private int maxTempInt;
    private int windDirectionInt;
    private double windSpeedDouble;
    private double humidityDouble;
    private String weatherString;

    private final static String LABEL_STYLE = "Label";
    private final static String PANE_STYLE = "Border-Pane";
    private final static short PANE_WIDTH = 1200;
    private final static short PANE_HEIGHT = 400;
    private final static byte INSIDE_PANE_MARGIN = 50;
    private final static byte INSIDE_VBOX_SPACING = 20;
    private final static byte INDICATOR_SIZE = 80;
    private final static byte CENTRAL_HBOX_SPACING = 100;

    public DetailInfo(JSONParser jsonParser) {
        this.parser = jsonParser;
    }

    public BorderPane showInfo(String stylesheet) {

        String paneStylesheet = getClass().getResource(stylesheet + "GeneralInfoStyle.css").toExternalForm();

        Insets margin = new Insets(INSIDE_PANE_MARGIN);
        WindDirectControl windController = new WindDirectControl();
        WeatherImage weatherImage = new WeatherImage(parser);

        BorderPane detailInfoPane = new BorderPane();
        detailInfoPane.getStyleClass().add(PANE_STYLE);
        HBox mainHBox = new HBox(CENTRAL_HBOX_SPACING);
        mainHBox.setAlignment(Pos.CENTER);
        BorderPane.setMargin(mainHBox, margin);
        //----------------------------------------creating all VBox with information
        VBox tempBlock = new VBox(INSIDE_VBOX_SPACING);
        LabelPattern titleFirstVBox = new LabelPattern("Temperature:", LABEL_STYLE);
        LabelPattern maxTemp = new LabelPattern("max temp: " + maxTempInt + "°C", LABEL_STYLE);
        LabelPattern minTemp = new LabelPattern("min temp: " + minTempInt + "°C", LABEL_STYLE);
        tempBlock.getChildren().addAll(titleFirstVBox, maxTemp, minTemp);

        VBox weatherBlock = new VBox(INSIDE_VBOX_SPACING);
        LabelPattern titleSecondVBox = new LabelPattern("Weather:", LABEL_STYLE);
        LabelPattern weatherVar = new LabelPattern(weatherString, LABEL_STYLE);
        ImageView weatherImages = weatherImage.selectImage(weatherString, stylesheet);
        weatherBlock.getChildren().addAll(titleSecondVBox, weatherVar, weatherImages);

        VBox windBlock = new VBox(INSIDE_VBOX_SPACING);
        LabelPattern titleThirdVBox = new LabelPattern("Wind:", LABEL_STYLE);
        LabelPattern windSpeed = new LabelPattern("Wind speed: " + windSpeedDouble + " m/s", LABEL_STYLE);
        LabelPattern windDirection = new LabelPattern("Wind direction: " + windDirectionInt + "°", LABEL_STYLE);
        ImageView windImage = new ImageView(stylesheet + "WindDirectionIcons/wind_direction_" + windController.getDirection(windDirectionInt) + ".jpg");
        windBlock.getChildren().addAll(titleThirdVBox, windSpeed, windDirection, windImage);

        VBox otherInoBlock = new VBox(INSIDE_VBOX_SPACING);
        otherInoBlock.setAlignment(Pos.TOP_CENTER);
        LabelPattern otherInfo = new LabelPattern("Other information:", LABEL_STYLE);
        LabelPattern humidity = new LabelPattern("Humidity:", LABEL_STYLE);
        ProgressIndicator humidityBar = new ProgressIndicator();
        humidityBar.getStyleClass().add("Progress-Indicator");
        humidityBar.setProgress(humidityDouble / 100);
        humidityBar.setPrefSize(INDICATOR_SIZE, INDICATOR_SIZE);
        otherInoBlock.getChildren().addAll(otherInfo, humidity, humidityBar);
        //----------------------------------------add all info blocks to central HBox
        mainHBox.getChildren().addAll(tempBlock, weatherBlock, windBlock, otherInoBlock);
        detailInfoPane.setCenter(mainHBox);
        detailInfoPane.getStylesheets().add(paneStylesheet);
        detailInfoPane.setPrefSize(PANE_WIDTH, PANE_HEIGHT);
        new FadeIn(detailInfoPane).play();

        return detailInfoPane;
    }

    public void setMinTempInt(int minT) {
        this.minTempInt = minT;
    }

    public void setMaxTempInt(int maxT) {
        this.maxTempInt = maxT;
    }

    public void setWeatherString(String weather) {
        this.weatherString = weather;
    }

    public void setHumidityDouble(double h) {
        this.humidityDouble = h;
    }

    public void setWindSpeedDouble(double speed) {
        this.windSpeedDouble = speed;
    }

    public void setWindDirectionInt(int windDirection) { this.windDirectionInt = windDirection; }
}