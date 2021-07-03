package Controller.ControllersForDetails;

import Model.DataAnalyser;
import Model.JSONDataParser;
import View.DetailInfo;

public class FourDayInfControl {
    private final DetailInfo detailInfo;
    private final JSONDataParser parser;
    private final DataAnalyser analyser;

    public FourDayInfControl(DetailInfo detailInfo, JSONDataParser jsonParser, DataAnalyser analyser) {
        this.detailInfo = detailInfo;
        this.parser = jsonParser;
        this.analyser = analyser;
    }

    public void putDataToPane() {
        detailInfo.setMinTempInt(analyser.minTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getFourDayIndex().getStartDayIndex())).
                headMap(parser.getKeysForMap().get(parser.getFourDayIndex().getEndDayIndex() + 1))));
        detailInfo.setMaxTempInt(analyser.maxTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getFourDayIndex().getStartDayIndex())).
                headMap(parser.getKeysForMap().get(parser.getFourDayIndex().getEndDayIndex() + 1))));
        detailInfo.setWeatherString(parser.getWeatherFourthDay());
        detailInfo.setWindSpeedDouble(parser.getSpeedWindList().get(3));
        detailInfo.setHumidityDouble(parser.getHumidityList().get(3));
        detailInfo.setWindDirectionInt(parser.getWindDirection().get(3));
    }
}