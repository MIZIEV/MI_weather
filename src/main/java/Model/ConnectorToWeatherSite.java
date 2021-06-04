package Model;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ConnectorToWeatherSite {

    private CityName cityName;
    private String temperature;
    private String minTemp;
    private String maxTemp;
    private String clouds;

    private String today;
    private String cloudsToday;
    private int minTempToday;
    private int maxTempToday;

    private String tomorrowDay;
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

    private String fifthDay;
    private String cloudsFifthDay;
    private int minTempFifthDay;
    private int maxTempFifthDay;

    public ConnectorToWeatherSite(CityName name) {
        this.cityName = name;
    }

    public void getConnection() {
        String weather = "http://api.openweathermap.org/data/2.5/weather?q=" + cityName.getCityName() + "&units=metric&appid=ceb8e786e2a20dff0a80033639084138";

        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(weather);
            URLConnection openCon = url.openConnection();
            System.out.println(openCon);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openCon.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(content);
        this.getResponse(content.toString());
    }

    public void getConnectionOnFiveDays() {
        String APICall = "http://api.openweathermap.org/data/2.5/forecast?q=" + cityName.getCityName() + "&appid=ceb8e786e2a20dff0a80033639084138";
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(APICall);
            URLConnection openCon = url.openConnection();
            System.out.println(openCon);
            BufferedReader bufferedReade = new BufferedReader(new InputStreamReader(openCon.getInputStream()));
            String line;

            while ((line = bufferedReade.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReade.close();
            System.out.println(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.getResponseOnFiveDays(content.toString());
    }

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
            double temperature = obj.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp");

            today = obj.getJSONArray("list").getJSONObject(0).getString("dt_txt");
            cloudsToday = obj.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("description");
            minTempToday = obj.getJSONArray("list").getJSONObject(0).getJSONObject("main").getInt("temp_min");
            maxTempToday = obj.getJSONArray("list").getJSONObject(0).getJSONObject("main").getInt("temp_max");

            tomorrowDay = obj.getJSONArray("list").getJSONObject(3).getString("dt_txt");
            cloudsTomorrow = obj.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getString("description");
            minTempTomorrowDay = obj.getJSONArray("list").getJSONObject(3).getJSONObject("main").getInt("temp_min");
            maxTempTomorrowDay = obj.getJSONArray("list").getJSONObject(3).getJSONObject("main").getInt("temp_max");

            dayAfterTomorrow = obj.getJSONArray("list").getJSONObject(11).getString("dt_txt");
            cloudsAfterTomorrow = obj.getJSONArray("list").getJSONObject(11).getJSONArray("weather").getJSONObject(0).getString("description");
            minTempAfterTomorrow = obj.getJSONArray("list").getJSONObject(11).getJSONObject("main").getInt("temp_min");
            maxTempAfterTomorrow = obj.getJSONArray("list").getJSONObject(11).getJSONObject("main").getInt("temp_max");

            fourthDay = obj.getJSONArray("list").getJSONObject(19).getString("dt_txt");
            cloudsFourthDay = obj.getJSONArray("list").getJSONObject(19).getJSONArray("weather").getJSONObject(0).getString("description");
            minTempFourthDay = obj.getJSONArray("list").getJSONObject(19).getJSONObject("main").getInt("temp_min");
            maxTempFourthDay = obj.getJSONArray("list").getJSONObject(19).getJSONObject("main").getInt("temp_max");

            fifthDay = obj.getJSONArray("list").getJSONObject(27).getString("dt_txt");
            cloudsFifthDay = obj.getJSONArray("list").getJSONObject(27).getJSONArray("weather").getJSONObject(0).getString("description");
            minTempFifthDay = obj.getJSONArray("list").getJSONObject(27).getJSONObject("main").getInt("temp_min");
            maxTempFifthDay = obj.getJSONArray("list").getJSONObject(27).getJSONObject("main").getInt("temp_max");

            System.out.println(today + " -" + minTempToday + " " + maxTempToday);
            System.out.println(tomorrowDay + " -" + minTempTomorrowDay + " " + maxTempTomorrowDay);
            System.out.println(dayAfterTomorrow + " -" + minTempAfterTomorrow + " " + maxTempAfterTomorrow);
            System.out.println(fourthDay + " -" + minTempFourthDay + " " + maxTempFourthDay);
            System.out.println(fifthDay + " -" + minTempFifthDay + " " + maxTempFifthDay);
            System.out.println(temperature);
        }
    }

    @Override
    public String toString() {
        return super.toString();
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

    public String getTomorrowDay() {
        return tomorrowDay;
    }

    public String getDayAfterTomorrow() {
        return dayAfterTomorrow;
    }

    public String getFourthDay() {
        return fourthDay;
    }

    public String getFifthDay() {
        return fifthDay;
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

    public double getMaxTempTomorrowDay() {
        return maxTempTomorrowDay;
    }

    public double getMinTempAfterTomorrow() {
        return minTempAfterTomorrow;
    }

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
}