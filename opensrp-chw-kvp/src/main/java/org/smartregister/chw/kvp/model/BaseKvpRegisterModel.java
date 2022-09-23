package org.smartregister.chw.kvp.model;

import com.vijay.jsonwizard.constants.JsonFormConstants;

import org.json.JSONArray;
import org.json.JSONObject;
import org.smartregister.chw.kvp.contract.KvpRegisterContract;
import org.smartregister.chw.kvp.util.Constants;
import org.smartregister.chw.kvp.util.KvpJsonFormUtils;
import org.smartregister.util.JsonFormUtils;

import static org.smartregister.chw.kvp.util.Constants.STEP_ONE;

public class BaseKvpRegisterModel implements KvpRegisterContract.Model {

    @Override
    public JSONObject getFormAsJson(String formName, String entityId, String currentLocationId) throws Exception {
        JSONObject jsonObject = KvpJsonFormUtils.getFormAsJson(formName);
        KvpJsonFormUtils.getRegistrationForm(jsonObject, entityId, currentLocationId);

        JSONArray fields = jsonObject.getJSONObject(STEP_ONE).getJSONArray(JsonFormConstants.FIELDS);
        JSONObject referralHealthFacilities = JsonFormUtils.getFieldJSONObject(fields, Constants.JSON_FORM_KEY.FACILITY_NAME);
        if(referralHealthFacilities!= null){
            KvpJsonFormUtils.initializeHealthFacilitiesList(referralHealthFacilities);
        }

        return jsonObject;
    }

    @Override
    public JSONObject getFormAsJson(String formName, String entityId, String currentLocationId, String gender, int age) throws Exception {
        JSONObject jsonObject = KvpJsonFormUtils.getFormAsJson(formName);
        KvpJsonFormUtils.getRegistrationForm(jsonObject, entityId, currentLocationId);

        JSONArray fields = jsonObject.getJSONObject(STEP_ONE).getJSONArray(JsonFormConstants.FIELDS);
        JSONObject referralHealthFacilities = JsonFormUtils.getFieldJSONObject(fields, Constants.JSON_FORM_KEY.FACILITY_NAME);
        if(referralHealthFacilities!= null){
            KvpJsonFormUtils.initializeHealthFacilitiesList(referralHealthFacilities);
        }

        JSONObject global = jsonObject.getJSONObject("global");
        if (global != null && gender != null) {
            global.put("age", age);
            global.put("gender", gender);
        }

        return jsonObject;
    }

}
