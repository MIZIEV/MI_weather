package prog.view;

import prog.controller.PieChartController;
import prog.workWIthDB.DBAnalyzer;
import prog.util.database.DBWorker;
import prog.view.patterns.controls.ButtonsPattern;
import animatefx.animation.FadeIn;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DBInfo {

    private final DBWorker dbWorker;

    private final static short PANE_WIDTH = 1200;
    private final static short PANE_HEIGHT = 400;

    private final static byte CENTRAL_HBOX_SPACING = 20;
    private final static byte DB_SIZE_VBOX_SPACING = 10;

    private final static short BUTTON_WIDTH = 120;
    private final static short BUTTON_HEIGHT = 40;

    private final static String SC_INFO_PANE = "Border-Pane";
    private final static String SC_LABEL = "Label";
    private final static String SC_CLEAR_BUTTON = "clear-button";

    public DBInfo(DBWorker worker) {
        this.dbWorker = worker;
    }

    public BorderPane showInfo(String theme) {
        String stylesheets = getClass().getResource(theme + "DBInfoStyle.css").toExternalForm();
        String pieChartStyle = getClass().getResource(theme + "PieChartStyle.css").toExternalForm();
        DBAnalyzer dbAnalyzer = new DBAnalyzer();
        PieChartController controller = new PieChartController(dbAnalyzer, dbWorker);

        ImageView DBSizeImage = new ImageView(theme + "DB_size_icon.jpg");
        BorderPane infoPane = new BorderPane();
        HBox centralHBox = new HBox(CENTRAL_HBOX_SPACING);
        VBox DBSizeBox = new VBox(DB_SIZE_VBOX_SPACING);
        DBSizeBox.setAlignment(Pos.CENTER);
        centralHBox.setAlignment(Pos.CENTER);
        PieChart pieChart = new PieChart();
        pieChart.getStylesheets().add(pieChartStyle);
        pieChart.setLegendSide(Side.LEFT);
        controller.putDataToPie(pieChart);

        Label DBSize = new Label("Data base size - " + dbWorker.getDataList().size());
        DBSize.getStyleClass().add(SC_LABEL);
        ButtonsPattern clearDBButton = new ButtonsPattern(BUTTON_WIDTH, BUTTON_HEIGHT, "Clear DB", SC_CLEAR_BUTTON);
        DBSizeBox.getChildren().addAll(DBSize, DBSizeImage, clearDBButton);
        clearDBButton.setOnAction(event -> {
            dbWorker.clearData();
            DBSize.setText("Data base size - " + 0);
            controller.clearDataFromPie(pieChart);
        });

        centralHBox.getChildren().addAll(pieChart, DBSizeBox);
        infoPane.setCenter(centralHBox);
        infoPane.getStyleClass().add(SC_INFO_PANE);
        new FadeIn(infoPane).play();
        infoPane.setPrefSize(PANE_WIDTH, PANE_HEIGHT);
        infoPane.getStylesheets().add(stylesheets);
        return infoPane;
    }
}