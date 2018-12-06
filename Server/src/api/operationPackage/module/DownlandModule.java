package api.operationPackage.module;

import api.APIModule;
import api.operationPackage.DownloadAIP;
import org.json.JSONObject;

public class DownlandModule extends APIModule {
    private final String fileurl;
    private final String userToken;

    private DownlandModule(String filePath, String userToken){
        this.fileurl = filePath;
        this.userToken = userToken;
    }

    public static DownlandModule create(JSONObject fileUrl) {
        return new DownlandModule(fileUrl.optString(DownloadAIP.FILE_URL),
                fileUrl.optString( DownloadAIP.USER_KEY));
    }
}
