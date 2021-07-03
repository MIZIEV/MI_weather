package Model.DBModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * class description:
 * this class is deeded to transform data from the database to show it on the pane
 */
public class DBAnalyzer {

    public HashMap<String, Integer> transformData(ArrayList<DataFromDB> list) {

        HashMap<String, Integer> dataForPieChart = new HashMap<>();
        HashSet<String> cityName = new HashSet<>();

        for (DataFromDB element : list) {
            cityName.add(element.getCityName());
        }

        for (String element : cityName) {
            int counter = 0;
            for (DataFromDB listElement : list) {
                if (element.equals(listElement.getCityName())) counter++;
                dataForPieChart.put(element, counter);
            }
        }
        return dataForPieChart;
    }
}