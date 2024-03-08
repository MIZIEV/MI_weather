package prog.web.jsonworker;

import org.json.JSONObject;

public interface JSONDataParser {

    JSONObject parserFromJSON(String data);
}
