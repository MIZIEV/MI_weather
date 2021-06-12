package Controller;

import Model.DataAnalyser;
import Model.JSONDataParser;
import View.GeneralInfo;

public class SecondDayInfControl {
    private final GeneralInfo generalInfo;
    private final JSONDataParser parser;
    private final DataAnalyser analyser;

    public SecondDayInfControl(GeneralInfo generalInfo, JSONDataParser jsonParser, DataAnalyser analyser) {
        this.generalInfo = generalInfo;
        this.parser = jsonParser;
        this.analyser = analyser;
    }

    public void putDataToPane() {
        generalInfo.setMinTempInt(analyser.minTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getIndexList().get(1))).
                headMap(parser.getKeysForMap().get(parser.getIndexList().get(2) + 1))) - 273);
        generalInfo.setMaxTempInt(analyser.maxTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getIndexList().get(1))).
                headMap(parser.getKeysForMap().get(parser.getIndexList().get(2) + 1))) - 273);
        generalInfo.setWeatherString(parser.getWeatherSecondDay());
        generalInfo.setWindSpeedDouble(parser.getSpeedWindList().get(1));
        generalInfo.setHumidityDouble(parser.getHumidityList().get(1));
    }
}