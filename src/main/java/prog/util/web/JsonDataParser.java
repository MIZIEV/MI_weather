package prog.util.web;


import prog.models.Measurements;

public interface JsonDataParser {
    Measurements parseJson(String json) throws Exception;
}
