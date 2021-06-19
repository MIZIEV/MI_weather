package Controller.ControllersForDiagram;

import Model.JSONDataParser;
import View.Diagram;
import View.SecondWindow;
import javafx.scene.chart.XYChart;

public class ThirdDayInfoController implements InfoButtonsControllers {

    private final Diagram diagram;
    private final JSONDataParser parser;

    public ThirdDayInfoController(Diagram diagram, JSONDataParser jsonParser) {
        this.diagram = diagram;
        this.parser = jsonParser;
    }

    @Override
    public void putDataToDiagram() {
        diagram.getTemp().getData().clear();

        for (String element : parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getIndexList().get(2))).
                headMap(parser.getKeysForMap().get(parser.getIndexList().get(3) + 1)).keySet()) {
            String[] bufferTime = element.split("\\s");
            int time = Integer.parseInt(bufferTime[2]);
            if (time == 0 & element.equals(parser.getKeysForMap().get(parser.getIndexList().get(3)))) time = 24;
            diagram.getTemp().getData().add(new XYChart.Data(time, parser.getTempMap().get(element) - 273));
        }
    }
}