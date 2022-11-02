package prog.controller.controllersForDetails;

import prog.models.DataAnalyser;
import prog.web.JSONParser;
import prog.view.DetailInfo;

public class ThirdDayInfControl {
    private final DetailInfo detailInfo;
    private final JSONParser parser;
    private final DataAnalyser analyser;

    public ThirdDayInfControl(DetailInfo detailInfo, JSONParser jsonParser, DataAnalyser analyser) {
        this.detailInfo = detailInfo;
        this.parser = jsonParser;
        this.analyser = analyser;
    }

    public void putDataToPane() {
        detailInfo.setMinTempInt(analyser.minTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getThirdDayIndex().getStartDayIndex())).
                headMap(parser.getKeysForMap().get(parser.getThirdDayIndex().getEndDayIndex() + 1))));
        detailInfo.setMaxTempInt(analyser.maxTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getThirdDayIndex().getStartDayIndex())).
                headMap(parser.getKeysForMap().get(parser.getThirdDayIndex().getEndDayIndex() + 1))));
        detailInfo.setWeatherString(parser.getWeatherThirdDay());
        detailInfo.setWindSpeedDouble(parser.getSpeedWindList().get(2));
        detailInfo.setHumidityDouble(parser.getHumidityList().get(2));
        detailInfo.setWindDirectionInt(parser.getWindDirection().get(2));
    }
}