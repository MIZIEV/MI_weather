package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorsWindow {

    private final Stage errorWindow = new Stage();
    private Label errorMessage = new Label("error");
    private final static String WINDOW_TITLE = "Error message";
    private final static short WINDOW_WIDTH = 700;
    private final static short WINDOW_HEIGHT = 400;
    private final static short MIN_WINDOW_WIDTH = 630;
    private final static short MIN_WINDOW_HEIGHT = 250;

    public void startWin() {

        String stylesheet = getClass().getResource("/ErrorWindow.css").toExternalForm();

        BorderPane generalPane = new BorderPane();
        BorderPane bottomPane = new BorderPane();
        generalPane.setBottom(bottomPane);

        ButtonsPattern okButton = new ButtonsPattern(180, 70, "OK");
        Label insert = new Label();

        generalPane.setCenter(errorMessage);
        bottomPane.setCenter(okButton);
        bottomPane.setBottom(insert);
        generalPane.getStyleClass().add("pane");
        okButton.getStyleClass().add("Button");

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                errorWindow.close();
            }
        });

        Scene errorWinScene = new Scene(generalPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        errorWinScene.getStylesheets().add(stylesheet);
        errorWindow.setMinWidth(MIN_WINDOW_WIDTH);
        errorWindow.setMinHeight(MIN_WINDOW_HEIGHT);
        errorWindow.getIcons().add(new Image("error_icon.png"));
        errorWindow.setTitle(WINDOW_TITLE);
        errorWindow.setScene(errorWinScene);
        errorWindow.initModality(Modality.APPLICATION_MODAL);
        errorWindow.show();
    }

    public void setErrorMessage(Label errorMessage) {
        this.errorMessage = errorMessage;
    }
}
