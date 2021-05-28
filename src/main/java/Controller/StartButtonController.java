package Controller;

import Model.CityName;
import Model.ConnectorToWeatherSite;
import View.FirstWindow;

public class StartButtonController {

    private final ConnectorToWeatherSite connectorToWeatherSite;
    private final FirstWindow firstWindow;
    private final CityName cityName;

    public StartButtonController(ConnectorToWeatherSite connector,FirstWindow firstW,CityName name) {
        this.connectorToWeatherSite = connector;
        this.firstWindow=firstW;
        this.cityName=name;
    }

    public void launchConnector(){
        cityName.setCityName(firstWindow.getInputText().getText());
        connectorToWeatherSite.getConnection();
    }
}