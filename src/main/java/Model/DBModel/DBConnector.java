package Model.DBModel;

import java.sql.*;

public class DBConnector {

    private Connection connection;
    private final static String URL = "jdbc:mySql://localhost:3306/temp_data";
    private final static String USERNAME = "mizZzev";
    private final static String PASSWORD = "20011993";

    public void startConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("connection DONE");
        } catch (SQLException throwables) {
            System.out.println("error DB connector");
        }
    }
    public void stopConnection(){
        try {
            connection.close();
            System.out.println("close connection");
        } catch (SQLException throwables) {
            System.out.println("error in stop connection");
        }
    }

    public Connection getConnection(){ return connection; }
}