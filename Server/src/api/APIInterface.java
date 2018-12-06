package api;

import org.json.JSONObject;
import server.Sender;

/**
 *
 */
public interface APIInterface<T extends APIModule> extends CheckPermeationFromJson, CheckPermeation<T>{
    /**
     * call api with JSON input
     * @param data
     *
     */
    JSONObject call(JSONObject data);

    JSONObject workApi(T module);
}
