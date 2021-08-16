package prog.model.database;

/**
 * class description:
 * this class is needed to describes data for the database
 */
public class DataForDB {

    private final String cityName;
    private final String date;
    private final String time;
    private final double windSpeed;
    private final int windDirection;
    private final int humidity;
    private final int temperature;

    public DataForDB(String cityName, int temp, String data, double windSpeed, int direction, int humidity) {
        this.cityName = cityName;
        this.temperature = temp;
        this.windSpeed = windSpeed;
        this.windDirection = direction;
        this.humidity = humidity;

        String bufferString[] = data.split("\\s");
        date = bufferString[0];
        time = bufferString[1];
    }

    @Override
    public String toString() {
        return "city name - " + cityName + "; date - " + date + "; time -" + time + "; temperature - " + temperature + "\n";
    }

    @Override
    public int hashCode() {
        int result = temperature + windDirection + humidity + (int) windSpeed;

        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);

        return result;
    }

    public String getCityName() {
        return cityName;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getTemperature() {
        return temperature;
    }

    public double getWindSpeed() { return windSpeed; }

    public int getWindDirection() { return windDirection; }

    public int getHumidity() { return humidity; }
}