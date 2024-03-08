package prog.web.jsonworker;

import java.util.List;
import java.util.Map;

public abstract class JSONDataWorker {

    protected JSONDataParser parser;
    protected List<Number> dataList;
    protected Map<String, Number> dataMap;

    public JSONDataWorker() {
    }

    public void getResponse(String jsonData) {
        parser.parserFromJSON(jsonData);
    }
}