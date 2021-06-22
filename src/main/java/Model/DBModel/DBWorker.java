package Model.DBModel;

import Model.CityName;
import Model.JSONDataParser;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBWorker {

    private DBConnector dbConnector = new DBConnector();
    private PreparedStatement preparedStatement;
    private final static int ID_COLLUM=1;
    private final static int CITY_NAME_COLLUM = 2;
    private final static int DATE_COLLUM = 3;
    private final static int TIME_COLLUM = 4;
    private final static int TEMPERATURE_COLLUM = 5;
    private final static String INSERT = "INSERT IGNORE INTO temp_table values (?,?,?,?,?)";
    private final JSONDataParser parser;

    public DBWorker(JSONDataParser parser) {
        this.parser = parser;
    }

    public void setDataToDB() {
        dbConnector.startConnection();
        try {
            preparedStatement = dbConnector.getConnection().prepareStatement(INSERT);
            for (StringForDB element : parser.getListForDB()) {
                preparedStatement.setInt(ID_COLLUM,element.hashCode());
                preparedStatement.setString(CITY_NAME_COLLUM, element.getCityName());
                preparedStatement.setString(DATE_COLLUM, element.getDate());
                preparedStatement.setString(TIME_COLLUM, element.getTime());
                preparedStatement.setInt(TEMPERATURE_COLLUM, element.getTemperature());
                preparedStatement.execute();
            }
        } catch (SQLException throwables) {
            System.out.println("error in DBWorker INSERT");
        }
        dbConnector.stopConnection();
    }
}