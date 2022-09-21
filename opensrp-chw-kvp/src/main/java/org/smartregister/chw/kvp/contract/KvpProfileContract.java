package org.smartregister.chw.kvp.contract;

import org.jetbrains.annotations.Nullable;
import org.smartregister.chw.kvp.domain.MemberObject;
import org.smartregister.domain.AlertStatus;

import java.util.Date;

public interface KvpProfileContract {
    interface View extends InteractorCallBack {

        void setProfileViewWithData();

        void setOverDueColor();

        void openMedicalHistory();

        void openUpcomingService();

        void openFamilyDueServices();

        void showProgressBar(boolean status);

        void recordAnc(MemberObject memberObject);

        void recordPnc(MemberObject memberObject);

        void hideView();

        void openFollowupVisit();
    }

    interface Presenter {

        void fillProfileData(@Nullable MemberObject memberObject);

        void saveForm(String jsonString);

        @Nullable
        View getView();

        void refreshProfileBottom();

        void recordKvpButton(String visitState);
    }

    interface Interactor {

        void refreshProfileInfo(MemberObject memberObject, InteractorCallBack callback);

        void saveRegistration(String jsonString, final KvpProfileContract.InteractorCallBack callBack);
    }


    interface InteractorCallBack {

        void refreshMedicalHistory(boolean hasHistory);

        void refreshUpComingServicesStatus(String service, AlertStatus status, Date date);

        void refreshFamilyStatus(AlertStatus status);

    }
}