package Controller;

import Model.JSONDataParser;
import View.SecondWindow;
import javafx.scene.chart.XYChart;

public class AfterTomorrowInfoController implements InfoButtonsControllers {

    private final SecondWindow secondWindow;
    private final JSONDataParser parser;

    public AfterTomorrowInfoController(SecondWindow secondW, JSONDataParser jsonParser) {
        this.secondWindow = secondW;
        this.parser = jsonParser;
    }

    @Override
    public void putDataToDiagram() {
        secondWindow.getTemp().getData().clear();
        secondWindow.getTemp().getData().add(new XYChart.Data(0, parser.getTempListAfterTomorrow().get(0) - 273));
        secondWindow.getTemp().getData().add(new XYChart.Data(3, parser.getTempListAfterTomorrow().get(1) - 273));
        secondWindow.getTemp().getData().add(new XYChart.Data(6, parser.getTempListAfterTomorrow().get(2) - 273));
        secondWindow.getTemp().getData().add(new XYChart.Data(9, parser.getTempListAfterTomorrow().get(3) - 273));
        secondWindow.getTemp().getData().add(new XYChart.Data(12, parser.getTempListAfterTomorrow().get(4) - 273));
        secondWindow.getTemp().getData().add(new XYChart.Data(15, parser.getTempListAfterTomorrow().get(5) - 273));
        secondWindow.getTemp().getData().add(new XYChart.Data(18, parser.getTempListAfterTomorrow().get(6) - 273));
        secondWindow.getTemp().getData().add(new XYChart.Data(21, parser.getTempListAfterTomorrow().get(7) - 273));
        secondWindow.getTemp().getData().add(new XYChart.Data(24, parser.getTempListAfterTomorrow().get(8) - 273));
    }
}