package Model;

import java.util.Iterator;
import java.util.SortedMap;

public class DataAnalyser {

    public int maxTemp(SortedMap<String, Integer> map) {
        Iterator<Integer> iterator = map.values().iterator();
        int max = map.get(map.firstKey());
        while (iterator.hasNext()) {
            int element = iterator.next();
            if (max < element) {
                max = element;
            }
        }
        return max;
    }

    public int minTemp(SortedMap<String, Integer> map) {
        Iterator<Integer> iterator = map.values().iterator();
        int min = map.get(map.firstKey());
        while (iterator.hasNext()) {
            int element = iterator.next();
            if (min > element) {
                min = element;
            }
        }
        return min;
    }
}