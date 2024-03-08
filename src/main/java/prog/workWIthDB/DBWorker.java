package prog.workWIthDB;

import prog.models.Measurements;
import prog.models.MeasurementsFromDB;
import prog.web.JSONParser;
import prog.view.ErrorsWindow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBWorker {
    private final JSONParser parser;
    private final DBConnector dbConnector = new DBConnector();
    private final static String INSERT = "INSERT IGNORE INTO measurements values (?,?,?,?,?,?,?,?)";
    private final static String SELECT = "SELECT * FROM weather_data.measurements";
    private final static String DELETE = "DELETE FROM measurements";
    private final static int ID_COLLUM = 1;
    private final static int CITY_NAME_COLLUM = 2;
    private final static int DATE_COLLUM = 3;
    private final static int TIME_COLLUM = 4;
    private final static int TEMPERATURE_COLLUM = 5;
    private final static int WIND_SPEED_COLUMN = 6;
    private final static int WIND_DIRECTION_COLUMN = 7;
    private final static int HUMIDITY_COLUMN = 8;
    private final ArrayList<MeasurementsFromDB> dataList = new ArrayList<>();
    public DBWorker(JSONParser parser) {
        this.parser = parser;
    }

    public void setDataToDB() {

        dbConnector.startConnection();

        try (PreparedStatement preparedStatement = dbConnector.getConnection().prepareStatement(INSERT)) {

            for (Measurements element : parser.getListForDB()) {
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
            ErrorsWindow errorsWindow = new ErrorsWindow();
            errorsWindow.launchErrorWin("Error with setting  data to DB");
        }
    }

    public void getDataFromDB() {
        try (Statement statement = dbConnector.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT)) {

            MeasurementsFromDB measurementsFromDB;
            while (resultSet.next()) {
                measurementsFromDB = new MeasurementsFromDB(resultSet.getString(CITY_NAME_COLLUM),
                        resultSet.getInt(TEMPERATURE_COLLUM),
                        resultSet.getString(DATE_COLLUM),
                        resultSet.getDouble(WIND_SPEED_COLUMN),
                        resultSet.getInt(WIND_DIRECTION_COLUMN),
                        resultSet.getInt(HUMIDITY_COLUMN));

                dataList.add(measurementsFromDB);
            }
        } catch (SQLException throwables) {
            ErrorsWindow errorsWindow = new ErrorsWindow();
            errorsWindow.launchErrorWin("Error with getting data from DB");
        }
    }

    public void clearData() {
        dbConnector.startConnection();
        try (PreparedStatement preparedStatement = dbConnector.getConnection().prepareStatement(DELETE)) {
            preparedStatement.execute();
        } catch (SQLException throwables) {
            ErrorsWindow errorsWindow = new ErrorsWindow();
            errorsWindow.launchErrorWin("Error while clearing the DB");
        }
    }

    public ArrayList<MeasurementsFromDB> getDataList() { return dataList; }
}