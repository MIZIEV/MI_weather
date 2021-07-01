package View;

import Model.JSONDataParser;
import javafx.scene.image.ImageView;

public class WeatherImage {

    private final JSONDataParser parser;
    private ImageView weatherImage;

    private final static String DAY_CLEAR_SKY = "/VisualStyles/DarkTheme/DarkWeatherIcons/day_clear_sky.jpg";
    private final static String DAY_LIGHT_RAIN = "/VisualStyles/DarkTheme/DarkWeatherIcons/day_light_rain.jpg";
    private final static String DAY_OVERCAST_CLOUDS = "/VisualStyles/DarkTheme/DarkWeatherIcons/day_overcast_clouds.jpg";

    public WeatherImage(JSONDataParser jsonDataParser) {
        this.parser = jsonDataParser;
    }

    public ImageView selectTodayPicture(String nowTime, String stylesheet) {
        String theme;
        if (stylesheet.equals("/VisualStyles/DarkTheme/")) theme = "DarkTheme/DarkWeatherIcons/";
        else theme = "LightTheme/LightWeatherIcons/";

        if (parser.getWeatherNow().equals("scattered clouds") ^ parser.getWeatherNow().equals("few clouds") ^
                parser.getWeatherNow().equals("broken clouds") ^ parser.getWeatherNow().equals("overcast clouds")) {
            weatherImage = new ImageView("/VisualStyles/" + theme + nowTime + "_overcast_clouds.jpg");
        }
        if (parser.getWeatherNow().equals("light rain") ^ parser.getWeatherNow().equals("moderate rain")) {
            weatherImage = new ImageView("/VisualStyles/" + theme + nowTime + "_light_rain.jpg");
        }
        if (parser.getWeatherNow().equals("clear sky")) {
            weatherImage = new ImageView("/VisualStyles/" + theme + nowTime + "_clear_sky.jpg");
        }
        weatherImage.setFitWidth(145);
        weatherImage.setFitHeight(145);
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
        weatherImage.setFitWidth(100);
        weatherImage.setFitHeight(100);
        return weatherImage;
    }
}