package Controller.ControllersForDetails;

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
                tailMap(parser.getKeysForMap().get(parser.getFourDayIndex().getStartDayIndex())).
                headMap(parser.getKeysForMap().get(parser.getFourDayIndex().getEndDayIndex() + 1))) - 273);
        generalInfo.setMaxTempInt(analyser.maxTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getFourDayIndex().getStartDayIndex())).
                headMap(parser.getKeysForMap().get(parser.getFourDayIndex().getEndDayIndex() + 1))) - 273);
        generalInfo.setWeatherString(parser.getWeatherFourthDay());
        generalInfo.setWindSpeedDouble(parser.getSpeedWindList().get(3));
        generalInfo.setHumidityDouble(parser.getHumidityList().get(3));
        generalInfo.setWindDirectionInt(parser.getWindDirection().get(3));
    }
}