package Controller;

import Model.CityName;
import Model.WEBConnector;
import Model.JSONDataParser;
import View.FirstWindow;

public class StartButtonController {

    private final WEBConnector WEBConnector;
    private final FirstWindow firstWindow;
    private final CityName cityName;
    private final JSONDataParser parser;

    public StartButtonController(WEBConnector connector, JSONDataParser jsonParser
            , FirstWindow firstW, CityName name) {
        this.WEBConnector = connector;
        this.parser = jsonParser;
        this.firstWindow = firstW;
        this.cityName = name;
    }

    public void launchConnector() {
        cityName.setCityName(firstWindow.getInputText().getText());
        parser.getResponse(WEBConnector.getTodayWeatherData());
        parser.getResponseOnFiveDays(WEBConnector.getForecastOnFiveDays());
    }
}