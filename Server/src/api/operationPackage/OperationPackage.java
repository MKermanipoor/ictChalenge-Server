package api.operationPackage;

import api.API;
import api.APIModule;
import org.json.JSONObject;

import static api.APIPublicValue.ILLEGALE_DATA;
import static api.APIPublicValue.OK_RESPONSE;

public abstract class OperationPackage<T extends APIModule> extends API<T> {
    public static final String TOKEN_KEY = "token";
    public static final String USER_KEY = "user";

    @Override
    public int checkPermeation(JSONObject data) {
        int superExitCode = super.checkPermeation(data);
        if (superExitCode != OK_RESPONSE){
            return superExitCode;
        }

        if (!data.has(TOKEN_KEY) || !data.has(USER_KEY))
            return ILLEGALE_DATA;
        // TODO: 12/6/2018 check token and user name assignment

        return OK_RESPONSE;
    }
}
