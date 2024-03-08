package prog.web;

import prog.models.CityName;
import prog.models.Measurements;

import java.util.ArrayList;
import java.util.TreeMap;

public class JSONParser {
/*
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

    private final CityName cityName;

    private final TreeMap<String, Integer> tempMap = new TreeMap<>();
    private final ArrayList<String> keysForMap = new ArrayList<>();
    private final ArrayList<Double> speedWindList = new ArrayList<>();
    private final ArrayList<Integer> windDirection = new ArrayList<>();
    private final ArrayList<Integer> humidityList = new ArrayList<>();
    private final ArrayList<Measurements> listForDB = new ArrayList<>();

    private final StartEndIndexDayParser todayIndex = new StartEndIndexDayParser();
    private final StartEndIndexDayParser secondDayIndex = new StartEndIndexDayParser();
    private final StartEndIndexDayParser thirdDayIndex = new StartEndIndexDayParser();
    private final StartEndIndexDayParser fourDayIndex = new StartEndIndexDayParser();
    private final StartEndIndexDayParser fifthDayIndex = new StartEndIndexDayParser();

    public JSONParser(CityName cityName) {
        this.cityName = cityName;
    }

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
                    speedWindList.add(obj.getJSONArray("list").getJSONObject(counter).getJSONObject("wind").getDouble("speed"));
                    windDirection.add(obj.getJSONArray("list").getJSONObject(counter).getJSONObject("wind").getInt("deg"));
                    humidityList.add(obj.getJSONArray("list").getJSONObject(counter).getJSONObject("main").getInt("humidity"));
                    todayIndex.setStartDayIndex(counter);
                }
                if (todayDay != bufferVar & tomorrowDay == 0) {
                    tomorrowDay = bufferVar;
                    speedWindList.add(obj.getJSONArray("list").getJSONObject(counter).getJSONObject("wind").getDouble("speed"));
                    windDirection.add(obj.getJSONArray("list").getJSONObject(counter).getJSONObject("wind").getInt("deg"));
                    humidityList.add(obj.getJSONArray("list").getJSONObject(counter).getJSONObject("main").getInt("humidity"));
                    todayIndex.setEndDayIndex(counter);
                    secondDayIndex.setStartDayIndex(counter);
                }
                if (tomorrowDay != bufferVar & todayDay != bufferVar & afterTomorrowDay == 0) {
                    afterTomorrowDay = bufferVar;
                    speedWindList.add(obj.getJSONArray("list").getJSONObject(counter).getJSONObject("wind").getDouble("speed"));
                    windDirection.add(obj.getJSONArray("list").getJSONObject(counter).getJSONObject("wind").getInt("deg"));
                    humidityList.add(obj.getJSONArray("list").getJSONObject(counter).getJSONObject("main").getInt("humidity"));
                    secondDayIndex.setEndDayIndex(counter);
                    thirdDayIndex.setStartDayIndex(counter);
                }
                if (bufferVar != afterTomorrowDay & todayDay != bufferVar & tomorrowDay != bufferVar
                        & afterTomorrowDay != bufferVar & fourDay == 0) {
                    fourDay = bufferVar;
                    speedWindList.add(obj.getJSONArray("list").getJSONObject(counter).getJSONObject("wind").getDouble("speed"));
                    windDirection.add(obj.getJSONArray("list").getJSONObject(counter).getJSONObject("wind").getInt("deg"));
                    humidityList.add(obj.getJSONArray("list").getJSONObject(counter).getJSONObject("main").getInt("humidity"));
                    thirdDayIndex.setEndDayIndex(counter);
                    fourDayIndex.setStartDayIndex(counter);
                }
                if (bufferVar != fourDay & todayDay != bufferVar & tomorrowDay != bufferVar
                        & afterTomorrowDay != bufferVar & fourDay != bufferVar & fifthDay == 0) {
                    fifthDay = bufferVar;
                    speedWindList.add(obj.getJSONArray("list").getJSONObject(counter).getJSONObject("wind").getDouble("speed"));
                    windDirection.add(obj.getJSONArray("list").getJSONObject(counter).getJSONObject("wind").getInt("deg"));
                    humidityList.add(obj.getJSONArray("list").getJSONObject(counter).getJSONObject("main").getInt("humidity"));
                    fourDayIndex.setEndDayIndex(counter);
                    fifthDayIndex.setStartDayIndex(counter);
                }
                if (bufferVar != fourDay & todayDay != bufferVar & tomorrowDay != bufferVar
                        & afterTomorrowDay != bufferVar & fourDay != bufferVar &
                        fifthDay != bufferVar & lastDay == 0) {
                    lastDay = bufferVar;
                    speedWindList.add(obj.getJSONArray("list").getJSONObject(counter).getJSONObject("wind").getDouble("speed"));
                    windDirection.add(obj.getJSONArray("list").getJSONObject(counter).getJSONObject("wind").getInt("deg"));
                    humidityList.add(obj.getJSONArray("list").getJSONObject(counter).getJSONObject("main").getInt("humidity"));
                    fifthDayIndex.setEndDayIndex(counter);
                }

                listForDB.add(new Measurements(cityName.getCityName(),
                        obj.getJSONArray("list").getJSONObject(counter).getJSONObject("main").getInt("temp") - 273,
                        obj.getJSONArray("list").getJSONObject(counter).getString("dt_txt"),
                        obj.getJSONArray("list").getJSONObject(counter).getJSONObject("wind").getDouble("speed"),
                        obj.getJSONArray("list").getJSONObject(counter).getJSONObject("wind").getInt("deg"),
                        obj.getJSONArray("list").getJSONObject(counter).getJSONObject("main").getInt("humidity")));

                // filter data to format:  key -( mount,day,hour ), value
                String dataForMap = obj.getJSONArray("list").getJSONObject(counter).getString("dt_txt");
                String filteredData[] = dataForMap.split("\\-|\\s|\\:");
                //  put filtered data to one treeMap
                tempMap.put(filteredData[1] + " " + filteredData[2] + " " + filteredData[3],
                        obj.getJSONArray("list").getJSONObject(counter).getJSONObject("main").getInt("temp")-273);
                // put the same filtered keys to ArrayList, they will be comparing with Map in another class
                keysForMap.add(filteredData[1] + " " + filteredData[2] + " " + filteredData[3]);
                counter++;

            }
            todayDate = obj.getJSONArray("list").getJSONObject(todayIndex.getStartDayIndex()).getString("dt_txt");
            String todayBuf[] = todayDate.split("\\s");
            todayDate = todayBuf[0];
            weatherToday = obj.getJSONArray("list").getJSONObject(todayIndex.getStartDayIndex()).getJSONArray("weather").getJSONObject(0).getString("description");

            dateSecondDay = obj.getJSONArray("list").getJSONObject(secondDayIndex.getStartDayIndex()).getString("dt_txt");
            String tomorrowBuf[] = dateSecondDay.split("\\s");
            dateSecondDay = tomorrowBuf[0];
            weatherSecondDay = obj.getJSONArray("list").getJSONObject(secondDayIndex.getStartDayIndex()).getJSONArray("weather").getJSONObject(0).getString("description");

            dateThirdDay = obj.getJSONArray("list").getJSONObject(thirdDayIndex.getStartDayIndex()).getString("dt_txt");
            String dayAfterTomorrowBuf[] = dateThirdDay.split("\\s");
            dateThirdDay = dayAfterTomorrowBuf[0];
            weatherThirdDay = obj.getJSONArray("list").getJSONObject(thirdDayIndex.getStartDayIndex()).getJSONArray("weather").getJSONObject(0).getString("description");

            dateFourDay = obj.getJSONArray("list").getJSONObject(fourDayIndex.getStartDayIndex()).getString("dt_txt");
            String fourthDayBuf[] = dateFourDay.split("\\s");
            dateFourDay = fourthDayBuf[0];
            weatherFourthDay = obj.getJSONArray("list").getJSONObject(fourDayIndex.getStartDayIndex()).getJSONArray("weather").getJSONObject(0).getString("description");

            dateFifthDay = obj.getJSONArray("list").getJSONObject(fifthDayIndex.getStartDayIndex()).getString("dt_txt");
            String fifthDataBuf[] = dateFifthDay.split("\\s");
            dateFifthDay = fifthDataBuf[0];
            weatherFifthDay = obj.getJSONArray("list").getJSONObject(fifthDayIndex.getStartDayIndex()).getJSONArray("weather").getJSONObject(0).getString("description");
        }
    }

    public String getTempNow() {
        return tempNow;
    }

    public String getWeatherNow() {
        return weatherNow;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public String getWeatherToday() {
        return weatherToday;
    }

    public String getDateSecondDay() {
        return dateSecondDay;
    }

    public String getWeatherSecondDay() {
        return weatherSecondDay;
    }

    public String getDateThirdDay() {
        return dateThirdDay;
    }

    public String getWeatherThirdDay() {
        return weatherThirdDay;
    }

    public String getDateFourDay() {
        return dateFourDay;
    }

    public String getWeatherFourthDay() {
        return weatherFourthDay;
    }

    public String getDateFifthDay() {
        return dateFifthDay;
    }

    public String getWeatherFifthDay() {
        return weatherFifthDay;
    }

    public TreeMap<String, Integer> getTempMap() {
        return tempMap;
    }

    public ArrayList<String> getKeysForMap() {
        return keysForMap;
    }

    public void setTempNow(String tempNow) {
        this.tempNow = tempNow;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }

    public ArrayList<Double> getSpeedWindList() {
        return speedWindList;
    }

    public ArrayList<Integer> getWindDirection() {
        return windDirection;
    }

    public ArrayList<Integer> getHumidityList() {
        return humidityList;
    }

    public StartEndIndexDayParser getTodayIndex() { return todayIndex; }

    public StartEndIndexDayParser getThirdDayIndex() {
        return thirdDayIndex;
    }

    public StartEndIndexDayParser getFourDayIndex() {
        return fourDayIndex;
    }

    public StartEndIndexDayParser getFifthDayIndex() {
        return fifthDayIndex;
    }

    public StartEndIndexDayParser getSecondDayIndex() {
        return secondDayIndex;
    }

    public ArrayList<Measurements> getListForDB() {
        return listForDB;
    }*/
}