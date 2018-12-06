package api;

import org.json.JSONObject;

import static api.APIPublicValue.*;

public abstract class API<T extends APIModule> extends Thread implements APIInterface<T> {

    @Override
    public int checkPermeation(JSONObject data) {
        // TODO: 12/6/2018 is something to check for all api ?
        return OK_RESPONSE;
    }


}
