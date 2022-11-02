package prog.controller.controllersForDetails;

import prog.models.DataAnalyser;
import prog.web.JSONParser;
import prog.view.DetailInfo;

public class FifthDayControl {
    private final DetailInfo detailInfo;
    private final JSONParser parser;
    private final DataAnalyser analyser;

    public FifthDayControl(DetailInfo detailInfo, JSONParser jsonParser, DataAnalyser analyser) {
        this.detailInfo = detailInfo;
        this.parser = jsonParser;
        this.analyser = analyser;
    }

    public void putDataToPane() {
        detailInfo.setMinTempInt(analyser.minTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getFifthDayIndex().getStartDayIndex())).
                headMap(parser.getKeysForMap().get(parser.getFifthDayIndex().getEndDayIndex() + 1))));
        detailInfo.setMaxTempInt(analyser.maxTemp(parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getFifthDayIndex().getStartDayIndex())).
                headMap(parser.getKeysForMap().get(parser.getFifthDayIndex().getEndDayIndex() + 1))));
        detailInfo.setWeatherString(parser.getWeatherFifthDay());
        detailInfo.setWindSpeedDouble(parser.getSpeedWindList().get(4));
        detailInfo.setHumidityDouble(parser.getHumidityList().get(4));
        detailInfo.setWindDirectionInt(parser.getWindDirection().get(4));
    }
}