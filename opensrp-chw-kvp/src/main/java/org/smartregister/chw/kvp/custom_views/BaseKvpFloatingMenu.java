package org.smartregister.chw.kvp.custom_views;

import android.app.Activity;
import android.content.Context;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.View;
import android.widget.LinearLayout;

import org.smartregister.chw.kvp.domain.MemberObject;
import org.smartregister.chw.kvp.fragment.BaseKvpCallDialogFragment;
import org.smartregister.kvp.R;

public class BaseKvpFloatingMenu extends LinearLayout implements View.OnClickListener {
    private MemberObject MEMBER_OBJECT;

    public BaseKvpFloatingMenu(Context context, MemberObject MEMBER_OBJECT) {
        super(context);
        initUi();
        this.MEMBER_OBJECT = MEMBER_OBJECT;
    }

    protected void initUi() {
        inflate(getContext(), R.layout.view_kvp_floating_menu, this);
        FloatingActionButton fab = findViewById(R.id.kvp_fab);
        if (fab != null)
            fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.kvp_fab) {
            Activity activity = (Activity) getContext();
            BaseKvpCallDialogFragment.launchDialog(activity, MEMBER_OBJECT);
        }  else if (view.getId() == R.id.refer_to_facility_layout) {
            Activity activity = (Activity) getContext();
            BaseKvpCallDialogFragment.launchDialog(activity, MEMBER_OBJECT);
        }
    }
}