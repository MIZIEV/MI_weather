package View;

import animatefx.animation.FadeIn;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * class description:
 * this class opens google map with the user selected city
 */
public class GoogleMap {

    private final Stage map = new Stage();
    private final static short WIN_WIDTH = 1024;
    private final static short WIN_HEIGHT = 768;
    private final static String WIN_TITLE = "Google map";
    private final static String WIN_ICON_URL = "/Icons/google_map_icon.png";
    private final static String GOOGLE_URL = "https://www.google.com.ua/maps/place/";

    public void launchMap(String cityName) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load(GOOGLE_URL+cityName);

        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(webView);
        Scene mapScene = new Scene(mainPane, WIN_WIDTH, WIN_HEIGHT);
        map.setTitle(WIN_TITLE);
        map.getIcons().add(new Image(WIN_ICON_URL));
        map.setScene(mapScene);
        new FadeIn(mainPane).play();
        map.show();
    }
}