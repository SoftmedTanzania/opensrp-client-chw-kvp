package org.smartregister.chw.kvp.contract;

import com.vijay.jsonwizard.domain.Form;

import org.json.JSONObject;
import org.smartregister.view.contract.BaseRegisterContract;

public interface KvpRegisterContract {

    interface View extends BaseRegisterContract.View {
        Presenter presenter();

        Form getFormConfig();

        void startFormActivity(String formName, String baseEntityId, String metadata, String gender, int age);
    }

    interface Presenter extends BaseRegisterContract.Presenter {

        void startForm(String formName, String entityId, String metadata, String currentLocationId) throws Exception;
        void startForm(String formName, String entityId, String metadata, String currentLocationId, String gender, int age) throws Exception;

        void saveForm(String jsonString);

    }

    interface Model {

        JSONObject getFormAsJson(String formName, String entityId, String currentLocationId) throws Exception;
        JSONObject getFormAsJson(String formName, String entityId, String currentLocationId, String gender, int age) throws Exception;

    }

    interface Interactor {

        void saveRegistration(String jsonString, final InteractorCallBack callBack);

    }

    interface InteractorCallBack {

        void onRegistrationSaved();

    }
}
