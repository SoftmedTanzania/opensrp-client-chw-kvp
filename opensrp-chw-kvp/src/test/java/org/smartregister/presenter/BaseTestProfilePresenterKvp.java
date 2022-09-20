package org.smartregister.presenter;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.smartregister.chw.kvp.contract.KvpProfileContract;
import org.smartregister.chw.kvp.domain.MemberObject;
import org.smartregister.chw.kvp.presenter.BaseKvpProfilePresenter;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class BaseTestProfilePresenterKvp {

    @Mock
    private KvpProfileContract.View view = Mockito.mock(KvpProfileContract.View.class);

    @Mock
    private KvpProfileContract.Interactor interactor = Mockito.mock(KvpProfileContract.Interactor.class);

    @Mock
    private MemberObject memberObject = new MemberObject();

    private BaseKvpProfilePresenter profilePresenter = new BaseKvpProfilePresenter(view, interactor, memberObject);


    @Test
    public void fillProfileDataCallsSetProfileViewWithDataWhenPassedMemberObject() {
        profilePresenter.fillProfileData(memberObject);
        verify(view).setProfileViewWithData();
    }

    @Test
    public void fillProfileDataDoesntCallsSetProfileViewWithDataIfMemberObjectEmpty() {
        profilePresenter.fillProfileData(null);
        verify(view, never()).setProfileViewWithData();
    }

    @Test
    public void kvpTestDatePeriodIsLessThanSeven() {
        profilePresenter.recordKvpButton("");
        verify(view).hideView();
    }

    @Test
    public void kvpTestDatePeriodGreaterThanTen() {
        profilePresenter.recordKvpButton("OVERDUE");
        verify(view).setOverDueColor();
    }

    @Test
    public void kvpTestDatePeriodIsMoreThanFourteen() {
        profilePresenter.recordKvpButton("EXPIRED");
        verify(view).hideView();
    }

    @Test
    public void refreshProfileBottom() {
        profilePresenter.refreshProfileBottom();
        verify(interactor).refreshProfileInfo(memberObject, profilePresenter.getView());
    }

    @Test
    public void saveForm() {
        profilePresenter.saveForm(null);
        verify(interactor).saveRegistration(null, view);
    }
}
