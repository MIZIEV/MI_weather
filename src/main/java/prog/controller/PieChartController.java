package prog.controller;

import prog.workWIthDB.DBAnalyzer;
import prog.workWIthDB.DBWorker;
import javafx.scene.chart.PieChart;

/**
 * class description:
 * this class is needed to put data from data base to pie chart with putDataToPie(PieChart pieChart) method
 */
public class PieChartController {

    private final DBAnalyzer dbAnalyzer;
    private final DBWorker dbWorker;

    public PieChartController(DBAnalyzer dbAnalyzer, DBWorker worker) {
        this.dbAnalyzer = dbAnalyzer;
        this.dbWorker = worker;
    }

    public void putDataToPie(PieChart pieChart) {
        for (String cityName : dbAnalyzer.transformData(dbWorker.getDataList()).keySet()) {
            PieChart.Data data = new PieChart.Data(cityName,
                    dbAnalyzer.transformData(dbWorker.getDataList()).get(cityName));
            pieChart.getData().add(data);
        }
    }

    public void clearDataFromPie(PieChart pieChart){ pieChart.getData().clear(); }
}