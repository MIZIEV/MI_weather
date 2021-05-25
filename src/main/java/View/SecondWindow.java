package View;

import Model.CityName;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SecondWindow {

    Stage secondWindow = new Stage();
    CityName cityName;

    public SecondWindow(CityName name) {
        this.cityName = name;
    }

    public void startWin(){
        String stylesheet = getClass().getResource("/SecondWindow.css").toExternalForm();

        BorderPane generalPane = new BorderPane();
        BorderPane topPane = new BorderPane();
        BorderPane bottomPane = new BorderPane();
        HBox centerHBox = new HBox();
        generalPane.getStyleClass().add("pane");

        String cityN=cityName.getCityName();

        Label cityName = new Label(cityN);
        topPane.setCenter(cityName);

        VBox leftVBox = new VBox();
        Label temperature = new Label("Temperature");
        Label min = new Label("min");
        Label max = new Label("max");
        leftVBox.getChildren().addAll(temperature, min, max);

        VBox rightVBox = new VBox();
        Label tempAnswer = new Label("temp1");
        Label minAnswer = new Label("min");
        Label maxAnswer = new Label("max");
        rightVBox.getChildren().addAll(tempAnswer, minAnswer, maxAnswer);

        ButtonsPattern backButton = new ButtonsPattern(150, 50, "Back");
        backButton.getStyleClass().add("Button");
        bottomPane.setLeft(backButton);

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