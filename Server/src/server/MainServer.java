package server;

import Utility.PublicValues;
import api.API;
import api.authenticationpackage.LoginAPI;
import fi.iki.elonen.NanoHTTPD;
import org.json.JSONObject;

import java.io.IOException;


public class MainServer extends NanoHTTPD {

    MainServer() throws IOException {
        super(PublicValues.SERVER_PORT);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("Running!");
    }

    @Override
    public Response serve(IHTTPSession session) {
        API api;
        switch (session.getMethod()){
            case LOGIN:
                api = new LoginAPI();
                break;
            default:
                return newFixedLengthResponse("");
        }

        JSONObject result = api.call(new JSONObject(session.getHeaders()));
        return newFixedLengthResponse(result.toString());
    }


    public static void main(String[] args){
        try {
            new MainServer();
        } catch (IOException e) {
            System.err.println("Cant start server!");
        }
    }
}
