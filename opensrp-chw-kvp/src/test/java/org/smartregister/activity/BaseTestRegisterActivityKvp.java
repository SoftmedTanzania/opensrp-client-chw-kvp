package org.smartregister.activity;

import android.content.Intent;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;
import org.smartregister.chw.kvp.activity.BaseKvpRegisterActivity;

public class BaseTestRegisterActivityKvp {
    @Mock
    public Intent data;
    @Mock
    private BaseKvpRegisterActivity baseKvpRegisterActivity = new BaseKvpRegisterActivity();

    @Test
    public void assertNotNull() {
        Assert.assertNotNull(baseKvpRegisterActivity);
    }

    @Test
    public void testFormConfig() {
        Assert.assertNull(baseKvpRegisterActivity.getFormConfig());
    }

    @Test
    public void checkIdentifier() {
        Assert.assertNotNull(baseKvpRegisterActivity.getViewIdentifiers());
    }

    @Test(expected = Exception.class)
    public void onActivityResult() throws Exception {
        Whitebox.invokeMethod(baseKvpRegisterActivity, "onActivityResult", 2244, -1, data);
        Mockito.verify(baseKvpRegisterActivity.presenter()).saveForm(null);
    }

}
