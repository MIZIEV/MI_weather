package View;

import animatefx.animation.FadeIn;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class DBInfo {

    public DBInfo(){

    }

    public BorderPane showInfo(){
        String stylesheets = getClass().getResource("/VisualStyles/DBInfoStyle.css").toExternalForm();
        BorderPane infoPane = new BorderPane();
        Label infoFromDB = new Label("Info from DB");
        infoPane.setCenter(infoFromDB);

        infoPane.getStyleClass().add("Border-Pane");
        new FadeIn(infoPane).play();
        infoPane.setPrefSize(1200,400);
        infoPane.getStylesheets().add(stylesheets);
        return infoPane;
    }
}
