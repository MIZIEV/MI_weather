package View;

import Model.CityName;
import Model.ConnectorToWeatherSite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SecondWindow {

    Stage secondWindow = new Stage();
    CityName cityName;
    ConnectorToWeatherSite connectorToWeatherSite;
    FirstWindow firstWindow;

    public SecondWindow(CityName name, ConnectorToWeatherSite connector, FirstWindow firstW) {
        this.cityName = name;
        this.connectorToWeatherSite = connector;
        this.firstWindow = firstW;
    }

    public void startWin() {
        String stylesheet = getClass().getResource("/SecondWindow.css").toExternalForm();

        BorderPane generalPane = new BorderPane();
        BorderPane topPane = new BorderPane();
        BorderPane bottomPane = new BorderPane();
        HBox centerHBox = new HBox();
        centerHBox.setAlignment(Pos.CENTER);
        generalPane.getStyleClass().add("pane");

        String cityN = cityName.getCityName();

        Label cityName = new Label(cityN);
        topPane.setCenter(cityName);

        VBox leftVBox = new VBox();
        leftVBox.setAlignment(Pos.CENTER);
        Label temperature = new Label("Temperature");
        Label min = new Label("min");
        Label max = new Label("max");
        leftVBox.getChildren().addAll(temperature, min, max);

        VBox rightVBox = new VBox();
        rightVBox.setAlignment(Pos.CENTER);
        Label tempAnswer = new Label(connectorToWeatherSite.getTemperature());
        Label minAnswer = new Label(connectorToWeatherSite.getMinTemp());
        Label maxAnswer = new Label(connectorToWeatherSite.getMaxTemp());
        rightVBox.getChildren().addAll(tempAnswer, minAnswer, maxAnswer);

        ButtonsPattern backButton = new ButtonsPattern(150, 50, "Back");
        backButton.getStyleClass().add("Button");
        bottomPane.setLeft(backButton);

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                secondWindow.close();
                firstWindow.startWin();
            }
        });

        centerHBox.getChildren().addAll(leftVBox, rightVBox);
        generalPane.setTop(topPane);
        generalPane.setCenter(centerHBox);
        generalPane.setBottom(bottomPane);

        Scene secondWindowScene = new Scene(generalPane, 1024, 768);
        secondWindowScene.getStylesheets().add(stylesheet);

        secondWindow.setScene(secondWindowScene);
        secondWindow.setTitle("MI weather");
        secondWindow.show();
    }
}