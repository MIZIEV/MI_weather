package prog.workWIthDB;

import prog.view.ErrorsWindow;

import java.sql.*;

public class DBConnector {

    private Connection connection;
    private final static String URL = "jdbc:mySql://localhost:3306/weather_data";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "qw20011993QW";

    public void startConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            ErrorsWindow errorsWindow = new ErrorsWindow();
            errorsWindow.launchErrorWin("Error with DB connection");
        }
    }

    public void stopConnection() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            System.out.println("error in stop connection");
        }
    }
    public Connection getConnection() { return connection; }
}