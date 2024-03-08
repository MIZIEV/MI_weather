package prog.view;

import prog.web.JSONParser;
import javafx.scene.image.ImageView;

/**
 * class description:
 * This class is needed to choosing tha weather image.
 *
 * getTodayImage(String nowTime, String styleTheme) method takes strings with present time & theme,
 * then depending on the weather, it return images.
 *
 * selectImage(String weather, String styleTheme) method takes strings with weather & theme,
 * then return weather images without time dependency
 */
public class WeatherImage {
/*
    private final JSONParser parser;
    private ImageView weatherImage;
    private final static short TODAY_IMAGE_SIZE = 145;
    private final static byte IMAGE_SIZE = 100;

    public WeatherImage(JSONParser jsonParser) {
        this.parser = jsonParser;
    }

    public ImageView getTodayImage(String nowTime, String styleTheme) {

        if (parser.getWeatherNow().equals("scattered clouds") ^ parser.getWeatherNow().equals("few clouds") ^
                parser.getWeatherNow().equals("broken clouds") ^ parser.getWeatherNow().equals("overcast clouds")) {
            weatherImage = new ImageView(styleTheme +"WeatherIcons/"+ nowTime + "_overcast_clouds.jpg");
        }
        if (parser.getWeatherNow().equals("light rain") ^ parser.getWeatherNow().equals("moderate rain")
                ^ parser.getWeatherNow().equals("heavy intensity rain")) {
            weatherImage = new ImageView(styleTheme +"WeatherIcons/"+ nowTime + "_light_rain.jpg");
        }
        if (parser.getWeatherNow().equals("clear sky")) {
            weatherImage = new ImageView(styleTheme+"WeatherIcons/" + nowTime + "_clear_sky.jpg");
        }
        weatherImage.setFitWidth(TODAY_IMAGE_SIZE);
        weatherImage.setFitHeight(TODAY_IMAGE_SIZE);
        return weatherImage;
    }

    public ImageView selectImage(String weather, String styleTheme) {

        if (weather.equals("scattered clouds") ^ weather.equals("few clouds") ^ weather.equals("broken clouds")
                ^ weather.equals("overcast clouds")) {
            weatherImage = new ImageView(styleTheme + "WeatherIcons/day_overcast_clouds.jpg");
        }
        if (weather.equals("light rain") ^ weather.equals("moderate rain") ^ weather.equals("heavy intensity rain")) {
            weatherImage = new ImageView(styleTheme + "WeatherIcons/day_light_rain.jpg");
        }
        if (weather.equals("clear sky")) {
            weatherImage = new ImageView( styleTheme + "WeatherIcons/day_clear_sky.jpg");
        }
        weatherImage.setFitWidth(IMAGE_SIZE);
        weatherImage.setFitHeight(IMAGE_SIZE);
        return weatherImage;
    }*/
}