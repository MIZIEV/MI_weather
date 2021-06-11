package Model;
/**
 * this is class, where JSON data parsing, formatting and placed to the Lists & Map
 * also it has getters for works with this data
 */
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.TreeMap;

public class JSONDataParser {

    private String tempNow;
    private String weatherNow;

    private String todayDate;
    private String weatherToday;

    private String dateSecondDay;
    private String weatherSecondDay;

    private String dateThirdDay;
    private String weatherThirdDay;

    private String dateFourDay;
    private String weatherFourthDay;

    private String dateFifthDay;
    private String weatherFifthDay;

    private final ArrayList<Integer> indexList = new ArrayList<>();
    private final TreeMap<String, Integer> tempMap = new TreeMap<>();
    private final ArrayList<String> keysForMap = new ArrayList<>();

    public void getResponse(String output) {
        if (!output.isEmpty()) {
            JSONObject obj = new JSONObject(output);
            tempNow = obj.getJSONObject("main").getInt("temp") + "°C";
            weatherNow = obj.getJSONArray("weather").getJSONObject(0).getString("description");
        }
    }

    public void getResponseOnFiveDays(String output) {
        if (!output.isEmpty()) {
            JSONObject obj = new JSONObject(output);

            int counter = 0;
            int todayDay = 0, tomorrowDay = 0, afterTomorrowDay = 0, fourDay = 0, fifthDay = 0, lastDay = 0;
            int jsonListLength = obj.getJSONArray("list").length();

            while (counter <= jsonListLength - 1) {
                String data = obj.getJSONArray("list").getJSONObject(counter).getString("dt_txt");
                String onlyData[] = data.split("\\s");
                String onlyDay[] = onlyData[0].split("\\-");

                int bufferVar = Integer.parseInt(onlyDay[2]);
                if (todayDay == 0) {            // all this "if" construction need for get index, where date changing
                    todayDay = bufferVar;       // and put this index to indexList
                    indexList.add(counter);
                }
                if (todayDay != bufferVar & tomorrowDay == 0) {
                    tomorrowDay = bufferVar;
                    indexList.add(counter);
                }
                if (tomorrowDay != bufferVar & todayDay != bufferVar & afterTomorrowDay == 0) {
                    afterTomorrowDay = bufferVar;
                    indexList.add(counter);
                }
                if (bufferVar > afterTomorrowDay & todayDay != bufferVar & tomorrowDay != bufferVar
                        & afterTomorrowDay != bufferVar & fourDay == 0) {
                    fourDay = bufferVar;
                    indexList.add(counter);
                }
                if (bufferVar > fourDay & todayDay != bufferVar & tomorrowDay != bufferVar
                        & afterTomorrowDay != bufferVar & fourDay != bufferVar & fifthDay == 0) {
                    fifthDay = bufferVar;
                    indexList.add(counter);
                }
                if (bufferVar > fourDay & todayDay != bufferVar & tomorrowDay != bufferVar
                        & afterTomorrowDay != bufferVar & fourDay != bufferVar &
                        fifthDay != bufferVar & lastDay == 0) {
                    lastDay = bufferVar;
                    indexList.add(counter);
                }

                // filter data to format:  key -( mount,day,hour ), value
                String dataForMap = obj.getJSONArray("list").getJSONObject(counter).getString("dt_txt");
                String filteredData[] = dataForMap.split("\\-|\\s|\\:");
                //  put filtered data to one treeMap
                tempMap.put(filteredData[1] + " " + filteredData[2] + " " + filteredData[3],
                        obj.getJSONArray("list").getJSONObject(counter).getJSONObject("main").getInt("temp"));
                // put the same filtered keys to ArrayList, they will be comparing with Map in another class
                keysForMap.add(filteredData[1] + " " + filteredData[2] + " " + filteredData[3]);
                counter++;
            }

            todayDate = obj.getJSONArray("list").getJSONObject(indexList.get(0)).getString("dt_txt");
            String todayBuf[] = todayDate.split("\\s");
            todayDate = todayBuf[0];
            weatherToday = obj.getJSONArray("list").getJSONObject(indexList.get(0)).getJSONArray("weather").getJSONObject(0).getString("description");

            dateSecondDay = obj.getJSONArray("list").getJSONObject(indexList.get(1)).getString("dt_txt");
            String tomorrowBuf[] = dateSecondDay.split("\\s");
            dateSecondDay = tomorrowBuf[0];
            weatherSecondDay = obj.getJSONArray("list").getJSONObject(indexList.get(1)).getJSONArray("weather").getJSONObject(0).getString("description");

            dateThirdDay = obj.getJSONArray("list").getJSONObject(indexList.get(2)).getString("dt_txt");
            String dayAfterTomorrowBuf[] = dateThirdDay.split("\\s");
            dateThirdDay = dayAfterTomorrowBuf[0];
            weatherThirdDay = obj.getJSONArray("list").getJSONObject(indexList.get(2)).getJSONArray("weather").getJSONObject(0).getString("description");

            dateFourDay = obj.getJSONArray("list").getJSONObject(indexList.get(3)).getString("dt_txt");
            String fourthDayBuf[] = dateFourDay.split("\\s");
            dateFourDay = fourthDayBuf[0];
            weatherFourthDay = obj.getJSONArray("list").getJSONObject(indexList.get(3)).getJSONArray("weather").getJSONObject(0).getString("description");

            dateFifthDay = obj.getJSONArray("list").getJSONObject(indexList.get(4)).getString("dt_txt");
            String fifthDataBuf[] = dateFifthDay.split("\\s");
            dateFifthDay = fifthDataBuf[0];
            weatherFifthDay = obj.getJSONArray("list").getJSONObject(indexList.get(4)).getJSONArray("weather").getJSONObject(0).getString("description");
        }
    }

    public String getTempNow() { return tempNow; }

    public String getWeatherNow() { return weatherNow; }

    public String getTodayDate() { return todayDate; }

    public String getWeatherToday() { return weatherToday; }

    public String getDateSecondDay() { return dateSecondDay; }

    public String getWeatherSecondDay() { return weatherSecondDay; }

    public String getDateThirdDay() { return dateThirdDay; }

    public String getWeatherThirdDay() { return weatherThirdDay; }

    public String getDateFourDay() { return dateFourDay; }

    public String getWeatherFourthDay() { return weatherFourthDay; }

    public String getDateFifthDay() { return dateFifthDay; }

    public String getWeatherFifthDay() { return weatherFifthDay; }

    public TreeMap<String, Integer> getTempMap() { return tempMap; }

    public ArrayList<String> getKeysForMap() { return keysForMap; }

    public ArrayList<Integer> getIndexList() { return indexList; }

    public void setTempNow(String tempNow) { this.tempNow = tempNow; }

    public void setTodayDate(String todayDate) { this.todayDate = todayDate; }
}