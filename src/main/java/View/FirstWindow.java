package View;

import Model.CityName;
import Model.ConnectorToWeatherSite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FirstWindow {

    private final Stage firstWindow = new Stage();
    CityName cityName = new CityName();
    ConnectorToWeatherSite connector = new ConnectorToWeatherSite(cityName);

    public void startWin() {
        SecondWindow secondWindow = new SecondWindow(cityName, connector,this);

        String stylesheet = getClass().getResource("/FirstWindow.css").toExternalForm();

        BorderPane generalPane = new BorderPane();          //create all panes
        BorderPane topPane = new BorderPane();
        BorderPane centralPane = new BorderPane();
        Pane leftInsert = new Pane();
        Pane rightInsert = new Pane();
        leftInsert.setPrefWidth(200);
        rightInsert.setPrefWidth(200);

        generalPane.getStyleClass().add("pane");

        generalPane.setTop(topPane);
        generalPane.setCenter(centralPane);
        generalPane.setLeft(leftInsert);
        generalPane.setRight(rightInsert);

        VBox centralVBOX = new VBox(30);                      // create central VBOX and add all elements
        Label title = new Label("MI weather program");
        Label textAreaSignature = new Label("Write the name of the city ");
        TextArea inputText = new TextArea();
        ButtonsPattern startButton = new ButtonsPattern(150, 50, "Start");
        startButton.getStyleClass().add("Button");

        inputText.setPrefHeight(50);
        topPane.setCenter(title);
        centralPane.setCenter(centralVBOX);

        centralVBOX.setAlignment(Pos.CENTER);                           //add all elements in central part
        centralVBOX.getChildren().addAll(textAreaSignature, inputText, startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cityName.setCityName(inputText.getText());
                System.out.println(cityName.toString());
                firstWindow.close();
                connector.getConnection();
                secondWindow.startWin();
            }
        });

        Scene firstWindowScene = new Scene(generalPane, 1024, 768);
        firstWindowScene.getStylesheets().add(stylesheet);
        firstWindow.setMinWidth(880);
        firstWindow.setMinHeight(550);
        firstWindow.setScene(firstWindowScene);
        firstWindow.setTitle("Mi weather program");
        firstWindow.show();
    }
}