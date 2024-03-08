package prog.workWIthDB;

import prog.models.MeasurementsFromDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * class description:
 * this class is deeded to transform data from the database to show it on the pane
 */
public class DBAnalyzer {

    public HashMap<String, Integer> transformData(ArrayList<MeasurementsFromDB> list) {

        HashMap<String, Integer> dataForPieChart = new HashMap<>();
        HashSet<String> cityName = new HashSet<>();

        for (MeasurementsFromDB element : list) {
            cityName.add(element.getCityName());
        }

        for (String element : cityName) {
            int counter = 0;
            for (MeasurementsFromDB listElement : list) {
                if (element.equals(listElement.getCityName())) counter++;
                dataForPieChart.put(element, counter);
            }
        }
        return dataForPieChart;
    }
}