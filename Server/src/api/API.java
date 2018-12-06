package api;

import org.json.JSONObject;

import static api.APIPublicValue.*;

public abstract class API<T extends APIModule> extends Thread implements APIInterface<T> {

    @Override
    public int checkPermeation(JSONObject data) {
        return OK_RESPONSE;
    }


}
