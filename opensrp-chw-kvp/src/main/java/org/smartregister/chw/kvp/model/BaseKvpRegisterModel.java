package org.smartregister.chw.kvp.model;

import org.json.JSONObject;
import org.smartregister.chw.kvp.contract.KvpRegisterContract;
import org.smartregister.chw.kvp.util.TestJsonFormUtils;

public class BaseKvpRegisterModel implements KvpRegisterContract.Model {

    @Override
    public JSONObject getFormAsJson(String formName, String entityId, String currentLocationId) throws Exception {
        JSONObject jsonObject = TestJsonFormUtils.getFormAsJson(formName);
        TestJsonFormUtils.getRegistrationForm(jsonObject, entityId, currentLocationId);

        return jsonObject;
    }

}
