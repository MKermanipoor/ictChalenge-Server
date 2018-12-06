package api.fileOperationPackage.module;

import api.APIModule;
import api.fileOperationPackage.UploadAPI;
import org.json.JSONObject;

public class UploadModule extends APIModule {
    private final String filePath;
    private final String userToken;
    private final long userID;

    private UploadModule(String filePath, String userToken, long userID) {
        this.filePath = filePath;
        this.userToken = userToken;
        this.userID = userID;
    }

    public static UploadModule create(JSONObject data, long userID){
        return new UploadModule(data.optString(UploadAPI.FILE_URL_KEY),
                data.optString(UploadAPI.TOKEN_KEY),
                userID);
    }
}
