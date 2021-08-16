package prog.controller.controllersForDetails;

import prog.model.DataAnalyser;
import prog.model.web.JSONDataParser;
import prog.view.DetailInfo;

public class FifthDayControl {
    private final DetailInfo detailInfo;
    private final JSONDataParser parser;
    private final DataAnalyser analyser;

    public FifthDayControl(DetailInfo detailInfo, JSONDataParser jsonParser, DataAnalyser analyser) {
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