package org.smartregister.chw.kvp.contract;

import android.content.Context;

public interface BaseKvpCallDialogContract {

    interface View {
        void setPendingCallRequest(Dialer dialer);
        Context getCurrentContext();
    }

    interface Dialer {
        void callMe();
    }
}
