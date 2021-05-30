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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        String stylesheet = getClass().getResource("/SecondWindow.css").toExternalForm();

        BorderPane generalPane = new BorderPane();
        BorderPane bottomPane = new BorderPane();

        VBox topVBox = new VBox();
        VBox bottomVBox = new VBox();
        HBox centerHBox = new HBox();
        HBox topCentralMiddleHBox = new HBox(10);

        centerHBox.setAlignment(Pos.CENTER);
        generalPane.getStyleClass().add("pane");

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

        bottomVBox.setAlignment(Pos.CENTER);
        bottomPane.setPrefSize(400, 150);
        bottomVBox.getChildren().addAll(backButton);
        bottomPane.setLeft(bottomVBox);

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                secondWindow.close();
                firstWindow.startWin();
            }
        });

        generalPane.setTop(topVBox);
        generalPane.setCenter(centerHBox);
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