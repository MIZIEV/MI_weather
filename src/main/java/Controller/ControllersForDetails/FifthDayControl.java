package Controller.ControllersForDetails;

import Model.DataAnalyser;
import Model.JSONDataParser;
import View.GeneralInfo;

public class FifthDayControl {
    private final GeneralInfo generalInfo;
    private final JSONDataParser parser;
    private final DataAnalyser analyser;

    public FifthDayControl(GeneralInfo generalInfo, JSONDataParser jsonParser, DataAnalyser analyser) {
        this.generalInfo = generalInfo;
        this.parser = jsonParser;
        this.analyser = analyser;
    }

    public void putDataToPane() {
        generalInfo.setMinTempInt(analyser.minTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getFifthDayIndex().getStartDayIndex())).
                headMap(parser.getKeysForMap().get(parser.getFifthDayIndex().getEndDayIndex() + 1))));
        generalInfo.setMaxTempInt(analyser.maxTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getFifthDayIndex().getStartDayIndex())).
                headMap(parser.getKeysForMap().get(parser.getFifthDayIndex().getEndDayIndex() + 1))));
        generalInfo.setWeatherString(parser.getWeatherFifthDay());
        generalInfo.setWindSpeedDouble(parser.getSpeedWindList().get(4));
        generalInfo.setHumidityDouble(parser.getHumidityList().get(4));
        generalInfo.setWindDirectionInt(parser.getWindDirection().get(4));
    }
}