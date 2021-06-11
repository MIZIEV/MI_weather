package View;

import Model.JSONDataParser;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class WeatherImage {
    private final JSONDataParser parser;
    private ImageView weatherImage;

    private final static String DAY_CLEAR_SKY = "/WeatherIcons/_clear_sky.jpg";
    private final static String NIGHT_CLEAR_SKY = "/WeatherIcons/_clear_sky.jpg";
    private final static String DAY_LIGHT_RAIN = "/WeatherIcons/_light_rain.jpg";
    private final static String NIGHT_LIGHT_RAIN = "/WeatherIcons/_light_rain.jpg";
    private final static String DAY_OVERCAST_CLOUDS = "/WeatherIcons/_overcast_clouds.jpg";
    private final static String NIGHT_OVERCAST_CLOUDS = "/WeatherIcons/_overcast_clouds.jpg";

    public WeatherImage(JSONDataParser jsonDataParser) {
        this.parser = jsonDataParser;
    }

    public void selectPicture(BorderPane pane, String nowTime) {
        if (parser.getWeatherNow().equals("scattered clouds") ^ parser.getWeatherNow().equals("few clouds") ^
                parser.getWeatherNow().equals("broken clouds") ^ parser.getWeatherNow().equals("overcast clouds")) {
            weatherImage = new ImageView("/WeatherIcons/" + nowTime + "_overcast_clouds.jpg");
            pane.setCenter(weatherImage);
        }
        if (parser.getWeatherNow().equals("light rain") ^ parser.getWeatherNow().equals("moderate rain")) {
            weatherImage = new ImageView("/WeatherIcons/" + nowTime + "_light_rain.jpg");
            pane.setCenter(weatherImage);
        }
        if (parser.getWeatherNow().equals("clear sky")) {
            weatherImage = new ImageView("/WeatherIcons/" + nowTime + "_clear_sky.jpg");
            pane.setCenter(weatherImage);
        }
    }
}