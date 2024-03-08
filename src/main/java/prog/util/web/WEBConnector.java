package prog.util.web;

import prog.view.ErrorsWindow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * class description:
 * this class is needed to get connection to site "https://openweathermap.org/" with API calls
 */

/*
*         cityName.setCityName(firstWindow.getInputText().getText());
        String oneDayAPICall = "http://api.openweathermap.org/data/2.5/weather?q=" +
                cityName.getCityName() + "&units=metric&appid=ceb8e786e2a20dff0a80033639084138";
        String fiveDayForecast = "http://api.openweathermap.org/data/2.5/forecast?q=" +
                cityName.getCityName() + "&appid=ceb8e786e2a20dff0a80033639084138";
        parser.getResponse(WEBConnector.getAPIResponse(oneDayAPICall));
        parser.getResponseOnFiveDays(WEBConnector.getAPIResponse(fiveDayForecast));*/
public class WEBConnector {

    public String getAPIResponse(String APICall) {

        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(APICall);
            URLConnection openCon = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openCon.getInputStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            ErrorsWindow errorsWindow = new ErrorsWindow();
            errorsWindow.launchErrorWin("Error in WEB connector"+"\n"
            + "with today weather call API");
        }
        return content.toString();
    }
}