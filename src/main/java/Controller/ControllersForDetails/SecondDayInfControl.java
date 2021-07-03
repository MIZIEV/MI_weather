package Controller.ControllersForDetails;

import Model.DataAnalyser;
import Model.JSONDataParser;
import View.DetailInfo;

public class SecondDayInfControl {
    private final DetailInfo detailInfo;
    private final JSONDataParser parser;
    private final DataAnalyser analyser;

    public SecondDayInfControl(DetailInfo detailInfo, JSONDataParser jsonParser, DataAnalyser analyser) {
        this.detailInfo = detailInfo;
        this.parser = jsonParser;
        this.analyser = analyser;
    }

    public void putDataToPane() {
        detailInfo.setMinTempInt(analyser.minTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getSecondDayIndex().getStartDayIndex())).
                headMap(parser.getKeysForMap().get(parser.getSecondDayIndex().getEndDayIndex() + 1))));
        detailInfo.setMaxTempInt(analyser.maxTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getSecondDayIndex().getStartDayIndex())).
                headMap(parser.getKeysForMap().get(parser.getSecondDayIndex().getEndDayIndex() + 1))));
        detailInfo.setWeatherString(parser.getWeatherSecondDay());
        detailInfo.setWindSpeedDouble(parser.getSpeedWindList().get(1));
        detailInfo.setHumidityDouble(parser.getHumidityList().get(1));
        detailInfo.setWindDirectionInt(parser.getWindDirection().get(1));

    }
}