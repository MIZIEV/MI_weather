package Controller;

import Model.JSONDataParser;
import View.SecondWindow;
import javafx.scene.chart.XYChart;

import java.util.Iterator;

public class TodayInfoController implements InfoButtonsControllers {

    private final SecondWindow secondWindow;
    private final JSONDataParser parser;

    public TodayInfoController(SecondWindow secondW, JSONDataParser jsonParser) {
        this.secondWindow = secondW;
        this.parser = jsonParser;
    }

    @Override
    public void putDataToDiagram() {
        secondWindow.getTemp().getData().clear();
        Iterator<String> iterator = parser.getTempMap().keySet().iterator();

        while (iterator.hasNext()) {

            String element = iterator.next();
            String bufferTime[] = element.split("\\s");
            String stringTime = bufferTime[2];
            int time = Integer.parseInt(stringTime);
/*            System.out.println("int time - " + time);
            System.out.println(parser.getTempMap().get(element) - 273);*/
            if (time == 0) time = 24;
            secondWindow.getTemp().getData().add(new XYChart.Data(time, parser.getTempMap().get(element) - 273));
            if (element.equals(parser.getMapKeys().get(parser.getIndexList().get(1)))) break;
        }
    }
}