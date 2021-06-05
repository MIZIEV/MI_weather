package Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ConnectorToWeatherSite {

    private final CityName cityName;

    public ConnectorToWeatherSite(CityName name) {
        this.cityName = name;
    }

    public String getConnection() {
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
        return content.toString();
    }

    public String getConnectionOnFiveDays() {

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
        return content.toString();
    }
}