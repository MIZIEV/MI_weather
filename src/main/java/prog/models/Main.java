package prog.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Main {

    @Column(name = "temp")
    private Double temp;

    @Column(name = "feels_like")
    private Double feelsLike;

    public Main(){}

    public Main(Double temp, Double feelsLike) {
        this.temp = temp;
        this.feelsLike = feelsLike;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }
}