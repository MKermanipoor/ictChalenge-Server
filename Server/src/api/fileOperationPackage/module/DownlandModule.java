package api.fileOperationPackage.module;

import api.APIModule;
import api.fileOperationPackage.DownloadAIP;
import org.json.JSONObject;

public class DownlandModule extends APIModule {
    private final String fileurl;
    private final String userToken;

    private DownlandModule(String filePath, String userToken){
        this.fileurl = filePath;
        this.userToken = userToken;
    }

    public static DownlandModule create(JSONObject data) {
        return new DownlandModule(data.optString(DownloadAIP.FILE_URL),
                data.optString( DownloadAIP.USER_KEY));
    }
}
