package prog.controller.controllersForDetails;

import prog.models.DataAnalyser;
import prog.web.JSONParser;
import prog.view.DetailInfo;

public class FourDayInfControl {
    private final DetailInfo detailInfo;
    private final JSONParser parser;
    private final DataAnalyser analyser;

    public FourDayInfControl(DetailInfo detailInfo, JSONParser jsonParser, DataAnalyser analyser) {
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