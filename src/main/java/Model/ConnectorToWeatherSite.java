package Model;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ConnectorToWeatherSite {
    String weather = "http://api.openweathermap.org/data/2.5/weather?q=Poltava&units=metric&appid=ceb8e786e2a20dff0a80033639084138";
    String output = getConnection(weather);
    String temperature;
    String minTemp;
    String maxTemp;

    public ConnectorToWeatherSite() {
        System.out.println(output);
        getResponse(output);
    }


    public static String getConnection(String URLAddress) {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(URLAddress);
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
        return content.toString();
    }

    public void getResponse(String output) {
        if (!output.isEmpty()) {
            JSONObject obj = new JSONObject(output);

            System.out.println("Температура: " + obj.getJSONObject("main").getDouble("temp") + "\n" +
                    "max: " + obj.getJSONObject("main").getDouble("temp_max") + "\n" +
                    "min: " + obj.getJSONObject("main").getDouble("temp_min"));

            temperature = obj.getJSONObject("main").getDouble("temp") + " C";
            minTemp = obj.getJSONObject("main").getDouble("temp_max") + " C";
            maxTemp = obj.getJSONObject("main").getDouble("temp_min") + " C";

        }
    }

    public String getTemperature() { return temperature; }
    public String getMinTemp(){ return minTemp; }
    public String getMaxTemp(){return maxTemp; }

}