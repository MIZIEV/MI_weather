package Model;

import java.util.ArrayList;

public class DataAnalyser {

    public int minTemp(ArrayList<Integer> list) {
        int minTemp = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < minTemp) {
                minTemp = list.get(i);
            }
        }
        return minTemp;
    }

    public int maxTemp(ArrayList<Integer> list) {
        int maxTemp = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > maxTemp) {
                maxTemp = list.get(i);
            }
        }
        return maxTemp;
    }
}