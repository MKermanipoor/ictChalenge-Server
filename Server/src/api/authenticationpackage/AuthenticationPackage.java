package api.authenticationpackage;

import api.APIModule;
import org.json.JSONObject;
import api.API;

import static api.APIPublicValue.*;
public abstract class AuthenticationPackage<T extends APIModule> extends API<T> {

    @Override
    public int checkPermeation(JSONObject data) {
        // TODO: 12/6/2018 check spaming
        int superExitCode = super.checkPermeation(data);
        if (superExitCode != OK_RESPONSE){
            return superExitCode;
        }

        return OK_RESPONSE;
    }
}
