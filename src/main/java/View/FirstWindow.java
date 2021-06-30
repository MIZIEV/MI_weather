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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    private final static String LIGHT_TITLE_IMAGE = "title_icon2.jpg";
    private final static String DARK_TITLE_IMAGE = "title_icon.jpg";

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

        String darkStylesheet = getClass().getResource("/VisualStyles/DarkTheme/FirstWindow.css").toExternalForm();
        String lightStylesheet = getClass().getResource("/VisualStyles/LightTheme/FirstWindow.css").toExternalForm();

        String secWinDarkStyle = getClass().getResource("/VisualStyles/DarkTheme/SecondWindow.css").toExternalForm();
        String secWinLightStyle = getClass().getResource("/VisualStyles/LightTheme/SecondWindow.css").toExternalForm();

        BorderPane mainPane = new BorderPane();          //create all panes
        BorderPane centralPane = new BorderPane();
        HBox topHBox = new HBox(30);
        BorderPane.setMargin(topHBox, margin);
        topHBox.getStyleClass().add("HBox");
        Scene firstWindowScene = new Scene(mainPane, WINDOW_WIDTH, WINDOW_HEIGHT);

        mainPane.getStyleClass().add("pane");
        mainPane.setTop(topHBox);
        mainPane.setCenter(centralPane);

        VBox centralVBOX = new VBox(30);                      // create central VBOX and add all elements
        ToggleGroup themeGroup = new ToggleGroup();
        RadioButton darkThemeButton = new RadioButton("Dark theme");
        RadioButton lightThemeButton = new RadioButton("Light theme");
        darkThemeButton.getStyleClass().add("Radio-Button");
        lightThemeButton.getStyleClass().add("Radio-Button");
        themeGroup.getToggles().addAll(darkThemeButton, lightThemeButton);
        darkThemeButton.fire();
        HBox radioButtonsBox = new HBox(40);
        radioButtonsBox.setAlignment(Pos.CENTER);
        radioButtonsBox.getChildren().addAll(darkThemeButton, lightThemeButton);
        Label textAreaSignature = new Label("Write the city name");
        ButtonsPattern startButton = new ButtonsPattern(150, 50, "Start", STYLE_CLASS_BUTTON);

        inputText.getStyleClass().add("text-input");
        inputText.setMaxSize(350, 50);
        inputText.positionCaret(5);

        ImageView titleImage = new ImageView();
        titleImage.setFitWidth(IMAGE_WIDTH);
        titleImage.setFitHeight(IMAGE_HEIGHT);
        topHBox.setAlignment(Pos.CENTER);
        BorderPane.setMargin(topHBox, margin);
        titleImage.setImage(new Image(DARK_TITLE_IMAGE));
        topHBox.getChildren().addAll(titleImage);
        centralPane.setCenter(centralVBOX);

        centralVBOX.setAlignment(Pos.CENTER);                           //add all elements in central part
        centralVBOX.getChildren().addAll(radioButtonsBox, textAreaSignature, inputText, startButton);

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
                    if (darkThemeButton.isSelected()) secondWindow.startWin(secWinDarkStyle);
                    else secondWindow.startWin(secWinLightStyle);
                }
            }
        });

        darkThemeButton.setOnAction(event -> {
            topHBox.getChildren().clear();
            firstWindowScene.getStylesheets().clear();
            titleImage.setImage(new Image(DARK_TITLE_IMAGE));
            firstWindowScene.getStylesheets().add(darkStylesheet);
            new FadeIn(mainPane).play();
            topHBox.getChildren().add(titleImage);
        });
        lightThemeButton.setOnAction(event -> {
            topHBox.getChildren().clear();
            firstWindowScene.getStylesheets().clear();
            titleImage.setImage(new Image(LIGHT_TITLE_IMAGE));
            firstWindowScene.getStylesheets().add(lightStylesheet);
            new FadeIn(mainPane).play();
            topHBox.getChildren().add(titleImage);
        });

        firstWindowScene.getStylesheets().add(darkStylesheet);
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