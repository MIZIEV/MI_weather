package prog.view;

import prog.Controller.StartButtonController;
import prog.Model.CityName;
import prog.Model.WEBConnector;
import prog.Model.JSONDataParser;
import prog.view.patterns.controls.ButtonsPattern;
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

    private final TextField inputText = new TextField();

    private final static String WIN_ICON_URL = "Icons/weather_icon.png";
    private final static String FIRST_WINDOW_TITLE = "Mi weather program";
    private final static String SC_BUTTON = "Button";            // SC = style class
    private final static String SC_RADIO_BUTTON = "Radio-Button";
    private final static String SC_TEXT_AREA = "text-input";
    private final static String LIGHT_TITLE_IMAGE = "Icons/light_title_icon.jpg";
    private final static String DARK_TITLE_IMAGE = "Icons/dark_title_icon.jpg";

    private final static short IMAGE_WIDTH = 300;
    private final static short IMAGE_HEIGHT = 300;

    private final static short WINDOW_WIDTH = 450;
    private final static short WINDOW_HEIGHT = 768;
    private final static short WINDOW_MIN_WIDTH = 400;
    private final static short WINDOW_MIN_HEIGHT = 700;

    private final static short BUTTON_WIDTH = 150;
    private final static short BUTTON_HEIGHT = 50;

    private final static byte TOP_CENTRAL_HBOX_SPACING = 30;
    private final static byte THEME_SPACING = 40;
    private final static short TEXT_AREA_WIDTH = 350;
    private final static short TEXT_AREA_HEIGHT = 50;
    private final static byte CARET_POSITION_IN_TEXT_AREA = 5;

    public void startWin() {
        Insets margin = new Insets(40);
        Stage firstWindow = new Stage();
        CityName cityName = new CityName();
        WEBConnector connector = new WEBConnector();
        JSONDataParser parser = new JSONDataParser(cityName);
        SecondWindow secondWindow = new SecondWindow(cityName, parser, this);

        StartButtonController startButtonController = new StartButtonController(connector, parser, this, cityName);

        String darkStylesheet = getClass().getResource("/VisualStyles/DarkTheme/FirstWindow.css").toExternalForm();
        String lightStylesheet = getClass().getResource("/VisualStyles/LightTheme/FirstWindow.css").toExternalForm();

        BorderPane mainPane = new BorderPane();          //create all panes
        BorderPane centralPane = new BorderPane();
        HBox topHBox = new HBox(TOP_CENTRAL_HBOX_SPACING);
        BorderPane.setMargin(topHBox, margin);
        topHBox.getStyleClass().add("HBox");
        Scene firstWindowScene = new Scene(mainPane, WINDOW_WIDTH, WINDOW_HEIGHT);

        mainPane.getStyleClass().add("pane");
        mainPane.setTop(topHBox);
        mainPane.setCenter(centralPane);

        VBox centralVBOX = new VBox(TOP_CENTRAL_HBOX_SPACING);              // create central VBOX and add all elements
        ToggleGroup themeGroup = new ToggleGroup();
        RadioButton darkThemeButton = new RadioButton("Dark theme");
        RadioButton lightThemeButton = new RadioButton("Light theme");
        darkThemeButton.getStyleClass().add(SC_RADIO_BUTTON);
        lightThemeButton.getStyleClass().add(SC_RADIO_BUTTON);
        themeGroup.getToggles().addAll(darkThemeButton, lightThemeButton);
        darkThemeButton.fire();
        HBox radioButtonsBox = new HBox(THEME_SPACING);
        radioButtonsBox.setAlignment(Pos.CENTER);
        radioButtonsBox.getChildren().addAll(darkThemeButton, lightThemeButton);
        Label textAreaSignature = new Label("Write the city name");
        ButtonsPattern startButton = new ButtonsPattern(BUTTON_WIDTH, BUTTON_HEIGHT, "Start", SC_BUTTON);

        inputText.getStyleClass().add(SC_TEXT_AREA);
        inputText.setMaxSize(TEXT_AREA_WIDTH, TEXT_AREA_HEIGHT);
        inputText.positionCaret(CARET_POSITION_IN_TEXT_AREA);

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
                errorsWindow.launchErrorWin("You have not entered the city name ");
            } else {
                startButtonController.launchConnector();

                if (parser.getTempNow() == null | parser.getTodayDate() == null) {
                    ErrorsWindow errorsWindow = new ErrorsWindow();
                    errorsWindow.launchErrorWin("City not found, try again!!!");
                } else {
                    firstWindow.close();
                    if (darkThemeButton.isSelected()) secondWindow.startWin("/VisualStyles/DarkTheme/");
                    else secondWindow.startWin("/VisualStyles/LightTheme/");
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
        firstWindow.getIcons().add(new Image(WIN_ICON_URL));
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