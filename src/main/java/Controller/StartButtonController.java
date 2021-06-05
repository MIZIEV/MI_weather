package Controller;

import Model.CityName;
import Model.ConnectorToWeatherSite;
import Model.JSONDataParser;
import View.FirstWindow;

public class StartButtonController {

    private final ConnectorToWeatherSite connectorToWeatherSite;
    private final FirstWindow firstWindow;
    private final CityName cityName;
    private final JSONDataParser parser;

    public StartButtonController(ConnectorToWeatherSite connector, JSONDataParser jsonParser
            , FirstWindow firstW, CityName name) {
        this.connectorToWeatherSite = connector;
        this.parser = jsonParser;
        this.firstWindow = firstW;
        this.cityName = name;
    }

    public void launchConnector() {
        cityName.setCityName(firstWindow.getInputText().getText());
        parser.getResponse(connectorToWeatherSite.getConnection());
        parser.getResponseOnFiveDays(connectorToWeatherSite.getConnectionOnFiveDays());
    }
}