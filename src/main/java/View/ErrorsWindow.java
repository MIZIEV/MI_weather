package View;

import View.Patterns.ButtonsPattern;
import animatefx.animation.FadeIn;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorsWindow {

    private final static String WINDOW_TITLE = "Error message";
    private final static String STYLE_CLASS_BUTTON = "Button";
    private final static String WIN_ICON_URL = "Icons/error_icon.png";

    private final static short WINDOW_WIDTH = 700;
    private final static short WINDOW_HEIGHT = 400;
    private final static short MIN_WINDOW_WIDTH = 630;
    private final static short MIN_WINDOW_HEIGHT = 250;

    private final static byte VBOX_SPACING = 10;
    private final static short BUTTON_WIDTH = 180;
    private final static short BUTTON_HEIGHT = 70;

    public void launchErrorWin(String errorText) {
        String stylesheet = getClass().getResource("/VisualStyles/ErrorWindow.css").toExternalForm();

        Stage errorWin = new Stage();
        BorderPane mainPane = new BorderPane();
        VBox mainBox = new VBox(VBOX_SPACING);
        mainBox.setAlignment(Pos.CENTER);
        ImageView errorImage = new ImageView("/Icons/error_icon.jpg");

        ButtonsPattern okButton = new ButtonsPattern(BUTTON_WIDTH, BUTTON_HEIGHT, "OK", STYLE_CLASS_BUTTON);
        Label errorMessage = new Label();
        errorMessage.setText(errorText);
        mainBox.getChildren().addAll(errorImage,errorMessage, okButton);
        mainPane.setCenter(mainBox);

        mainPane.getStyleClass().add("pane");
        okButton.setOnAction(event -> errorWin.close());

        Scene errorWinScene = new Scene(mainPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        errorWinScene.getStylesheets().add(stylesheet);
        errorWin.setMinWidth(MIN_WINDOW_WIDTH);
        errorWin.setMinHeight(MIN_WINDOW_HEIGHT);
        errorWin.getIcons().add(new Image(WIN_ICON_URL));
        errorWin.setTitle(WINDOW_TITLE);
        errorWin.setScene(errorWinScene);
        errorWin.initModality(Modality.APPLICATION_MODAL);
        new FadeIn(mainPane).play();
        errorWin.show();
    }
}