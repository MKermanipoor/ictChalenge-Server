package api.authenticationPackage;

import api.ApiErrorCodeParser;
import api.authenticationPackage.module.LoginModule;
import org.json.JSONObject;

import static api.APIPublicValue.*;

public class LoginAPI extends AuthenticationPackage<LoginModule> {

    public static final String USER_NAME_KEY = "userName";
    public static final String PASSWORD_KEY = "password";

    private LoginModule loginModule;

    @Override
    public JSONObject call(JSONObject data) {
        int checkPermeationCode = checkPermeation(data);
        if (checkPermeationCode != OK_RESPONSE)
            return ApiErrorCodeParser.parseToJson(checkPermeationCode);

        return workApi(loginModule);
    }

    @Override
    public JSONObject workApi(LoginModule module) {
        // TODO: 12/6/2018 check user data and make json

        return new JSONObject();
    }

    @Override
    public int checkPermeation(JSONObject data) {
        int superCheckEx = super.checkPermeation(data);
        if (superCheckEx != OK_RESPONSE) {
            return superCheckEx;
        }
        loginModule = LoginModule.create(data);
        return checkPermeation(loginModule);
    }

    public int checkPermeation(LoginModule data) {
        if (data.getPassword() == null || data.getPassword().isEmpty() || data.getUserName() == null || data.getUserName().isEmpty())
            return ILLEGALE_DATA;

        return OK_RESPONSE;
    }
}
