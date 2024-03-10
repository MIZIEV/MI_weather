package prog.util.web;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import prog.models.WeatherData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonDataParserImpl implements JsonDataParser {

    @Override
    public List<WeatherData> parseJson(String json) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(json);

        if (jsonNode.isArray()) {
            List<WeatherData> weatherDataList = new ArrayList<>();
            for (JsonNode node : jsonNode) {
                WeatherData weatherData = mapper.treeToValue(node, WeatherData.class);
                weatherDataList.add(weatherData);
            }
            return weatherDataList;
        } else {
            WeatherData weatherData = mapper.readValue(json, WeatherData.class);
            return Collections.singletonList(weatherData);
        }
    }
}