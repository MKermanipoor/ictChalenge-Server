package server;

import Utility.PublicValues;
import api.fileOperationPackage.DownloadAIP;
import api.fileOperationPackage.UploadAPI;
import appModule.User;
import database.dataManager.DataManager;
import database.dataManager.DataManagerIMP;
import elonen.NanoHTTPD;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;


public class MainServer extends NanoHTTPD {

    MainServer() throws IOException {
        super(PublicValues.SERVER_PORT);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("Running!");
        DataManager dataManager= DataManagerIMP.GetDataManger();
        dataManager.getUserDao().add(new User(-1,"test","1234","5465",new Date(),new Date()));
        System.out.println("database end");
    }

    @Override
    public Response serve(IHTTPSession session) {
        String result = "";
        JSONObject data = new JSONObject(session.getHeaders());
        switch (session.getMethod()){
            case LOGIN:
                api.authenticationPackage.LoginAPI loginAPI = new api.authenticationPackage.LoginAPI();
                return newFixedLengthResponse(loginAPI.call(data).toString());

            case GET:
                DownloadAIP uploadAPI = new DownloadAIP();
                JSONObject uploadApiResult = uploadAPI.call(data);
                String filePathUpload  = uploadApiResult.optString(UploadAPI.FILE_URL_KEY);
                String fileMimeType = uploadApiResult.optString(UploadAPI.MIME_TYPE_KEY);
                FileInputStream fileInputStream = null;

                try {
                    fileInputStream = new FileInputStream(filePathUpload);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                if (fileInputStream == null)
                    return newFixedLengthResponse("read file error");

                return new NanoHTTPD.Response(Response.Status.OK, fileMimeType, fileInputStream, -1);

            case PUT:


            default:
                return newFixedLengthResponse("");
        }
    }


    public static void main(String[] args){
        try {
            new MainServer();
        } catch (IOException e) {
            System.err.println("Cant start server!");
        }
    }
}
