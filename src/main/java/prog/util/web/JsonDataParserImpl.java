package prog.util.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import prog.models.Measurements;

public class JsonDataParserImpl implements JsonDataParser {

    @Override
    public Measurements parseJson(String json) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(json, Measurements.class);
    }
}