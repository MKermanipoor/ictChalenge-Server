package api.operationPackage;

import api.API;
import api.APIModule;
import org.json.JSONObject;

import static api.APIPublicValue.ILLEGAL_DATA;
import static api.APIPublicValue.OK_RESPONSE;

public abstract class OperationPackage<T extends APIModule> extends API<T> {
    public static final String TOKEN_KEY = "token";
    public static final String USER_KEY = "user";
    public static final String FILE_UEL_KEY = "file_url";

    protected long userID;

    @Override
    public int checkPermeation(JSONObject data) {
        int superExitCode = super.checkPermeation(data);
        if (superExitCode != OK_RESPONSE){
            return superExitCode;
        }

        if (!data.has(TOKEN_KEY) || !data.has(USER_KEY))
            return ILLEGAL_DATA;
        // TODO: 12/6/2018 check token and user name assignment
        // TODO: 12/6/2018 after check set user id
        return OK_RESPONSE;
    }
}
