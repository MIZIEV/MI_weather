package View;

import Controller.StartButtonController;
import Model.CityName;
import Model.ConnectorToWeatherSite;
import Model.JSONDataParser;
import animatefx.animation.FadeIn;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private final static String STYLE_CLASS_BUTTON = "Button";
    private final ImageView image = new ImageView("white_picture.jpg");
    private final static short IMAGE_WIDTH = 600;
    private final static short IMAGE_HEIGHT = 200;
    private final static short WINDOW_WIDTH = 1024;
    private final static short WINDOW_HEIGHT = 768;
    private final static short WINDOW_MIN_WIDTH = 880;
    private final static short WINDOW_MIN_HEIGHT = 650;

    public void startWin() {
        Insets margin = new Insets(40);

        JSONDataParser parser = new JSONDataParser();
        SecondWindow secondWindow = new SecondWindow(cityName, parser, this);
        StartButtonController startButtonController = new StartButtonController(connector, parser, this, cityName);
        String stylesheet = getClass().getResource("/FirstWindow.css").toExternalForm();

        BorderPane generalPane = new BorderPane();          //create all panes
        BorderPane centralPane = new BorderPane();
        VBox topVBox = new VBox(30);
        Pane leftInsert = new Pane();
        Pane rightInsert = new Pane();
        Pane bottomInsert = new Pane();

        leftInsert.setPrefWidth(200);
        rightInsert.setPrefWidth(200);
        bottomInsert.setPrefHeight(150);
        generalPane.getStyleClass().add("pane");

        generalPane.setTop(topVBox);
        generalPane.setCenter(centralPane);
        generalPane.setLeft(leftInsert);
        generalPane.setRight(rightInsert);
        generalPane.setBottom(bottomInsert);

        VBox centralVBOX = new VBox(30);                      // create central VBOX and add all elements
        Label title = new Label("MI weather program");
        Label textAreaSignature = new Label("Write the city name");
        ButtonsPattern startButton = new ButtonsPattern(150, 50, "Start", STYLE_CLASS_BUTTON);

        inputText.setPrefHeight(50);
        image.setFitWidth(IMAGE_WIDTH);
        image.setFitHeight(IMAGE_HEIGHT);
        topVBox.setAlignment(Pos.CENTER);
        BorderPane.setMargin(topVBox, margin);
        topVBox.getChildren().addAll(title, image);
        centralPane.setCenter(centralVBOX);

        centralVBOX.setAlignment(Pos.CENTER);                           //add all elements in central part
        centralVBOX.getChildren().addAll(textAreaSignature, inputText, startButton);

        startButton.setOnAction(event -> {
            if (inputText.getText().trim().length() == 0) {
                ErrorsWindow errorsWindow = new ErrorsWindow();
                errorsWindow.setErrorMessage(new Label("You have not entered the city name "));
                errorsWindow.startWin();
            } else {
                startButtonController.launchConnector();

                if (parser.getTempNow() == null | parser.getTodayDate() == null) {
                    ErrorsWindow errorsWindow = new ErrorsWindow();
                    errorsWindow.setErrorMessage(new Label("City not found, try again!!!"));
                    errorsWindow.startWin();
                } else {
                    firstWindow.close();
                    secondWindow.startWin();
                }
            }
        });

        Scene firstWindowScene = new Scene(generalPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        firstWindowScene.getStylesheets().add(stylesheet);
        firstWindow.getIcons().add(new Image("weather_icon.png"));
        firstWindow.setMinWidth(WINDOW_MIN_WIDTH);
        firstWindow.setMinHeight(WINDOW_MIN_HEIGHT);
        firstWindow.setScene(firstWindowScene);
        firstWindow.setTitle(FIRST_WINDOW_TITLE);
        new FadeIn(generalPane).play();
        firstWindow.show();
    }

    public TextArea getInputText() {
        return inputText;
    }
}