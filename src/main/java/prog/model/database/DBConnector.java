package prog.model.database;

import prog.view.ErrorsWindow;

import java.sql.*;

/**
 * class description:
 * this class is needed to get connection with database
 */
public class DBConnector {

    private Connection connection;
    private final static String URL = "jdbc:mySql://localhost:3306/temp_data";
    private final static String USERNAME = "mizZzev";
    private final static String PASSWORD = "20011993";

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