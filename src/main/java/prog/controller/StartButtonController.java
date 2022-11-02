package prog.controller;

import prog.models.CityName;
import prog.web.WEBConnector;
import prog.web.JSONParser;
import prog.view.FirstWindow;

public class StartButtonController {

    private final WEBConnector WEBConnector;
    private final FirstWindow firstWindow;
    private final CityName cityName;
    private final JSONParser parser;

    public StartButtonController(WEBConnector connector, JSONParser jsonParser,
                                 FirstWindow firstW, CityName name) {
        this.WEBConnector = connector;
        this.firstWindow = firstW;
        this.cityName = name;
        this.parser = jsonParser;
    }

    public void launchConnector() {
        cityName.setCityName(firstWindow.getInputText().getText());
        String oneDayAPICall = "http://api.openweathermap.org/data/2.5/weather?q=" +
                cityName.getCityName() + "&units=metric&appid=ceb8e786e2a20dff0a80033639084138";
        String fiveDayForecast = "http://api.openweathermap.org/data/2.5/forecast?q=" +
                cityName.getCityName() + "&appid=ceb8e786e2a20dff0a80033639084138";
        parser.getResponse(WEBConnector.getAPIResponse(oneDayAPICall));
        parser.getResponseOnFiveDays(WEBConnector.getAPIResponse(fiveDayForecast));
    }
}