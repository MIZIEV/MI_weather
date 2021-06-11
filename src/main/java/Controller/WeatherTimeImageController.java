package Controller;

import Model.PresentTime;
import View.WeatherImage;

public class WeatherTimeImageController {
    private final WeatherImage weatherImage;
    private final PresentTime presentTime;

    public WeatherTimeImageController(WeatherImage weatherI, PresentTime time) {
        this.weatherImage = weatherI;
        this.presentTime = time;
    }

    public String startController() {
        if (presentTime.getPresentTime() > 4 & presentTime.getPresentTime() < 18) return "day";
        else return "night";
    }
}