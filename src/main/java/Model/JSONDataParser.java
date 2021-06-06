package Model;

import org.json.JSONObject;

import java.util.ArrayList;

public class JSONDataParser {

    private String temperature;
    private String minTemp;
    private String maxTemp;
    private String clouds;

    private String today;
    private String cloudsToday;
    private int minTempToday;
    private int maxTempToday;

    private String tomorrowData;
    private String cloudsTomorrow;
    private int minTempTomorrowDay;
    private int maxTempTomorrowDay;

    private String dayAfterTomorrow;
    private String cloudsAfterTomorrow;
    private int minTempAfterTomorrow;
    private int maxTempAfterTomorrow;

    private String fourthDay;
    private String cloudsFourthDay;
    private int minTempFourthDay;
    private int maxTempFourthDay;

    private String fifthData;
    private String cloudsFifthDay;
    private int minTempFifthDay;
    private int maxTempFifthDay;

    private final ArrayList<Integer> indexList = new ArrayList<>();
    private final ArrayList<Integer> tempListOnFiveDays = new ArrayList<>();
    private final ArrayList<Integer> tempListTomorrow = new ArrayList<>();
    private final ArrayList<Integer> tempListAfterTomorrow = new ArrayList<>();
    private final ArrayList<Integer> tempListFourDay = new ArrayList<>();
    private final ArrayList<Integer> tempListFifthDay = new ArrayList<>();

    public void getResponse(String output) {
        if (!output.isEmpty()) {

            JSONObject obj = new JSONObject(output);

            temperature = obj.getJSONObject("main").getDouble("temp") + " C";
            minTemp = obj.getJSONObject("main").getDouble("temp_min") + " C ;";
            maxTemp = obj.getJSONObject("main").getDouble("temp_max") + " C";
            clouds = obj.getJSONArray("weather").getJSONObject(0).getString("description");
        }
    }

    public void getResponseOnFiveDays(String output) {
        if (!output.isEmpty()) {
            JSONObject obj = new JSONObject(output);
            String data;
            String onlyData[];
            String onlyDay[];
            int index;
            int counter = 0;
            int todayDay = 0, tomorrowDay = 0, afterTomorrowDay = 0, fourDay = 0, fifthDay = 0;

            index = obj.getJSONArray("list").length();
            System.out.println("JSON length - " + index);

            while (counter <= index - 1) {
                data = obj.getJSONArray("list").getJSONObject(counter).getString("dt_txt");
                onlyData = data.split("\\s");

                onlyDay = onlyData[0].split("\\-");

                int bufferVar = Integer.parseInt(onlyDay[2]);
                if (todayDay == 0) {
                    todayDay = bufferVar;
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
                tempListOnFiveDays.add(obj.getJSONArray("list").getJSONObject(counter).getJSONObject("main").getInt("temp"));
                counter++;
            }

            tempListTomorrow.addAll(tempListOnFiveDays.subList(indexList.get(1), indexList.get(2) + 1));
            tempListAfterTomorrow.addAll(tempListOnFiveDays.subList(indexList.get(2), indexList.get(3) + 1));
            tempListFourDay.addAll(tempListOnFiveDays.subList(indexList.get(3), indexList.get(4) + 1));
            tempListFifthDay.addAll(tempListOnFiveDays.subList(indexList.get(4), tempListOnFiveDays.size() - 5));

            today = obj.getJSONArray("list").getJSONObject(indexList.get(0)).getString("dt_txt");
            String todayBuf[] = today.split("\\s");
            today = todayBuf[0];
            cloudsToday = obj.getJSONArray("list").getJSONObject(indexList.get(0)).getJSONArray("weather").getJSONObject(0).getString("description");
            minTempToday = obj.getJSONArray("list").getJSONObject(indexList.get(0)).getJSONObject("main").getInt("temp_min");
            maxTempToday = obj.getJSONArray("list").getJSONObject(indexList.get(0)).getJSONObject("main").getInt("temp_max");

            tomorrowData = obj.getJSONArray("list").getJSONObject(indexList.get(1)).getString("dt_txt");
            String tomorrowBuf[] = tomorrowData.split("\\s");
            tomorrowData = tomorrowBuf[0];
            cloudsTomorrow = obj.getJSONArray("list").getJSONObject(indexList.get(1)).getJSONArray("weather").getJSONObject(0).getString("description");
            minTempTomorrowDay = obj.getJSONArray("list").getJSONObject(indexList.get(1)).getJSONObject("main").getInt("temp_min");
            maxTempTomorrowDay = obj.getJSONArray("list").getJSONObject(indexList.get(1)).getJSONObject("main").getInt("temp_max");

            dayAfterTomorrow = obj.getJSONArray("list").getJSONObject(indexList.get(2)).getString("dt_txt");
            String dayAfterTomorrowBuf[] = dayAfterTomorrow.split("\\s");
            dayAfterTomorrow = dayAfterTomorrowBuf[0];
            cloudsAfterTomorrow = obj.getJSONArray("list").getJSONObject(indexList.get(2)).getJSONArray("weather").getJSONObject(0).getString("description");
            minTempAfterTomorrow = obj.getJSONArray("list").getJSONObject(indexList.get(2)).getJSONObject("main").getInt("temp_min");
            maxTempAfterTomorrow = obj.getJSONArray("list").getJSONObject(indexList.get(2)).getJSONObject("main").getInt("temp_max");

            fourthDay = obj.getJSONArray("list").getJSONObject(indexList.get(3)).getString("dt_txt");
            String fourthDayBuf[] = fourthDay.split("\\s");
            fourthDay = fourthDayBuf[0];
            cloudsFourthDay = obj.getJSONArray("list").getJSONObject(indexList.get(3)).getJSONArray("weather").getJSONObject(0).getString("description");
            minTempFourthDay = obj.getJSONArray("list").getJSONObject(indexList.get(3)).getJSONObject("main").getInt("temp_min");
            maxTempFourthDay = obj.getJSONArray("list").getJSONObject(indexList.get(3)).getJSONObject("main").getInt("temp_max");

            fifthData = obj.getJSONArray("list").getJSONObject(indexList.get(4)).getString("dt_txt");
            String fifthDataBuf[] = fifthData.split("\\s");
            fifthData = fifthDataBuf[0];
            cloudsFifthDay = obj.getJSONArray("list").getJSONObject(indexList.get(4)).getJSONArray("weather").getJSONObject(0).getString("description");
            minTempFifthDay = obj.getJSONArray("list").getJSONObject(indexList.get(4)).getJSONObject("main").getInt("temp_min");
            maxTempFifthDay = obj.getJSONArray("list").getJSONObject(indexList.get(4)).getJSONObject("main").getInt("temp_max");
        }
    }

    public String getTemperature() {
        return temperature;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public String getClouds() {
        return clouds;
    }

    public String getToday() {
        return today;
    }

    public String getTomorrowData() {
        return tomorrowData;
    }

    public String getDayAfterTomorrow() {
        return dayAfterTomorrow;
    }

    public String getFourthDay() {
        return fourthDay;
    }

    public String getFifthData() {
        return fifthData;
    }

    public double getMinTempToday() {
        return minTempToday;
    }

    public double getMaxTempToday() {
        return maxTempToday;
    }

    public double getMinTempTomorrowDay() {
        return minTempTomorrowDay;
    }

    public double getMaxTempTomorrowDay() { return maxTempTomorrowDay; }

    public double getMinTempAfterTomorrow() {
        return minTempAfterTomorrow;
    }

    public void setTemperature(String temperature) { this.temperature = temperature; }

    public void setToday(String today) { this.today = today; }

    public double getMaxTempAfterTomorrow() {
        return maxTempAfterTomorrow;
    }

    public double getMinTempFourthDay() {
        return minTempFourthDay;
    }

    public double getMaxTempFourthDay() {
        return maxTempFourthDay;
    }

    public double getMinTempFifthDay() {
        return minTempFifthDay;
    }

    public double getMaxTempFifthDay() {
        return maxTempFifthDay;
    }

    public String getCloudsToday() {
        return cloudsToday;
    }

    public String getCloudsTomorrow() {
        return cloudsTomorrow;
    }

    public String getCloudsAfterTomorrow() {
        return cloudsAfterTomorrow;
    }

    public String getCloudsFourthDay() {
        return cloudsFourthDay;
    }

    public String getCloudsFifthDay() {
        return cloudsFifthDay;
    }

    public ArrayList<Integer> getTempListTomorrow() { return tempListTomorrow; }

    public ArrayList<Integer> getTempListAfterTomorrow() { return tempListAfterTomorrow; }

    public ArrayList<Integer> getTempListFourDay() { return tempListFourDay; }

    public ArrayList<Integer> getTempListFifthDay() { return tempListFifthDay; }
}