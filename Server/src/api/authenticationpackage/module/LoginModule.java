package api.authenticationPackage.module;

import api.APIModule;
import api.authenticationPackage.LoginAPI;
import org.json.JSONObject;

public class LoginModule extends APIModule {

    private final String userName;
    private final String password;

    private LoginModule(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public static LoginModule create(JSONObject data) {
        String pass,user;

        user = data.optString(LoginAPI.USER_NAME_KEY);
        pass = data.optString(LoginAPI.PASSWORD_KEY);

        return new LoginModule(user, pass);
    }
}
