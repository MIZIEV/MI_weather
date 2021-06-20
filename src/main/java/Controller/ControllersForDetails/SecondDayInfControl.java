package Controller.ControllersForDetails;

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
                tailMap(parser.getKeysForMap().get(parser.getSecondDayIndex().getStartDayIndex())).
                headMap(parser.getKeysForMap().get(parser.getSecondDayIndex().getEndDayIndex() + 1))) - 273);
        generalInfo.setMaxTempInt(analyser.maxTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getSecondDayIndex().getStartDayIndex())).
                headMap(parser.getKeysForMap().get(parser.getSecondDayIndex().getEndDayIndex() + 1))) - 273);
        generalInfo.setWeatherString(parser.getWeatherSecondDay());
        generalInfo.setWindSpeedDouble(parser.getSpeedWindList().get(1));
        generalInfo.setHumidityDouble(parser.getHumidityList().get(1));
        generalInfo.setWindDirectionInt(parser.getWindDirection().get(1));

    }
}