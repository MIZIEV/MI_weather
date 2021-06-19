package View;

import Controller.WindDirectionControl;
import Model.JSONDataParser;
import animatefx.animation.FadeIn;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GeneralInfo {

    private final JSONDataParser parser;

    private int minTempInt;
    private int maxTempInt;
    private int windDirectionInt;
    private double windSpeedDouble;
    private double humidityDouble;
    private String weatherString = "clouds";

    private final static String LABEL_STYLE ="Label";
    private final static String PANE_STYLE ="Border-Pane";
    private final static short PANE_WIDTH = 1200;
    private final static short PANE_HEIGHT = 400;
    private final static byte INSIDE_PANE_MARGIN = 50;
    private final static byte INSIDE_VBOX_SPACING = 10;
    private final static byte INDICATOR_WIDTH = 80;
    private final static byte INDICATOR_HEIGHT = 80;

    public GeneralInfo(JSONDataParser jsonParser) {
        this.parser = jsonParser;
    }

    public BorderPane showInfo() {

        String stylesheet = getClass().getResource("/GeneralInfoStyle.css").toExternalForm();
        Insets margin = new Insets(INSIDE_PANE_MARGIN);
        WindDirectionControl windController = new WindDirectionControl();
        WeatherImage weatherImage = new WeatherImage(parser);

        BorderPane infoPane = new BorderPane();
        infoPane.getStyleClass().add(PANE_STYLE);
        HBox centralHBox = new HBox(120);
        centralHBox.setAlignment(Pos.CENTER);
        BorderPane.setMargin(centralHBox, margin);

        VBox vBox1 = new VBox(INSIDE_VBOX_SPACING);
        LabelPattern titleFirstVBox = new LabelPattern("Temperature:", LABEL_STYLE);
        LabelPattern maxTemp = new LabelPattern("max temp: "+maxTempInt+"°C", LABEL_STYLE);
        LabelPattern minTemp = new LabelPattern("min temp: "+minTempInt+"°C", LABEL_STYLE);
        vBox1.getChildren().addAll(titleFirstVBox, maxTemp, minTemp);

        VBox vbox2 = new VBox(INSIDE_VBOX_SPACING);
        LabelPattern titleSecondVBox = new LabelPattern("Weather:", LABEL_STYLE);
        LabelPattern weatherVar = new LabelPattern(weatherString, LABEL_STYLE);
        ImageView weatherImages = weatherImage.selectPicture(weatherString);
        new FadeIn(weatherImages).play();
        vbox2.getChildren().addAll(titleSecondVBox, weatherVar, weatherImages);

        VBox vbox3 = new VBox(INSIDE_VBOX_SPACING);
        LabelPattern titleThirdVBox = new LabelPattern("Wind:", LABEL_STYLE);
        LabelPattern windSpeed = new LabelPattern("Wind speed: "+windSpeedDouble+" m/s", LABEL_STYLE);
        LabelPattern windDirection = new LabelPattern("Wind direction: " + windDirectionInt + "°", LABEL_STYLE);
        ImageView windImage = new ImageView("/WindDirectionIcons/wind_direction_" + windController.getDirection(windDirectionInt) + ".jpg");
        new FadeIn(windImage).play();
        vbox3.getChildren().addAll(titleThirdVBox, windSpeed, windDirection,windImage);

        VBox vbox4 = new VBox(INSIDE_VBOX_SPACING);
        LabelPattern otherInfo = new LabelPattern("Other information:", LABEL_STYLE);
        LabelPattern humidity = new LabelPattern("Humidity:", LABEL_STYLE);
        ProgressIndicator humidityBar = new ProgressIndicator();
        humidityBar.setProgress(humidityDouble / 100);
        humidityBar.setPrefSize(INDICATOR_WIDTH, INDICATOR_HEIGHT);
        vbox4.getChildren().addAll(otherInfo, humidity, humidityBar);

        centralHBox.getChildren().addAll(vBox1, vbox2, vbox3, vbox4);
        infoPane.setCenter(centralHBox);
        infoPane.getStylesheets().add(stylesheet);
        infoPane.setPrefSize(PANE_WIDTH, PANE_HEIGHT);
        new FadeIn(infoPane).play();

        return infoPane;
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