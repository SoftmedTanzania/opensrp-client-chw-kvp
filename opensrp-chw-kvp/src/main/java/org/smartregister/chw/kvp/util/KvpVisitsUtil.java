package org.smartregister.chw.kvp.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.smartregister.chw.kvp.KvpLibrary;
import org.smartregister.chw.kvp.domain.Visit;
import org.smartregister.chw.kvp.repository.VisitDetailsRepository;
import org.smartregister.chw.kvp.repository.VisitRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import timber.log.Timber;

public class KvpVisitsUtil extends VisitUtils {

    public static String Complete = "complete";
    public static String Pending = "pending";
    public static String Ongoing = "ongoing";

    public static void processVisits() throws Exception {
        processVisits(KvpLibrary.getInstance().visitRepository(), KvpLibrary.getInstance().visitDetailsRepository());
    }

    private static void processVisits(VisitRepository visitRepository, VisitDetailsRepository visitDetailsRepository) throws Exception {
        List<Visit> visits = visitRepository.getAllUnSynced();
        List<Visit> bioMedicalServiceVisit = new ArrayList<>();
        List<Visit> behavioralServiceVisits = new ArrayList<>();
        List<Visit> structuralServiceVisits = new ArrayList<>();
        List<Visit> otherServiceVisits = new ArrayList<>();

        for (Visit v : visits) {
            Date truncatedUpdatedDate = new Date(v.getUpdatedAt().getTime() - v.getUpdatedAt().getTime() % (24 * 60 * 60 * 1000));
            Date today = new Date(Calendar.getInstance().getTimeInMillis() - Calendar.getInstance().getTimeInMillis() % (24 * 60 * 60 * 1000));
            if (truncatedUpdatedDate.before(today)) {
                if (v.getVisitType().equalsIgnoreCase(Constants.EVENT_TYPE.KVP_BIO_MEDICAL_SERVICE_VISIT) && getBioMedicalStatus(v).equals(Complete)) {
                    bioMedicalServiceVisit.add(v);
                }
                if (v.getVisitType().equalsIgnoreCase(Constants.EVENT_TYPE.KVP_BEHAVIORAL_SERVICE_VISIT) && getBehavioralServiceStatus(v).equals(Complete)) {
                    behavioralServiceVisits.add(v);
                }
                if (v.getVisitType().equalsIgnoreCase(Constants.EVENT_TYPE.KVP_STRUCTURAL_SERVICE_VISIT) && getStructuralServiceStatus(v).equals(Complete)) {
                    structuralServiceVisits.add(v);
                }
                if (v.getVisitType().equalsIgnoreCase(Constants.EVENT_TYPE.KVP_OTHER_SERVICE_VISIT) && getOtherServiceStatus(v).equals(Complete)) {
                    otherServiceVisits.add(v);
                }
            }
        }
        if (bioMedicalServiceVisit.size() > 0) {
            processVisits(bioMedicalServiceVisit, visitRepository, visitDetailsRepository);
        }
        if (behavioralServiceVisits.size() > 0) {
            processVisits(behavioralServiceVisits, visitRepository, visitDetailsRepository);
        }
        if (structuralServiceVisits.size() > 0) {
            processVisits(structuralServiceVisits, visitRepository, visitDetailsRepository);
        }
        if (otherServiceVisits.size() > 0) {
            processVisits(otherServiceVisits, visitRepository, visitDetailsRepository);
        }
    }

    public static String getBioMedicalStatus(Visit lastVisit) {
        HashMap<String, Boolean> completionObject = new HashMap<>();
        try {
            JSONObject jsonObject = new JSONObject(lastVisit.getJson());
            JSONArray obs = jsonObject.getJSONArray("obs");

            completionObject.put("is-client_status-done", computeCompletionStatus(obs, "client_status"));
            completionObject.put("is-condom_provision-done", computeCompletionStatus(obs, "provided_male_condoms"));
            completionObject.put("is-hts-done", computeCompletionStatus(obs, "previous_hiv_testing_method"));
            completionObject.put("is-hepatitis-done", computeCompletionStatus(obs, "hep_b_screening"));
            completionObject.put("is-family_planning-done", computeCompletionStatus(obs, "family_planning_service"));
            completionObject.put("is-mat-done", computeCompletionStatus(obs, "mat_provided"));
            completionObject.put("is-vmmc-done", computeCompletionStatus(obs, "vmcc_provided"));
            completionObject.put("is-tb_screening-done", computeCompletionStatus(obs, "tb_screening"));
            completionObject.put("is-cervical_cancer_screening-done", computeCompletionStatus(obs, "cervical_cancer_screening"));

        } catch (Exception e) {
            Timber.e(e);
        }
        return getActionStatus(completionObject);
    }

    public static String getBehavioralServiceStatus(Visit lastVisit) {
        HashMap<String, Boolean> completionObject = new HashMap<>();
        try {
            JSONObject jsonObject = new JSONObject(lastVisit.getJson());
            JSONArray obs = jsonObject.getJSONArray("obs");

            completionObject.put("is-iec_sbcc-done", computeCompletionStatus(obs, "iec_sbcc_materials"));
            completionObject.put("is-health_education-done", computeCompletionStatus(obs, "health_education_provided"));

        } catch (Exception e) {
            Timber.e(e);
        }
        return getActionStatus(completionObject);
    }

    public static String getStructuralServiceStatus(Visit lastVisit) {
        HashMap<String, Boolean> completionObject = new HashMap<>();
        try {
            JSONObject jsonObject = new JSONObject(lastVisit.getJson());
            JSONArray obs = jsonObject.getJSONArray("obs");

            completionObject.put("is-gbv_analysis-done", computeCompletionStatus(obs, "gbv_screening"));

        } catch (Exception e) {
            Timber.e(e);
        }
        return getActionStatus(completionObject);
    }

    public static String getOtherServiceStatus(Visit lastVisit) {
        HashMap<String, Boolean> completionObject = new HashMap<>();
        try {
            JSONObject jsonObject = new JSONObject(lastVisit.getJson());
            JSONArray obs = jsonObject.getJSONArray("obs");

            completionObject.put("is-other_services_and_referrals-done", computeCompletionStatus(obs, "health_education_provided"));

        } catch (Exception e) {
            Timber.e(e);
        }
        return getActionStatus(completionObject);
    }

    public static String getActionStatus(Map<String, Boolean> checkObject) {
        for (Map.Entry<String, Boolean> entry : checkObject.entrySet()) {
            if (entry.getValue()) {
                if (checkObject.containsValue(false)) {
                    return Ongoing;
                }
                return Complete;
            }
        }
        return Pending;
    }

    public static boolean computeCompletionStatus(JSONArray obs, String checkString) throws JSONException {
        int size = obs.length();
        for (int i = 0; i < size; i++) {
            JSONObject checkObj = obs.getJSONObject(i);
            if (checkObj.getString("fieldCode").equalsIgnoreCase(checkString)) {
                return true;
            }
        }
        return false;
    }

    public static void manualProcessVisit(Visit visit) throws Exception {
        List<Visit> manualProcessedVisits = new ArrayList<>();
        VisitDetailsRepository visitDetailsRepository = KvpLibrary.getInstance().visitDetailsRepository();
        VisitRepository visitRepository = KvpLibrary.getInstance().visitRepository();
        manualProcessedVisits.add(visit);
        processVisits(manualProcessedVisits, visitRepository, visitDetailsRepository);
    }
}
