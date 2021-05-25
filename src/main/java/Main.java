import Model.CityName;
import View.FirstWindow;
import View.SecondWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    CityName cityName = new CityName();
    SecondWindow secondWindow = new SecondWindow(cityName);

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        FirstWindow firstWindow = new FirstWindow(cityName,secondWindow);

    }
}