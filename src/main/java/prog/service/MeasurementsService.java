package prog.service;

import prog.models.WeatherData;

import java.util.List;

public interface MeasurementsService {

    void saveAllMeasurements(List<WeatherData> weatherDataList);
    List<WeatherData> getAllMeasurements();
}
