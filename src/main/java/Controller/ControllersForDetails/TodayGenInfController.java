package Controller.ControllersForDetails;

import Model.DataAnalyser;
import Model.IndexClass;
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
                tailMap(parser.getKeysForMap().get(parser.getTodayIndex().getStartDayIndex())).
                headMap(parser.getKeysForMap().get(parser.getTodayIndex().getEndDayIndex() + 1))) - 273);
        generalInfo.setMaxTempInt(analyser.maxTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getTodayIndex().getStartDayIndex())).
                headMap(parser.getKeysForMap().get(parser.getTodayIndex().getStartDayIndex() + 1))) - 273);
        generalInfo.setWeatherString(parser.getWeatherNow());
        generalInfo.setWindSpeedDouble(parser.getSpeedWindList().get(0));
        generalInfo.setHumidityDouble(parser.getHumidityList().get(0));
        generalInfo.setWindDirectionInt(parser.getWindDirection().get(0));
    }
}