package org.smartregister.chw.kvp.presenter;

import android.content.Context;

import org.smartregister.chw.kvp.contract.KvpProfileContract;
import org.smartregister.chw.kvp.domain.MemberObject;

import java.lang.ref.WeakReference;

import androidx.annotation.Nullable;
import timber.log.Timber;


public class BaseKvpProfilePresenter implements KvpProfileContract.Presenter {
    protected WeakReference<KvpProfileContract.View> view;
    protected MemberObject memberObject;
    protected KvpProfileContract.Interactor interactor;
    protected Context context;

    public BaseKvpProfilePresenter(KvpProfileContract.View view, KvpProfileContract.Interactor interactor, MemberObject memberObject) {
        this.view = new WeakReference<>(view);
        this.memberObject = memberObject;
        this.interactor = interactor;
    }

    @Override
    public void fillProfileData(MemberObject memberObject) {
        if (memberObject != null && getView() != null) {
            getView().setProfileViewWithData();
        }
    }

    @Override
    public void recordKvpButton(@Nullable String visitState) {
        if (getView() == null) {
            return;
        }

        if (("OVERDUE").equals(visitState) || ("DUE").equals(visitState)) {
            if (("OVERDUE").equals(visitState)) {
                getView().setOverDueColor();
            }
        } else {
            getView().hideView();
        }
    }

    @Override
    @Nullable
    public KvpProfileContract.View getView() {
        if (view != null && view.get() != null)
            return view.get();

        return null;
    }

    @Override
    public void refreshProfileBottom() {
        interactor.refreshProfileInfo(memberObject, getView());
    }

    @Override
    public void saveForm(String jsonString) {
        try {
            interactor.saveRegistration(jsonString, getView());
        } catch (Exception e) {
            Timber.e(e);
        }
    }
}
