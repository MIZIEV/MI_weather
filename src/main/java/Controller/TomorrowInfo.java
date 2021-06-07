package Controller;

import Model.JSONDataParser;
import View.SecondWindow;
import javafx.scene.chart.XYChart;

public class TomorrowInfo implements InfoButtonsControllers{
    private final SecondWindow secondWindow;
    private final JSONDataParser parser;

    public TomorrowInfo(SecondWindow secondW, JSONDataParser jsonParser) {
        this.secondWindow = secondW;
        this.parser = jsonParser;
    }

    public void putDataToDiagram() {
        secondWindow.getTemp().getData().clear();
        secondWindow.getTemp().getData().add(new XYChart.Data(0, parser.getTempListTomorrow().get(0) - 273));
        secondWindow.getTemp().getData().add(new XYChart.Data(3, parser.getTempListTomorrow().get(1) - 273));
        secondWindow.getTemp().getData().add(new XYChart.Data(6, parser.getTempListTomorrow().get(2) - 273));
        secondWindow.getTemp().getData().add(new XYChart.Data(9, parser.getTempListTomorrow().get(3) - 273));
        secondWindow.getTemp().getData().add(new XYChart.Data(12, parser.getTempListTomorrow().get(4) - 273));
        secondWindow.getTemp().getData().add(new XYChart.Data(15, parser.getTempListTomorrow().get(5) - 273));
        secondWindow.getTemp().getData().add(new XYChart.Data(18, parser.getTempListTomorrow().get(6) - 273));
        secondWindow.getTemp().getData().add(new XYChart.Data(21, parser.getTempListTomorrow().get(7) - 273));
        secondWindow.getTemp().getData().add(new XYChart.Data(24, parser.getTempListTomorrow().get(8) - 273));
    }
}