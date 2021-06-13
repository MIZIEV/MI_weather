package View;

import Model.JSONDataParser;
import javafx.scene.image.ImageView;

public class WeatherImage {

    private final JSONDataParser parser;
    private ImageView weatherImage;

    private final static String DAY_CLEAR_SKY = "/WeatherIcons/day_clear_sky.jpg";
    private final static String DAY_LIGHT_RAIN = "/WeatherIcons/day_light_rain.jpg";
    private final static String DAY_OVERCAST_CLOUDS = "/WeatherIcons/day_overcast_clouds.jpg";

    public WeatherImage(JSONDataParser jsonDataParser) {
        this.parser = jsonDataParser;
    }

    public ImageView selectTodayPicture(String nowTime) {
        if (parser.getWeatherNow().equals("scattered clouds") ^ parser.getWeatherNow().equals("few clouds") ^
                parser.getWeatherNow().equals("broken clouds") ^ parser.getWeatherNow().equals("overcast clouds")) {
            weatherImage = new ImageView("/WeatherIcons/" + nowTime + "_overcast_clouds.jpg");
        }
        if (parser.getWeatherNow().equals("light rain") ^ parser.getWeatherNow().equals("moderate rain")) {
            weatherImage = new ImageView("/WeatherIcons/" + nowTime + "_light_rain.jpg");
        }
        if (parser.getWeatherNow().equals("clear sky")) {
            weatherImage = new ImageView("/WeatherIcons/" + nowTime + "_clear_sky.jpg");
        }
        return weatherImage;
    }

    public ImageView selectPicture(String weather) {
        if (weather.equals("scattered clouds") ^ weather.equals("few clouds") ^ weather.equals("broken clouds")
                ^ weather.equals("overcast clouds")) {
            weatherImage = new ImageView(DAY_OVERCAST_CLOUDS);
        }
        if (weather.equals("light rain") ^ weather.equals("moderate rain")) {
            weatherImage = new ImageView(DAY_LIGHT_RAIN);
        }
        if (weather.equals("clear sky")) {
            weatherImage = new ImageView(DAY_CLEAR_SKY);
        }
        return weatherImage;
    }
}