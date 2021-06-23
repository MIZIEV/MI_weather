package Controller.ControllersForDiagram;

import Controller.ControllersForDiagram.InfoButtonsControllers;
import Model.JSONDataParser;
import View.Diagram;
import View.SecondWindow;
import javafx.scene.chart.XYChart;

public class TodayInfoController implements InfoButtonsControllers {

    private final Diagram diagram;
    private final JSONDataParser parser;

    public TodayInfoController(Diagram diagram, JSONDataParser jsonParser) {
        this.diagram = diagram;
        this.parser = jsonParser;
    }

    @Override
    public void putDataToDiagram() {
        diagram.getTemp().getData().clear();

        for (String element : parser.getTempMap().keySet()) {
            String bufferTime[] = element.split("\\s");
            String stringTime = bufferTime[2];
            int time = Integer.parseInt(stringTime);
            if (time == 0) time = 24;
            diagram.getTemp().getData().add(new XYChart.Data(time, parser.getTempMap().get(element)));
            if (element.equals(parser.getKeysForMap().get(parser.getTodayIndex().getEndDayIndex()))) break;
        }
    }
}