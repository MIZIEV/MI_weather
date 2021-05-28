package Model;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ConnectorToWeatherSite {

    CityName cityName;
    String temperature;
    String minTemp;
    String maxTemp;
    String clouds;

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

    public void getResponse(String output) {
        if (!output.isEmpty()) {

            JSONObject obj = new JSONObject(output);

            temperature = obj.getJSONObject("main").getDouble("temp") + " C";
            minTemp = obj.getJSONObject("main").getDouble("temp_max") + " C ;";
            maxTemp = obj.getJSONObject("main").getDouble("temp_min") + " C";
            clouds = obj.getJSONArray("weather").getJSONObject(0).getString("description");
        }
    }

    public String getTemperature() { return temperature; }

    public String getMinTemp() { return minTemp; }

    public String getMaxTemp() { return maxTemp; }

    public String getClouds() { return clouds; }
}