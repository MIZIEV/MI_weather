package View;

import Controller.StartButtonController;
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
    private final TextArea inputText = new TextArea();

    private final CityName cityName = new CityName();
    private final ConnectorToWeatherSite connector = new ConnectorToWeatherSite(cityName);

    private final static String FIRST_WINDOW_TITLE = "Mi weather program";
    private final static short WINDOW_WIDTH = 1024;
    private final static short WINDOW_HEIGHT = 768;
    private final static short WINDOW_MIN_WIDTH = 880;
    private final static short WINDOW_MIN_HEIGHT = 550;

    public void startWin() {
        SecondWindow secondWindow = new SecondWindow(cityName, connector, this);
        StartButtonController startButtonController = new StartButtonController(connector, this, cityName);
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
                startButtonController.launchConnector();
                firstWindow.close();
                secondWindow.startWin();
            }
        });

        Scene firstWindowScene = new Scene(generalPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        firstWindowScene.getStylesheets().add(stylesheet);
        firstWindow.setMinWidth(WINDOW_MIN_WIDTH);
        firstWindow.setMinHeight(WINDOW_MIN_HEIGHT);
        firstWindow.setScene(firstWindowScene);
        firstWindow.setTitle(FIRST_WINDOW_TITLE);
        firstWindow.show();
    }

    public TextArea getInputText() { return inputText; }
}