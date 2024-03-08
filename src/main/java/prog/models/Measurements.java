package prog.models;

import javax.persistence.*;

@Entity
@Table(name = "measurements")
public class Measurements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "city_name")
    private  String cityName;
    @Column(name = "date")
    private  String date;
    @Column(name = "time")
    private  String time;
    @Column(name = "wind_speed")
    private  double windSpeed;
    @Column(name = "wind_direction")
    private  int windDirection;
    @Column(name = "humidity")
    private  int humidity;
    @Column(name = "temperature")
    private  int temperature;

    public Measurements(){}

    public Measurements(String cityName, int temp, String data, double windSpeed, int direction, int humidity) {
        this.cityName = cityName;
        this.temperature = temp;
        this.windSpeed = windSpeed;
        this.windDirection = direction;
        this.humidity = humidity;

        String bufferString[] = data.split("\\s");
        date = bufferString[0];
        time = bufferString[1];
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

    @Override
    public int hashCode() {
        int result = temperature + windDirection + humidity + (int) windSpeed;

        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return "city name - " + cityName + "; date - " + date + "; time -" + time + "; temperature - " + temperature + "\n";
    }
}