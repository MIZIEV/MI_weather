package Controller;

import Model.JSONDataParser;
import View.SecondWindow;
import javafx.scene.chart.XYChart;

import java.util.Iterator;

public class FourInfoController implements InfoButtonsControllers {

    private final SecondWindow secondWindow;
    private final JSONDataParser parser;

    public FourInfoController(SecondWindow secondW, JSONDataParser jsonParser) {
        this.secondWindow = secondW;
        this.parser = jsonParser;
    }

    @Override
    public void putDataToDiagram() {
        secondWindow.getTemp().getData().clear();
        Iterator<String> iterator = parser.getTempMap().
                tailMap(parser.getMapKeys().get(parser.getIndexList().get(3))).
                headMap(parser.getMapKeys().get(parser.getIndexList().get(4)+1)).keySet().iterator();

        while (iterator.hasNext()) {
            String element = iterator.next();
            String[] bufferTime = element.split("\\s");
            int time = Integer.parseInt(bufferTime[2]);
            if (time == 0 & element.equals(parser.getMapKeys().get(parser.getIndexList().get(4)))) time = 24;
            secondWindow.getTemp().getData().add(new XYChart.Data(time, parser.getTempMap().get(element) - 273));
        }
    }
}