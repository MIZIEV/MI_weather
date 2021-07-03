package Controller.ControllersForDetails;

import Model.DataAnalyser;
import Model.JSONDataParser;
import View.DetailInfo;

public class TodayInfoControl {
    private final DetailInfo detailInfo;
    private final JSONDataParser parser;
    private final DataAnalyser analyser;

    public TodayInfoControl(DetailInfo detailInfo, JSONDataParser jsonParser, DataAnalyser analyser) {
        this.detailInfo = detailInfo;
        this.parser = jsonParser;
        this.analyser = analyser;
    }

    public void putDataToPane() {
        detailInfo.setMinTempInt(analyser.minTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getTodayIndex().getStartDayIndex())).
                headMap(parser.getKeysForMap().get(parser.getTodayIndex().getEndDayIndex() + 1))));
        detailInfo.setMaxTempInt(analyser.maxTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getTodayIndex().getStartDayIndex())).
                headMap(parser.getKeysForMap().get(parser.getTodayIndex().getStartDayIndex() + 1))));
        detailInfo.setWeatherString(parser.getWeatherNow());
        detailInfo.setWindSpeedDouble(parser.getSpeedWindList().get(0));
        detailInfo.setHumidityDouble(parser.getHumidityList().get(0));
        detailInfo.setWindDirectionInt(parser.getWindDirection().get(0));
    }
}