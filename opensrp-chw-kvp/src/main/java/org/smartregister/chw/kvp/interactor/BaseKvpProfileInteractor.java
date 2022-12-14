package org.smartregister.chw.kvp.interactor;

import org.smartregister.chw.kvp.contract.KvpProfileContract;
import org.smartregister.chw.kvp.domain.MemberObject;
import org.smartregister.chw.kvp.util.AppExecutors;
import org.smartregister.chw.kvp.util.KvpUtil;
import org.smartregister.domain.AlertStatus;

import java.util.Date;

import androidx.annotation.VisibleForTesting;

public class BaseKvpProfileInteractor implements KvpProfileContract.Interactor {
    protected AppExecutors appExecutors;

    @VisibleForTesting
    BaseKvpProfileInteractor(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
    }

    public BaseKvpProfileInteractor() {
        this(new AppExecutors());
    }

    @Override
    public void refreshProfileInfo(MemberObject memberObject, KvpProfileContract.InteractorCallBack callback) {
        Runnable runnable = () -> appExecutors.mainThread().execute(() -> {
            callback.refreshFamilyStatus(AlertStatus.normal);
            callback.refreshMedicalHistory(true);
            callback.refreshUpComingServicesStatus("Kvp Visit", AlertStatus.normal, new Date());
        });
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void saveRegistration(final String jsonString, final KvpProfileContract.InteractorCallBack callback) {

        Runnable runnable = () -> {
            try {
                KvpUtil.saveFormEvent(jsonString);
            } catch (Exception e) {
                e.printStackTrace();
            }

        };
        appExecutors.diskIO().execute(runnable);
    }
}
