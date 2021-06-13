package View;

import Controller.WeatherTimeImageController;
import Controller.WindDirectionControl;
import Model.JSONDataParser;
import Model.PresentTime;
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

    public GeneralInfo(JSONDataParser jsonParser) {
        this.parser = jsonParser;
    }

    public BorderPane showInfo() {

        String stylesheet = getClass().getResource("/GeneralInfoStyle.css").toExternalForm();
        Insets margin = new Insets(50);

        WindDirectionControl windController = new WindDirectionControl();

        PresentTime presentTime = new PresentTime();
        WeatherTimeImageController weatherImageController = new WeatherTimeImageController(presentTime);
        WeatherImage weatherImage = new WeatherImage(parser);

        BorderPane infoPane = new BorderPane();
        infoPane.getStyleClass().add("Border-Pane");
        HBox centralHBox = new HBox(120);
        centralHBox.setAlignment(Pos.CENTER);
        BorderPane.setMargin(centralHBox, margin);

        VBox vBox1 = new VBox(10);
        LabelPattern titleFirstVBox = new LabelPattern("Temperature:", "Label");
        LabelPattern maxTemp = new LabelPattern("max temp: "+maxTempInt+"°C", "Label");
        LabelPattern minTemp = new LabelPattern("min temp: "+minTempInt+"°C", "Label");
        vBox1.getChildren().addAll(titleFirstVBox, maxTemp, minTemp);

        VBox vbox2 = new VBox(10);
        LabelPattern titleSecondVBox = new LabelPattern("Weather:", "Label");
        LabelPattern weatherVar = new LabelPattern(weatherString, "Label");
        ImageView weatherImages = weatherImage.selectPicture(weatherImageController.startController());
        vbox2.getChildren().addAll(titleSecondVBox, weatherVar, weatherImages);

        VBox vbox3 = new VBox(10);
        LabelPattern titleThirdVBox = new LabelPattern("Wind:", "Label");
        LabelPattern windSpeed = new LabelPattern("Wind speed: "+windSpeedDouble+" m/s", "Label");
        LabelPattern windDirection = new LabelPattern("Wind direction: " + windDirectionInt + "°", "Label");
        ImageView windImage = new ImageView("/WindDirectionIcons/wind_direction_" + windController.getDirection(windDirectionInt) + ".jpg");
        vbox3.getChildren().addAll(titleThirdVBox, windSpeed, windDirection,windImage);

        VBox vbox4 = new VBox(10);
        LabelPattern otherInfo = new LabelPattern("Other information:", "Label");
        LabelPattern humidity = new LabelPattern("Humidity:", "Label");
        ProgressIndicator humidityBar = new ProgressIndicator();
        humidityBar.setProgress(humidityDouble / 100);
        humidityBar.setPrefSize(80, 80);
        vbox4.getChildren().addAll(otherInfo, humidity, humidityBar);

        centralHBox.getChildren().addAll(vBox1, vbox2, vbox3, vbox4);
        infoPane.setCenter(centralHBox);
        infoPane.getStylesheets().add(stylesheet);
        infoPane.setPrefSize(1200, 400);
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