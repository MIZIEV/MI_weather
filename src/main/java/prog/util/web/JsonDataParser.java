package prog.util.web;


import prog.models.WeatherData;

import java.util.List;

public interface JsonDataParser {
    List<WeatherData> parseJson(String json) throws Exception;
}
