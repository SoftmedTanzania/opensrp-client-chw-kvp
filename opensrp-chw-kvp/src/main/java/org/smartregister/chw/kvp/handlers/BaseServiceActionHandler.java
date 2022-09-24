package org.smartregister.chw.kvp.handlers;

import android.view.View;
import android.widget.Toast;

import org.smartregister.chw.kvp.domain.ServiceCard;
import org.smartregister.kvp.R;

public class BaseServiceActionHandler implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if(i == R.id.process_visit){
            Toast.makeText(view.getContext(), "Process Visit clicked", Toast.LENGTH_SHORT).show();
        }
        if(i == R.id.card_layout){
            ServiceCard serviceCard = (ServiceCard) view.getTag();
            Toast.makeText(view.getContext(),  serviceCard.getServiceName()+ "Loading activity... ", Toast.LENGTH_SHORT).show();
        }
    }
}
