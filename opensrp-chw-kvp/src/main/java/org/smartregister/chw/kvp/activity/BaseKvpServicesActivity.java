package org.smartregister.chw.kvp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.smartregister.chw.kvp.adapter.BaseServiceCardAdapter;
import org.smartregister.chw.kvp.dao.KvpDao;
import org.smartregister.chw.kvp.domain.MemberObject;
import org.smartregister.chw.kvp.domain.ServiceCard;
import org.smartregister.chw.kvp.handlers.BaseServiceActionHandler;
import org.smartregister.chw.kvp.util.Constants;
import org.smartregister.kvp.R;
import org.smartregister.view.activity.SecuredActivity;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BaseKvpServicesActivity extends SecuredActivity {
    protected BaseServiceCardAdapter serviceCardAdapter;
    protected BaseServiceActionHandler serviceActionHandler = new BaseServiceActionHandler();
    protected TextView tvTitle;
    protected MemberObject memberObject;
    protected String baseEntityId;

    public static void startMe(Activity activity, String baseEntityID) {
        Intent intent = new Intent(activity, BaseKvpServicesActivity.class);
        intent.putExtra(Constants.ACTIVITY_PAYLOAD.BASE_ENTITY_ID, baseEntityID);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kvp_services);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            baseEntityId = getIntent().getStringExtra(Constants.ACTIVITY_PAYLOAD.BASE_ENTITY_ID);
        }
        memberObject = KvpDao.getKvpMember(baseEntityId);
        setupViews();
        initializeMainServiceContainers();

    }


    protected void setupViews() {
        initializeRecyclerView();
        View cancelButton = findViewById(R.id.undo_button);
        cancelButton.setOnClickListener(v -> finish());
        tvTitle = findViewById(R.id.top_patient_name);
        tvTitle.setText(MessageFormat.format("{0}, {1}", memberObject.getFullName(), memberObject.getAge()));
    }

    protected void initializeRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        serviceCardAdapter = new BaseServiceCardAdapter(this, new ArrayList<>(), serviceActionHandler);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(serviceCardAdapter);
    }

    protected void initializeMainServiceContainers() {
        List<ServiceCard> serviceCards = new ArrayList<>();

        ServiceCard bioMedicalService = new ServiceCard();
        bioMedicalService.setServiceName("Bio-Medical Services");
        bioMedicalService.setActionItems(20);
        bioMedicalService.setServiceStatus("In-Progress");
        bioMedicalService.setServiceIcon(R.drawable.ic_bio_medical);
        bioMedicalService.setBackground(R.drawable.purple_bg);
        serviceCards.add(bioMedicalService);

        ServiceCard behavioralService = new ServiceCard();
        behavioralService.setServiceName("Behavioral Services");
        behavioralService.setActionItems(20);
        behavioralService.setServiceStatus("Not Started");
        behavioralService.setServiceIcon(R.drawable.ic_behavioral);
        behavioralService.setBackground(R.drawable.orange_bg);
        serviceCards.add(behavioralService);

        ServiceCard structuralService = new ServiceCard();
        structuralService.setServiceName("Structural Services");
        structuralService.setActionItems(20);
        structuralService.setServiceStatus("Complete");
        structuralService.setServiceIcon(R.drawable.ic_structural);
        structuralService.setBackground(R.drawable.dark_blue_bg);
        serviceCards.add(structuralService);

        ServiceCard otherService = new ServiceCard();
        otherService.setServiceName("Other Services");
        otherService.setActionItems(20);
        otherService.setServiceStatus("Not Started");
        otherService.setServiceIcon(R.drawable.ic_others);
        otherService.setBackground(R.drawable.ocean_blue_bg);
        serviceCards.add(otherService);

        serviceCardAdapter.setServiceCards(serviceCards);
    }

    @Override
    protected void onCreation() {
        //override
    }

    @Override
    protected void onResumption() {
        //override
    }
}
