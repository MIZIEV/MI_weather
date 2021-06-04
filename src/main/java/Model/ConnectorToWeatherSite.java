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
    private double minTempToday;


    private double maxTempToday;
    private String tomorrowDay;
    private double minTempTomorrowDay;
    private double maxTempTomorrowDay;
    private String dayAfterTomorrow;
    private double minTempAfterTomorrow;
    private double maxTempAfterTomorrow;
    private String fourthDay;
    private double minTempFourthDay;
    private double maxTempFourthDay;
    private String fifthDay;
    private double minTempFifthDay;
    private double maxTempFifthDay;

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
            minTempToday = obj.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp_min");
            maxTempToday = obj.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp_max");

            tomorrowDay = obj.getJSONArray("list").getJSONObject(3).getString("dt_txt");
            minTempTomorrowDay = obj.getJSONArray("list").getJSONObject(3).getJSONObject("main").getDouble("temp_min");
            maxTempTomorrowDay = obj.getJSONArray("list").getJSONObject(3).getJSONObject("main").getDouble("temp_max");

            dayAfterTomorrow = obj.getJSONArray("list").getJSONObject(11).getString("dt_txt");
            minTempAfterTomorrow = obj.getJSONArray("list").getJSONObject(11).getJSONObject("main").getDouble("temp_min");
            maxTempTomorrowDay = obj.getJSONArray("list").getJSONObject(11).getJSONObject("main").getDouble("temp_max");

            fourthDay = obj.getJSONArray("list").getJSONObject(19).getString("dt_txt");
            minTempFourthDay = obj.getJSONArray("list").getJSONObject(19).getJSONObject("main").getDouble("temp_min");
            maxTempFourthDay = obj.getJSONArray("list").getJSONObject(19).getJSONObject("main").getDouble("temp_max");

            fifthDay = obj.getJSONArray("list").getJSONObject(27).getString("dt_txt");
            minTempFifthDay = obj.getJSONArray("list").getJSONObject(27).getJSONObject("main").getDouble("temp_min");
            maxTempFifthDay = obj.getJSONArray("list").getJSONObject(27).getJSONObject("main").getDouble("temp_max");

            System.out.println(today + " -" + minTempToday + " " + maxTempToday);
            System.out.println(tomorrowDay + " -" + minTempTomorrowDay + " " + maxTempTomorrowDay);
            System.out.println(dayAfterTomorrow+ " -" + minTempAfterTomorrow + " " + maxTempAfterTomorrow);
            System.out.println(fourthDay+ " -" + minTempFourthDay + " " + maxTempFourthDay);
            System.out.println(fifthDay+ " -" + minTempFifthDay + " " + maxTempFifthDay);
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
}