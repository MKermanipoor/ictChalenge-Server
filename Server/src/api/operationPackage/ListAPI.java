package api.operationPackage;

import api.ApiErrorCodeParser;
import api.operationPackage.module.ListApiModule;
import org.json.JSONObject;

import static api.APIPublicValue.*;

public class ListAPI extends OperationPackage<ListApiModule> {
    private ListApiModule listApiModule;

    @Override
    public JSONObject call(JSONObject data) {
        int checkPermeationCode = checkPermeation(data);
        if (checkPermeationCode != OK_RESPONSE)
            return ApiErrorCodeParser.parseToJson(checkPermeationCode);

        return workApi(listApiModule);
    }

    @Override
    public JSONObject workApi(ListApiModule module) {
        // TODO: 12/6/2018 query to db
        return new JSONObject();
    }

    @Override
    public int checkPermeation(JSONObject data) {
        int checkPermeationCode = super.checkPermeation(data);
        if (checkPermeationCode != OK_RESPONSE)
            return checkPermeationCode;

        if (!data.has(FILE_UEL_KEY))
            return ILLEGAL_DATA;

        listApiModule = ListApiModule.create(data);
        return checkPermeation(listApiModule);
    }

    @Override
    public int checkPermeation(ListApiModule data) {
        return OK_RESPONSE;
    }
}
