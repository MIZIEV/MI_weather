package View;

import Model.JSONDataParser;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GeneralInfo {

    private final JSONDataParser parser;
    private int minTempInt;
    private int maxTempInt;
    private int wingsSpeedInt;
    private int humidityInt;
    private String weatherString = "clouds";

    public GeneralInfo(JSONDataParser jsonParser) {
        this.parser = jsonParser;
    }

    public BorderPane showInfo() {
        String stylesheet = getClass().getResource("/GeneralInfoStyle.css").toExternalForm();
        BorderPane infoPane = new BorderPane();
        infoPane.getStyleClass().add("Border-Pane");
        HBox centralHBox = new HBox(80);
        centralHBox.setAlignment(Pos.CENTER);

        VBox vBox1 = new VBox(10);
        HBox maxTempHBox = new HBox(10);
        LabelPattern maxTemp = new LabelPattern("max temp: ", "Label");
        LabelPattern maxVar = new LabelPattern(maxTempInt + " C", "Label");
        maxTempHBox.getChildren().addAll(maxTemp, maxVar);
        HBox minTempHBox = new HBox(10);
        LabelPattern minTemp = new LabelPattern("min temp: ", "Label");
        LabelPattern minVar = new LabelPattern(minTempInt + " C", "Label");
        minTempHBox.getChildren().addAll(minTemp, minVar);
        HBox humidityHBox = new HBox(10);
        LabelPattern humidity = new LabelPattern("Humidity", "Label");
        LabelPattern humidityVar = new LabelPattern(humidityInt + "", "Label");
        humidityHBox.getChildren().addAll(humidity, humidityVar);
        ProgressBar humidityBar = new ProgressBar();
        humidityBar.setProgress(0.6);
        vBox1.getChildren().addAll(maxTempHBox, minTempHBox, humidityHBox, humidityBar);

        VBox vbox2 = new VBox(10);
        HBox weatherHBox = new HBox(10);
        LabelPattern weather = new LabelPattern("Weather: ", "Label");
        LabelPattern weatherVar = new LabelPattern(weatherString, "Label");
        ImageView weatherImage = new ImageView("/WeatherIcons/day_clear_sky.jpg");
        weatherHBox.getChildren().addAll(weather, weatherVar);
        vbox2.getChildren().addAll(weatherHBox, weatherImage);

        VBox vbox3 = new VBox(10);
        LabelPattern wind = new LabelPattern("Wind", "Label");
        HBox windHBox = new HBox(10);
        LabelPattern windSpeed = new LabelPattern("Wind speed: ", "Label");
        LabelPattern windSpeedVar = new LabelPattern(wingsSpeedInt + "m/s", "Label");
        windHBox.getChildren().addAll(windSpeed, windSpeedVar);
        vbox3.getChildren().addAll(wind, windHBox);

        centralHBox.getChildren().addAll(vBox1, vbox2, vbox3);
        infoPane.setCenter(centralHBox);
        infoPane.getStylesheets().add(stylesheet);

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

    public void setHumidityInt(int h) {
        this.humidityInt = h;
    }

    public void setWingsSpeedInt(int speed) {
        this.wingsSpeedInt = speed;
    }
}