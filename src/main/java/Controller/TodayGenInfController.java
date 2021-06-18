package Controller;

import Model.DataAnalyser;
import Model.JSONDataParser;
import View.GeneralInfo;

public class TodayGenInfController {
    private final GeneralInfo generalInfo;
    private final JSONDataParser parser;
    private final DataAnalyser analyser;

    public TodayGenInfController(GeneralInfo generalInfo, JSONDataParser jsonParser, DataAnalyser analyser) {
        this.generalInfo = generalInfo;
        this.parser = jsonParser;
        this.analyser = analyser;
    }

    public void putDataToPane() {
        generalInfo.setMinTempInt(analyser.minTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getIndexList().get(0))).
                headMap(parser.getKeysForMap().get(parser.getIndexList().get(1) + 1))) - 273);
        generalInfo.setMaxTempInt(analyser.maxTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getIndexList().get(0))).
                headMap(parser.getKeysForMap().get(parser.getIndexList().get(1) + 1))) - 273);
        generalInfo.setWeatherString(parser.getWeatherNow());
        generalInfo.setWindSpeedDouble(parser.getSpeedWindList().get(0));
        generalInfo.setHumidityDouble(parser.getHumidityList().get(0));
        generalInfo.setWindDirectionInt(parser.getWindDirection().get(0));
    }
}