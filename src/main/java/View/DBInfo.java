package View;

import Controller.PieChartController;
import Model.DBModel.DBAnalyzer;
import Model.DBModel.DBWorker;
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

    public DBInfo(DBWorker worker) {
        this.dbWorker = worker;
    }

    public BorderPane showInfo(String theme) {
        String stylesheets = getClass().getResource(theme+"DBInfoStyle.css").toExternalForm();
        String pieChartStyle = getClass().getResource(theme+"PieChartStyle.css").toExternalForm();
        DBAnalyzer dbAnalyzer = new DBAnalyzer();
        PieChartController controller = new PieChartController(dbAnalyzer, dbWorker);

        ImageView DBSizeImage = new ImageView(theme+"DB_size_icon.jpg");
        BorderPane infoPane = new BorderPane();
        HBox centralHBox = new HBox(20);
        VBox DBSizeBox = new VBox(10);
        DBSizeBox.setAlignment(Pos.CENTER);
        centralHBox.setAlignment(Pos.CENTER);
        PieChart pieChart = new PieChart();
        pieChart.getStylesheets().add(pieChartStyle);
        pieChart.setLegendSide(Side.LEFT);
        controller.putDataToPie(pieChart);

        Label DBSize = new Label("Data base size - " + dbWorker.getDataList().size());
        DBSize.getStyleClass().add("Label");
        DBSizeBox.getChildren().addAll(DBSize,DBSizeImage);

        centralHBox.getChildren().addAll(pieChart, DBSizeBox);
        infoPane.setCenter(centralHBox);
        infoPane.getStyleClass().add("Border-Pane");
        new FadeIn(infoPane).play();
        infoPane.setPrefSize(1200, 400);
        infoPane.getStylesheets().add(stylesheets);
        return infoPane;
    }
}
