package prog.controller.controllersForDiagram;
/**
 * this controller class set NULL to the two variables
 * this is need too call error window if client come back to start window and will not set any city name
 */

import prog.model.web.JSONDataParser;

public class BackButtonController {

    private final JSONDataParser parser;

    public BackButtonController(JSONDataParser jsonParser) {
        this.parser = jsonParser;
    }

    public void deletingTwoValues() {
        parser.setTempNow(null);
        parser.setTodayDate(null);
    }
}