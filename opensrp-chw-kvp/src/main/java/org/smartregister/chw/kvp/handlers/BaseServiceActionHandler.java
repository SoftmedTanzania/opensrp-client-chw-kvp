package org.smartregister.chw.kvp.handlers;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import org.smartregister.chw.kvp.KvpLibrary;
import org.smartregister.chw.kvp.activity.BaseKvpVisitActivity;
import org.smartregister.chw.kvp.domain.ServiceCard;
import org.smartregister.chw.kvp.domain.Visit;
import org.smartregister.chw.kvp.util.Constants;
import org.smartregister.kvp.R;

public class BaseServiceActionHandler implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        int i = view.getId();
        ServiceCard serviceCard = (ServiceCard) view.getTag();
        String baseEntityID = (String) view.getTag(R.id.BASE_ENTITY_ID);
        if (i == R.id.process_visit) {
            Toast.makeText(view.getContext(), "Process Visit clicked", Toast.LENGTH_SHORT).show();
        }
        if (i == R.id.card_layout) {
            startVisitActivity(view.getContext(), serviceCard, baseEntityID);
        }
    }

    protected void startVisitActivity(Context context, ServiceCard serviceCard, String baseEntityId) {
        boolean isEditMode = isEditMode(serviceCard.getServiceEventName(), baseEntityId);
        if (serviceCard.getServiceId().equals(Constants.SERVICES.KVP_BIO_MEDICAL)) {
            BaseKvpVisitActivity.startMe((Activity) context, baseEntityId, isEditMode, Constants.PROFILE_TYPES.KVP_PROFILE);
            return;
        }
        Toast.makeText(context, serviceCard.getServiceName() + "Loading activity... ", Toast.LENGTH_SHORT).show();

    }

    protected boolean isEditMode(String eventName, String baseEntityId) {
        Visit lastVisit = KvpLibrary.getInstance().visitRepository().getLatestVisit(baseEntityId, eventName);
        return lastVisit != null && !lastVisit.getProcessed();
    }

}
