package api.fileOperationPackage;

import api.ApiErrorCodeParser;
import api.fileOperationPackage.module.UploadModule;
import org.json.JSONObject;

import static api.APIPublicValue.ILLEGAL_DATA;
import static api.APIPublicValue.OK_RESPONSE;

public class UploadAPI extends FileOperationPackage<UploadModule> {
    private UploadModule uploadModule;

    public static final String FILE_URL_KEY = "file_url";

    @Override
    public JSONObject call(JSONObject data) {
        int checkPermeationCode = checkPermeation(data);
        if (checkPermeationCode != OK_RESPONSE)
            return ApiErrorCodeParser.parseToJson(checkPermeationCode);

        return workApi(uploadModule);
    }

    @Override
    public JSONObject workApi(UploadModule module) {
        // TODO: 12/6/2018 if dir is not ecsiste create if first
        // TODO: 12/6/2018 upload file may be need some thing to uplaod file to client
        return new JSONObject();
    }

    @Override
    public int checkPermeation(JSONObject data) {
        int checkPermeationCode = super.checkPermeation(data);
        if (checkPermeationCode != OK_RESPONSE)
            return checkPermeationCode;

        if (!data.has(FILE_URL_KEY))
            return ILLEGAL_DATA;

        uploadModule = UploadModule.create(data, userID);
        return checkPermeation(uploadModule);
    }

    @Override
    public int checkPermeation(UploadModule data) {
        return OK_RESPONSE;
    }
}
