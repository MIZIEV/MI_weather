package Model.DBModel;

public class StringForDB {

    private String cityName;
    private String date;
    private String time;
    private int temperature;

    public StringForDB(String cityName, int temp, String data) {
        this.cityName = cityName;
        this.temperature = temp;

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
        int result = temperature;

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
}