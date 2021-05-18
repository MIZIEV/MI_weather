import View.FirstWindow;
import javafx.application.Application;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Main extends Application {
    public static void main(String[] args) {


        launch(args);
        /*String weather = "http://api.openweathermap.org/data/2.5/weather?q=Poltava&units=metric&appid=ceb8e786e2a20dff0a80033639084138";
        String output = getConnection(weather);
        System.out.println(output);
        System.out.println("Ready response");
        getResponse(output);*/
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FirstWindow firstWindow = new FirstWindow();
    }

    public static String getConnection(String urlAddress) {
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(urlAddress);
            URLConnection URLCon = url.openConnection();
            System.out.println(URLCon);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(URLCon.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();

        } catch (Exception e) {
            System.out.println("connect error !!!");
        }
        return content.toString();
    }

    public static void getResponse(String output) {

        if (!output.isEmpty()) {
            JSONObject obj = new JSONObject(output);
            System.out.println("Температура: " + obj.getJSONObject("main").getDouble("temp") + "\n" +
                    "max: " + obj.getJSONObject("main").getDouble("temp_max") + "\n" +
                    "min: " + obj.getJSONObject("main").getDouble("temp_min"));
        }
    }
}