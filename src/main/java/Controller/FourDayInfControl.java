package Controller;

import Model.DataAnalyser;
import Model.JSONDataParser;
import View.GeneralInfo;

public class FourDayInfControl {
    private final GeneralInfo generalInfo;
    private final JSONDataParser parser;
    private final DataAnalyser analyser;

    public FourDayInfControl(GeneralInfo generalInfo, JSONDataParser jsonParser, DataAnalyser analyser) {
        this.generalInfo = generalInfo;
        this.parser = jsonParser;
        this.analyser = analyser;
    }

    public void putDataToPane() {
        generalInfo.setMinTempInt(analyser.minTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getIndexList().get(3))).
                headMap(parser.getKeysForMap().get(parser.getIndexList().get(4) + 1))) - 273);
        generalInfo.setMaxTempInt(analyser.maxTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getIndexList().get(3))).
                headMap(parser.getKeysForMap().get(parser.getIndexList().get(4) + 1))) - 273);
        generalInfo.setWeatherString(parser.getWeatherFourthDay());
        generalInfo.setWindSpeedDouble(parser.getSpeedWindList().get(3));
        generalInfo.setHumidityDouble(parser.getHumidityList().get(3));
    }
}