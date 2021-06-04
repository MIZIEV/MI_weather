package View;

import Model.CityName;
import Model.ConnectorToWeatherSite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
    private final ConnectorToWeatherSite connectorToWeatherSite;
    private final FirstWindow firstWindow;

    private final static String WINDOW_TITLE = "MI weather";
    private final static short WINDOW_WIDTH = 1024;
    private final static short WINDOW_HEIGHT = 768;
    private final static short WINDOW_MIN_WIDTH = 880;
    private final static short WINDOW_MIN_HEIGHT = 550;

    public SecondWindow(CityName name, ConnectorToWeatherSite connector, FirstWindow firstW) {
        this.cityName = name;
        this.connectorToWeatherSite = connector;
        this.firstWindow = firstW;
    }

    public void startWin() {
        DecimalFormat format = new DecimalFormat("#");

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

        VBox vbox1 = new VBox(10);
        HBox hBox1 = new HBox(10);
        Label data1 = new Label(connectorToWeatherSite.getToday());
        Label minTemp1 = new Label(format.format(connectorToWeatherSite.getMinTempToday() - 273.15) + " C");
        Label maxTemp1 = new Label(format.format(connectorToWeatherSite.getMaxTempToday() - 273.15) + " C");
        Label clouds1 = new Label(connectorToWeatherSite.getCloudsToday());
        hBox1.getChildren().addAll(minTemp1, maxTemp1);
        vbox1.getChildren().addAll(data1, hBox1, clouds1);

        VBox vbox2 = new VBox(10);
        HBox hBox2 = new HBox(10);
        Label data2 = new Label(connectorToWeatherSite.getTomorrowDay());
        Label minTemp2 = new Label(format.format(connectorToWeatherSite.getMinTempTomorrowDay() - 273.15) + " C");
        Label maxTemp2 = new Label(format.format(connectorToWeatherSite.getMaxTempTomorrowDay() - 273.15) + " C");
        Label clouds2 = new Label(connectorToWeatherSite.getCloudsTomorrow());
        hBox2.getChildren().addAll(minTemp2, maxTemp2);
        vbox2.getChildren().addAll(data2, hBox2, clouds2);

        VBox vbox3 = new VBox(10);
        HBox hBox3 = new HBox(10);
        Label data3 = new Label(connectorToWeatherSite.getDayAfterTomorrow());
        Label minTemp3 = new Label(format.format(connectorToWeatherSite.getMinTempAfterTomorrow() - 273.15) + " C");
        Label maxTemp3 = new Label(format.format(connectorToWeatherSite.getMaxTempAfterTomorrow() - 273.15) + " C");
        Label clouds3 = new Label(connectorToWeatherSite.getCloudsAfterTomorrow());
        hBox3.getChildren().addAll(minTemp3, maxTemp3);
        vbox3.getChildren().addAll(data3, hBox3, clouds3);

        VBox vbox4 = new VBox(10);
        HBox hBox4 = new HBox(10);
        Label data4 = new Label(connectorToWeatherSite.getFourthDay());
        Label minTemp4 = new Label(format.format(connectorToWeatherSite.getMinTempFourthDay() - 273.15) + " C");
        Label maxTemp4 = new Label(format.format(connectorToWeatherSite.getMaxTempFourthDay() - 273.15) + " C");
        Label clouds4 = new Label(connectorToWeatherSite.getCloudsFourthDay());
        hBox4.getChildren().addAll(minTemp4, maxTemp4);
        vbox4.getChildren().addAll(data4, hBox4, clouds4);

        VBox vbox5 = new VBox(10);
        HBox hBox5 = new HBox(10);
        Label data5 = new Label(connectorToWeatherSite.getFifthDay());
        Label minTemp5 = new Label(format.format(connectorToWeatherSite.getMinTempFifthDay() - 273.15) + " C");
        Label maxTemp5 = new Label(format.format(connectorToWeatherSite.getMaxTempFifthDay() - 273.15) + " C");
        Label clouds5 = new Label(connectorToWeatherSite.getCloudsFifthDay());
        hBox5.getChildren().addAll(minTemp5, maxTemp5);
        vbox5.getChildren().addAll(data5, hBox5, clouds5);

        vbox1.getStyleClass().add("VBox");
        vbox2.getStyleClass().add("VBox");
        vbox3.getStyleClass().add("VBox");
        vbox4.getStyleClass().add("VBox");
        vbox5.getStyleClass().add("VBox");

        centerHBox.setAlignment(Pos.CENTER);
        generalPane.getStyleClass().add("pane");
        topVBox.getStyleClass().add("VBox");
        String cityN = cityName.getCityName();

        Label cityName = new Label(cityN);
        topVBox.setAlignment(Pos.TOP_CENTER);

        Label temperature = new Label("Temperature");
        Label min = new Label("min:");
        Label max = new Label("max:");
        Label tempAnswer = new Label(connectorToWeatherSite.getTemperature());
        Label minAnswer = new Label(connectorToWeatherSite.getMinTemp());
        Label maxAnswer = new Label(connectorToWeatherSite.getMaxTemp());
        Label clouds = new Label(connectorToWeatherSite.getClouds());

        topCentralMiddleHBox.setAlignment(Pos.TOP_CENTER);
        topCentralMiddleHBox.getChildren().addAll(min, minAnswer, max, maxAnswer);
        topVBox.getChildren().addAll(cityName, temperature, tempAnswer, topCentralMiddleHBox, clouds);

        ButtonsPattern backButton = new ButtonsPattern(150, 50, "Back");
        backButton.getStyleClass().add("Button");

        bottomPane.setPrefSize(400, 150);

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                secondWindow.close();
                firstWindow.startWin();
            }
        });

        topPane.setLeft(topLeftInsert);
        topPane.setRight(topRightInsert);
        topPane.setCenter(topVBox);
        generalPane.setTop(topPane);

        centerHBox.getChildren().addAll(vbox1, vbox2, vbox3, vbox4, vbox5);
        centerVbox.getChildren().addAll(centerHBox);

        bottomPane.setCenter(backButton);
        generalPane.setCenter(centerVbox);
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
}