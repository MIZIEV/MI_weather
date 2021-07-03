package Model.DBModel;

import Model.JSONDataParser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * class description:
 * this class is needed to putting and getting data from database
 */
public class DBWorker {

    private final JSONDataParser parser;
    private final DBConnector dbConnector = new DBConnector();
    private final static String INSERT = "INSERT IGNORE INTO temp_table values (?,?,?,?,?,?,?,?)";
    private final static String SELECT = "SELECT * FROM temp_data.temp_table";

    private final static int ID_COLLUM = 1;
    private final static int CITY_NAME_COLLUM = 2;
    private final static int DATE_COLLUM = 3;
    private final static int TIME_COLLUM = 4;
    private final static int TEMPERATURE_COLLUM = 5;
    private final static int WIND_SPEED_COLUMN = 6;
    private final static int WIND_DIRECTION_COLUMN = 7;
    private final static int HUMIDITY_COLUMN = 8;

    private final ArrayList<StringFromDB> dataList = new ArrayList<>();

    public DBWorker(JSONDataParser parser) {
        this.parser = parser;
    }

    public void setDataToDB() {

        dbConnector.startConnection();

        try (PreparedStatement preparedStatement = dbConnector.getConnection().prepareStatement(INSERT)) {

            for (DataForDB element : parser.getListForDB()) {
                preparedStatement.setInt(ID_COLLUM, element.hashCode());
                preparedStatement.setString(CITY_NAME_COLLUM, element.getCityName());
                preparedStatement.setString(DATE_COLLUM, element.getDate());
                preparedStatement.setString(TIME_COLLUM, element.getTime());
                preparedStatement.setInt(TEMPERATURE_COLLUM, element.getTemperature());
                preparedStatement.setDouble(WIND_SPEED_COLUMN, element.getWindSpeed());
                preparedStatement.setInt(WIND_DIRECTION_COLUMN, element.getWindDirection());
                preparedStatement.setInt(HUMIDITY_COLUMN, element.getHumidity());
                preparedStatement.execute();
            }
        } catch (SQLException throwables) {
            System.out.println("error in DBWorker INSERT");
        }
    }

    public void getDataFromDB() {
        try (Statement statement = dbConnector.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT)) {

            StringFromDB stringFromDB;
            while (resultSet.next()) {
                stringFromDB = new StringFromDB(resultSet.getString(CITY_NAME_COLLUM),
                        resultSet.getInt(TEMPERATURE_COLLUM),
                        resultSet.getString(DATE_COLLUM),
                        resultSet.getDouble(WIND_SPEED_COLUMN),
                        resultSet.getInt(WIND_DIRECTION_COLUMN),
                        resultSet.getInt(HUMIDITY_COLUMN));

                dataList.add(stringFromDB);
            }
        } catch (SQLException throwables) {
            System.out.println("error getDataFromDB method !!!");
        }
    }
    public ArrayList<StringFromDB> getDataList() { return dataList; }
}