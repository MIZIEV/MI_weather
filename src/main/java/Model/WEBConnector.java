package Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * class description:
 * this class is needed to get connection to site "https://openweathermap.org/" with API calls
 */
public class WEBConnector {

    private final CityName cityName;

    public WEBConnector(CityName name) {
        this.cityName = name;
    }

    public String getTodayWeatherData() {

        String statisticalWeatherDataAPICall = "http://api.openweathermap.org/data/2.5/weather?q=" +
                cityName.getCityName() + "&units=metric&appid=ceb8e786e2a20dff0a80033639084138";
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(statisticalWeatherDataAPICall);
            URLConnection openCon = url.openConnection();

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

    public String getForecastOnFiveDays() {

        String forecastOnFiveDaysAPICall = "http://api.openweathermap.org/data/2.5/forecast?q=" +
                cityName.getCityName() + "&appid=ceb8e786e2a20dff0a80033639084138";
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(forecastOnFiveDaysAPICall);
            URLConnection openCon = url.openConnection();
            BufferedReader bufferedReade = new BufferedReader(new InputStreamReader(openCon.getInputStream()));

            String line;
            while ((line = bufferedReade.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReade.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}