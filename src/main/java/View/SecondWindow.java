package View;

import Controller.BackButtonController;
import Model.CityName;
import Model.ConnectorToWeatherSite;
import Model.JSONDataParser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class SecondWindow {

    private final Stage secondWindow = new Stage();
    private final CityName cityName;
    private final FirstWindow firstWindow;
    private final JSONDataParser parser;

    private final static String WINDOW_TITLE = "MI weather";
    private final static short WINDOW_WIDTH = 1024;
    private final static short WINDOW_HEIGHT = 768;
    private final static short WINDOW_MIN_WIDTH = 880;
    private final static short WINDOW_MIN_HEIGHT = 550;

    public SecondWindow(CityName name,  JSONDataParser jsonParser, FirstWindow firstW) {
        this.cityName = name;
        this.parser = jsonParser;
        this.firstWindow = firstW;
    }

    public void startWin() {
        Insets margin = new Insets(20);
        DecimalFormat format = new DecimalFormat("#");

        String stylesheet = getClass().getResource("/SecondWindow.css").toExternalForm();

        BorderPane generalPane = new BorderPane();
        BorderPane bottomPane = new BorderPane();
        BorderPane topPane = new BorderPane();
        VBox centerVbox = new VBox(20);
        Pane topLeftInsert = new Pane();
        Pane topRightInsert = new Pane();
        topLeftInsert.setPrefWidth(350);
        topRightInsert.setPrefWidth(350);

        VBox topVBox = new VBox();
        HBox centerHBox = new HBox(20);
        HBox topCentralMiddleHBox = new HBox(10);

        VBox vbox1 = new VBox(10);
        vbox1.setPrefSize(150, 150);
        vbox1.setAlignment(Pos.CENTER);
        HBox hBox1 = new HBox(10);
        hBox1.setAlignment(Pos.CENTER);
        Label data1 = new Label(parser.getToday());
        data1.getStyleClass().add("Label-smale");
        Label minTemp1 = new Label("min " + format.format(parser.getMinTempToday() - 273.15) + " C;");
        minTemp1.getStyleClass().add("Label-smale");
        Label maxTemp1 = new Label("max " + format.format(parser.getMaxTempToday() - 273.15) + " C");
        maxTemp1.getStyleClass().add("Label-smale");
        Label clouds1 = new Label(parser.getCloudsToday());
        clouds1.getStyleClass().add("Label-smale");
        hBox1.getChildren().addAll(minTemp1, maxTemp1);
        vbox1.getChildren().addAll(data1, hBox1, clouds1);

        VBox vbox2 = new VBox(10);
        vbox2.setPrefSize(150, 150);
        vbox2.setAlignment(Pos.CENTER);
        HBox hBox2 = new HBox(10);
        hBox2.setAlignment(Pos.CENTER);
        Label data2 = new Label(parser.getTomorrowData());
        Label minTemp2 = new Label("min " + format.format(parser.getMinTempTomorrowDay() - 273.15) + " C;");
        Label maxTemp2 = new Label("max " + format.format(parser.getMaxTempTomorrowDay() - 273.15) + " C");
        Label clouds2 = new Label(parser.getCloudsTomorrow());
        hBox2.getChildren().addAll(minTemp2, maxTemp2);
        vbox2.getChildren().addAll(data2, hBox2, clouds2);

        VBox vbox3 = new VBox(10);
        vbox3.setAlignment(Pos.CENTER);
        vbox3.setPrefSize(150, 150);
        HBox hBox3 = new HBox(10);
        hBox3.setAlignment(Pos.CENTER);
        Label data3 = new Label(parser.getDayAfterTomorrow());
        Label minTemp3 = new Label("min " + format.format(parser.getMinTempAfterTomorrow() - 273.15) + " C;");
        Label maxTemp3 = new Label("max " + format.format(parser.getMaxTempAfterTomorrow() - 273.15) + " C");
        Label clouds3 = new Label(parser.getCloudsAfterTomorrow());
        hBox3.getChildren().addAll(minTemp3, maxTemp3);
        vbox3.getChildren().addAll(data3, hBox3, clouds3);

        VBox vbox4 = new VBox(10);
        vbox4.setAlignment(Pos.CENTER);
        vbox4.setPrefSize(150, 150);
        HBox hBox4 = new HBox(10);
        hBox4.setAlignment(Pos.CENTER);
        Label data4 = new Label(parser.getFourthDay());
        Label minTemp4 = new Label("min " + format.format(parser.getMinTempFourthDay() - 273.15) + " C;");
        Label maxTemp4 = new Label("max " + format.format(parser.getMaxTempFourthDay() - 273.15) + " C");
        Label clouds4 = new Label(parser.getCloudsFourthDay());
        hBox4.getChildren().addAll(minTemp4, maxTemp4);
        vbox4.getChildren().addAll(data4, hBox4, clouds4);

        VBox vbox5 = new VBox(10);
        vbox5.setAlignment(Pos.CENTER);
        vbox5.setPrefSize(150, 150);
        HBox hBox5 = new HBox(10);
        hBox5.setAlignment(Pos.CENTER);
        Label data5 = new Label(parser.getFifthData());
        Label minTemp5 = new Label("min " + format.format(parser.getMinTempFifthDay() - 273.15) + " C;");
        Label maxTemp5 = new Label("max " + format.format(parser.getMaxTempFifthDay() - 273.15) + " C");
        Label clouds5 = new Label(parser.getCloudsFifthDay());
        hBox5.getChildren().addAll(minTemp5, maxTemp5);
        vbox5.getChildren().addAll(data5, hBox5, clouds5);

        vbox1.getStyleClass().add("VBox");
        vbox2.getStyleClass().add("VBox");
        vbox3.getStyleClass().add("VBox");
        vbox4.getStyleClass().add("VBox");
        vbox5.getStyleClass().add("VBox");

        centerHBox.setAlignment(Pos.CENTER);
        generalPane.getStyleClass().add("pane");
        topVBox.getStyleClass().add("VBox");
        String cityN = cityName.getCityName();

        Label cityName = new Label(cityN);
        topVBox.setAlignment(Pos.TOP_CENTER);

        Label temperature = new Label("Temperature");
        Label min = new Label("min:");
        Label max = new Label("max:");
        Label tempAnswer = new Label(parser.getTemperature());
        Label minAnswer = new Label(parser.getMinTemp());
        Label maxAnswer = new Label(parser.getMaxTemp());
        Label clouds = new Label(parser.getClouds());

        topCentralMiddleHBox.setAlignment(Pos.TOP_CENTER);
        topCentralMiddleHBox.getChildren().addAll(min, minAnswer, max, maxAnswer);
        topVBox.getChildren().addAll(cityName, temperature, tempAnswer, topCentralMiddleHBox, clouds);

        ButtonsPattern backButton = new ButtonsPattern(150, 50, "Back");
        backButton.getStyleClass().add("Button");

        bottomPane.setPrefSize(400, 150);

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                secondWindow.close();
                BackButtonController controller = new BackButtonController(parser);
                controller.deletingTwoValues();
                firstWindow.startWin();
            }
        });

        topPane.setLeft(topLeftInsert);
        topPane.setRight(topRightInsert);
        topPane.setCenter(topVBox);
        BorderPane.setMargin(topVBox, margin);
        generalPane.setTop(topPane);

        centerHBox.getChildren().addAll(vbox1, vbox2, vbox3, vbox4, vbox5);
        centerVbox.getChildren().addAll(centerHBox);

        bottomPane.setCenter(backButton);
        generalPane.setCenter(centerVbox);
        BorderPane.setMargin(centerVbox, margin);
        generalPane.setBottom(bottomPane);

        Scene secondWindowScene = new Scene(generalPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        secondWindowScene.getStylesheets().add(stylesheet);

        secondWindow.getIcons().add(new Image("weather_icon.png"));
        secondWindow.setMinWidth(WINDOW_MIN_WIDTH);
        secondWindow.setMinHeight(WINDOW_MIN_HEIGHT);
        secondWindow.setScene(secondWindowScene);
        secondWindow.setTitle(WINDOW_TITLE);
        secondWindow.show();
    }
}