package prog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "forecast", schema = "weather_db")
public class Forecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "weather_data_id", nullable = false)
    private WeatherData weatherData;

    @Column(name = "dt")
    private Long dt;

    @Column(name = "main")
    private Main main;

    @OneToMany(mappedBy = "forecast", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Weather> weatherList;

    public Forecast(){}

    public Forecast(Long id, WeatherData weatherData, Long dt, Main main, List<Weather> weatherList) {
        this.id = id;
        this.weatherData = weatherData;
        this.dt = dt;
        this.main = main;
        this.weatherList = weatherList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }
}