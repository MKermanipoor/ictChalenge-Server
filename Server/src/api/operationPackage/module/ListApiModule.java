package api.operationPackage.module;

import api.APIModule;
import api.operationPackage.ListAPI;
import org.json.JSONObject;

public class ListApiModule extends APIModule {
    private final String fileUrl;
    private final String userToken;

    private ListApiModule(String fileUrl, String userToken){
        this.fileUrl = fileUrl;
        this.userToken = userToken;
    }

    public static ListApiModule create(JSONObject data){
        return new ListApiModule(data.optString(ListAPI.FILE_UEL_KEY),
                data.optString(ListAPI.TOKEN_KEY));
    }
}
