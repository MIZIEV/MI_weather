package prog.controller.controllersForDiagram;

import prog.web.JSONParser;
import prog.view.Diagram;
import javafx.scene.chart.XYChart;

public class ThirdDayInfoController implements InfoButtonsControllers {

    private final Diagram diagram;
    private final JSONParser parser;

    public ThirdDayInfoController(Diagram diagram, JSONParser jsonParser) {
        this.diagram = diagram;
        this.parser = jsonParser;
    }

    @Override
    public void putDataToDiagram() {
        diagram.getTemp().getData().clear();

        for (String element : parser.getTempMap().
                tailMap(parser.getKeysForMap().get(parser.getThirdDayIndex().getStartDayIndex())).
                headMap(parser.getKeysForMap().get(parser.getThirdDayIndex().getEndDayIndex() + 1)).keySet()) {
            String[] bufferTime = element.split("\\s");
            int time = Integer.parseInt(bufferTime[2]);
            if (time == 0 & element.equals(parser.getKeysForMap().get(parser.getThirdDayIndex().getEndDayIndex()))) time = 24;
            diagram.getTemp().getData().add(new XYChart.Data(time, parser.getTempMap().get(element)));
        }
    }
}