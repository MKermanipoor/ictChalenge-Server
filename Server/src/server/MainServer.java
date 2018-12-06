package server;

import Utility.PublicValues;
import api.API;
import api.authenticationPackage.LoginAPI;
import appModule.User;
import database.dataManager.DataManager;
import database.dataManager.DataManagerIMP;
import elonen.NanoHTTPD;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;


public class MainServer extends NanoHTTPD {

    MainServer() throws IOException {
        super(PublicValues.SERVER_PORT);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("Running!");
        DataManager dataManager= DataManagerIMP.GetDataManger();
        dataManager.getUserDao().add(new User(-1,"test","1234","5465",new Date(),new Date()));

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
