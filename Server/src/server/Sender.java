package server;

import org.json.JSONObject;

public interface Sender {
    void send(JSONObject data);
}
