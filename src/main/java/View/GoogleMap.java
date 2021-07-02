package View;

import animatefx.animation.FadeIn;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class GoogleMap {

    private final Stage map = new Stage();
    private final static short WIN_WIDTH = 1024;
    private final static short WIN_HEIGHT = 768;
    private final static String GOOGLE_URL = "https://www.google.com.ua/maps/place/";

    public void launchMap(String cityName) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load(GOOGLE_URL + cityName);

        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(webView);
        Scene mapScene = new Scene(mainPane, WIN_WIDTH, WIN_HEIGHT);
        map.setScene(mapScene);
        new FadeIn(mainPane).play();
        map.show();
    }
}