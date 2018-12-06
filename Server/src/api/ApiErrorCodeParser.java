package api;

import org.json.JSONObject;

import static api.APIPublicValue.*;

public abstract class ApiErrorCodeParser {
    public static String parseToString(int errorCode) {
        switch (errorCode) {
            case ILLEGALE_DATA:
                return "data is not correct.";
            default:
                return "unknown error code";
        }
    }

    public static JSONObject parseToJson(int errorCode){
        // TODO: 12/6/2018 complete this
        return new JSONObject(parseToString(errorCode));
    }
}
