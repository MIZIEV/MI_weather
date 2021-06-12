package Controller;

import Model.JSONDataParser;
import View.SecondWindow;
import javafx.scene.chart.XYChart;

public class ThirdDayInfoController implements InfoButtonsControllers {

    private final SecondWindow secondWindow;
    private final JSONDataParser parser;

    public ThirdDayInfoController(SecondWindow secondW, JSONDataParser jsonParser) {
        this.secondWindow = secondW;
        this.parser = jsonParser;
    }

    @Override
    public void putDataToDiagram() {
        secondWindow.getTemp().getData().clear();

        for (String element : parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getIndexList().get(2))).
                headMap(parser.getKeysForMap().get(parser.getIndexList().get(3) + 1)).keySet()) {
            String[] bufferTime = element.split("\\s");
            int time = Integer.parseInt(bufferTime[2]);
            if (time == 0 & element.equals(parser.getKeysForMap().get(parser.getIndexList().get(3)))) time = 24;
            secondWindow.getTemp().getData().add(new XYChart.Data(time, parser.getTempMap().get(element) - 273));
        }
    }
}