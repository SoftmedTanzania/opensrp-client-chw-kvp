package org.smartregister.chw.kvp.interactor;

import org.smartregister.chw.kvp.contract.KvpRegisterContract;
import org.smartregister.chw.kvp.util.AppExecutors;
import org.smartregister.chw.kvp.util.KvpUtil;

import androidx.annotation.VisibleForTesting;

public class BaseKvpRegisterInteractor implements KvpRegisterContract.Interactor {

    private AppExecutors appExecutors;

    @VisibleForTesting
    BaseKvpRegisterInteractor(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
    }

    public BaseKvpRegisterInteractor() {
        this(new AppExecutors());
    }

    @Override
    public void saveRegistration(final String jsonString, final KvpRegisterContract.InteractorCallBack callBack) {

        Runnable runnable = () -> {
            try {
                KvpUtil.saveFormEvent(jsonString);
            } catch (Exception e) {
                e.printStackTrace();
            }

            appExecutors.mainThread().execute(() -> callBack.onRegistrationSaved());
        };
        appExecutors.diskIO().execute(runnable);
    }
}
