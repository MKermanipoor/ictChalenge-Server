package api.fileOperationPackage;

import api.ApiErrorCodeParser;
import api.fileOperationPackage.module.DownlandModule;
import org.json.JSONObject;

import static api.APIPublicValue.*;

public class DownloadAIP extends FileOperationPackage<DownlandModule> {
    public final static String FILE_URL = "file_url";
    public static final String MIME_TYPE_KEY = "mime_type";

    private DownlandModule downlandModule = null;
    @Override
    public JSONObject call(JSONObject data) {
        int checkPermeationCode = checkPermeation(data);
        if (checkPermeationCode != OK_RESPONSE)
            return ApiErrorCodeParser.parseToJson(checkPermeationCode);

        return workApi(downlandModule);
    }

    @Override
    public JSONObject workApi(DownlandModule module) {
        // TODO: 12/6/2018 get file path and start download
        return null;
    }

    @Override
    public int checkPermeation(DownlandModule data) {
        // TODO: 12/6/2018 check file existed?
        // TODO: 12/6/2018 check file parent
        return OK_RESPONSE;
    }

    @Override
    public int checkPermeation(JSONObject data) {
        int superCheckEx = super.checkPermeation(data);

        if (superCheckEx != OK_RESPONSE) {
            return superCheckEx;
        }

        downlandModule = DownlandModule.create(data);
        return checkPermeation(downlandModule);
    }
}
