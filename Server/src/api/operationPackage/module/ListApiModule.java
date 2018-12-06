package api.operationPackage.module;

import api.APIModule;
import api.operationPackage.ListAPI;
import org.json.JSONObject;

public class ListApiModule extends APIModule {
    private final String fileUrl;
    private final String userToken;
    private final long userID;

    private ListApiModule(String fileUrl, String userToken, long userID){
        this.fileUrl = fileUrl;
        this.userToken = userToken;
        this.userID = userID;
    }

    public static ListApiModule create(JSONObject data, long userID){
        return new ListApiModule(data.optString(ListAPI.FILE_UEL_KEY),
                data.optString(ListAPI.TOKEN_KEY),
                userID);
    }
}
