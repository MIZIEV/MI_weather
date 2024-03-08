package prog.web.jsonworker;

import org.json.JSONObject;

public class JSONParserOnToday implements JSONDataParser {
    @Override
    public JSONObject parserFromJSON(String data) {
        JSONObject obj = null;
        if (!data.isEmpty()) {
            obj = new JSONObject(data);
            // tempNow = obj.getJSONObject("main").getInt("temp") + "°C";
            // weatherNow = obj.getJSONArray("weather").getJSONObject(0).getString("description");
        }
        return obj;
    }
}
