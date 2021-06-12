package Controller;

import Model.DataAnalyser;
import Model.JSONDataParser;
import View.GeneralInfo;

public class ThirdDayInfControl {
    private final GeneralInfo generalInfo;
    private final JSONDataParser parser;
    private final DataAnalyser analyser;

    public ThirdDayInfControl(GeneralInfo generalInfo, JSONDataParser jsonParser, DataAnalyser analyser) {
        this.generalInfo = generalInfo;
        this.parser = jsonParser;
        this.analyser = analyser;
    }

    public void putDataToPane() {
        generalInfo.setMinTempInt(analyser.minTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getIndexList().get(2))).
                headMap(parser.getKeysForMap().get(parser.getIndexList().get(3) + 1))) - 273);
        generalInfo.setMaxTempInt(analyser.maxTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getIndexList().get(2))).
                headMap(parser.getKeysForMap().get(parser.getIndexList().get(3) + 1))) - 273);
        generalInfo.setWeatherString(parser.getWeatherThirdDay());
        generalInfo.setWindSpeedDouble(parser.getSpeedWindList().get(2));
        generalInfo.setHumidityDouble(parser.getHumidityList().get(2));
    }
}