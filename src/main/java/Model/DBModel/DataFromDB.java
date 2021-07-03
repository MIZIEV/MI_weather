package Model.DBModel;

/**
 * class description:
 * this class is needed to describes data from the database
 */
public class DataFromDB {

    private final String cityName;
    private final int temperature;
    private final String date;
    private final double windSpeed;
    private final int windDirection;
    private final int humidity;

    public DataFromDB(String cityName, int temp, String data, double windSpeed, int direction, int humidity) {
        this.cityName = cityName;
        this.temperature = temp;
        this.date = data;
        this.windSpeed = windSpeed;
        this.windDirection = direction;
        this.humidity = humidity;
    }

    public String getCityName() { return cityName; }

    @Override
    public String toString() {
        return "city name - " + cityName + "; temperature - " + temperature + "; date - " + date +
                "; wind speed - " + windSpeed + "; wind direction - " + windDirection +
                "; humidity - " + humidity+"\n";
    }
}