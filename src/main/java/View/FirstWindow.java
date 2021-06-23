package View;

import Controller.StartButtonController;
import Model.CityName;
import Model.ConnectorToWeatherSite;
import Model.JSONDataParser;
import View.Patterns.ButtonsPattern;
import animatefx.animation.FadeIn;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FirstWindow {

    private final Stage firstWindow = new Stage();
    private final TextField inputText = new TextField();

    private final CityName cityName = new CityName();
    private final ConnectorToWeatherSite connector = new ConnectorToWeatherSite(cityName);

    private final static String FIRST_WINDOW_TITLE = "Mi weather program";
    private final static String STYLE_CLASS_BUTTON = "Button";
    private final ImageView image = new ImageView("title_icon.jpg");

    private final static short IMAGE_WIDTH = 300;
    private final static short IMAGE_HEIGHT = 300;

    private final static short WINDOW_WIDTH = 450;
    private final static short WINDOW_HEIGHT = 768;
    private final static short WINDOW_MIN_WIDTH = 400;
    private final static short WINDOW_MIN_HEIGHT = 700;

    public void startWin() {
        Insets margin = new Insets(40);

        JSONDataParser parser = new JSONDataParser(cityName);
        SecondWindow secondWindow = new SecondWindow(cityName, parser, this);
        StartButtonController startButtonController = new StartButtonController(connector, parser, this, cityName);
        String stylesheet = getClass().getResource("/VisualStyles/FirstWindow.css").toExternalForm();

        BorderPane mainPane = new BorderPane();          //create all panes
        BorderPane centralPane = new BorderPane();
        HBox topHBox = new HBox(30);
        BorderPane.setMargin(topHBox,margin);
        topHBox.getStyleClass().add("HBox");

        mainPane.getStyleClass().add("pane");

        mainPane.setTop(topHBox);
        mainPane.setCenter(centralPane);

        VBox centralVBOX = new VBox(30);                      // create central VBOX and add all elements
        Label textAreaSignature = new Label("Write the city name");
        ButtonsPattern startButton = new ButtonsPattern(150, 50, "Start", STYLE_CLASS_BUTTON);

        inputText.getStyleClass().add("text-input");
        inputText.setMaxSize(350,50);

        inputText.positionCaret(5);
        image.setFitWidth(IMAGE_WIDTH);
        image.setFitHeight(IMAGE_HEIGHT);
        topHBox.setAlignment(Pos.CENTER);
        BorderPane.setMargin(topHBox, margin);
        topHBox.getChildren().addAll( image);
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

        Scene firstWindowScene = new Scene(mainPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        firstWindowScene.getStylesheets().add(stylesheet);
        firstWindow.getIcons().add(new Image("weather_icon.png"));
        firstWindow.setMinWidth(WINDOW_MIN_WIDTH);
        firstWindow.setMinHeight(WINDOW_MIN_HEIGHT);
        firstWindow.setScene(firstWindowScene);
        firstWindow.setTitle(FIRST_WINDOW_TITLE);
        new FadeIn(mainPane).play();
        firstWindow.show();
    }

    public TextField getInputText() {
        return inputText;
    }
}