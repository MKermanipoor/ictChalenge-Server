package api.authenticationpackage;

import api.authenticationpackage.module.LoginModule;
import org.json.JSONObject;

import static api.APIPublicValue.*;

public class LoginAPI extends AuthenticationPackage<LoginModule> {

    private LoginModule loginModule;

    @Override
    public JSONObject call(JSONObject data) {
        int checkPermeationCode = super.checkPermeation(data);

        return new JSONObject();
    }

    @Override
    public JSONObject workApi(LoginModule module) {
        return new JSONObject();
    }

    @Override
    public int checkPermeation(JSONObject data) {
        int superCheckEx=super.checkPermeation(data);
        if(superCheckEx != OK_RESPONSE){
            return superCheckEx;
        }
        loginModule = LoginModule.create(data);
        return checkPermeation(loginModule);
    }

    public int checkPermeation(LoginModule data) {
        return 0;
    }
}
