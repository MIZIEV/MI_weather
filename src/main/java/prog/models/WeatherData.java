package prog.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "weather_data", schema = "weather_db")
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToMany(mappedBy = "weatherData", cascade = CascadeType.ALL)
    private List<Forecast> forecastList;

    public WeatherData(){}

    public WeatherData(Long id, List<Forecast> forecastList) {
        this.id = id;
        this.forecastList = forecastList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Forecast> getForecastList() {
        return forecastList;
    }

    public void setForecastList(List<Forecast> forecastList) {
        this.forecastList = forecastList;
    }
}