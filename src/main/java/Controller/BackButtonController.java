package Controller;

import Model.JSONDataParser;

public class BackButtonController {

    private final JSONDataParser parser;

    public BackButtonController(JSONDataParser jsonParser) {
        this.parser = jsonParser;
    }

    public void deletingTwoValues(){
        parser.setTemperature(null);
        parser.setToday(null);
    }
}