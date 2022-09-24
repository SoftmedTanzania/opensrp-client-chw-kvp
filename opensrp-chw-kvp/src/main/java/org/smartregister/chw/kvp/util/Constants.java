package org.smartregister.chw.kvp.util;

public interface Constants {

    int REQUEST_CODE_GET_JSON = 2244;
    String ENCOUNTER_TYPE = "encounter_type";
    String STEP_ONE = "step1";
    String STEP_TWO = "step2";
    String KVP_VISIT_GROUP = "kvp_visit_group";

    interface JSON_FORM_EXTRA {
        String JSON = "json";
        String ENCOUNTER_TYPE = "encounter_type";
    }

    interface EVENT_TYPE {
        String KVP_PrEP_REGISTRATION = "KVP PrEP Registration";
        String KVP_PrEP_FOLLOW_UP_VISIT = "Kvp PrEP Follow-up Visit";
        String KVP_REGISTRATION = "KVP Registration";
        String PrEP_REGISTRATION = "PrEP Registration";
        String VOID_EVENT = "Void Event";
        String KVP_BIO_MEDICAL_SERVICE_VISIT = "KVP Bio Medical Service Visit";
        String KVP_BEHAVIORAL_SERVICE_VISIT = "KVP Behavioral Service Visit";
        String KVP_STRUCTURAL_SERVICE_VISIT = "KVP Structural Service Visit";
        String KVP_OTHER_SERVICE_VISIT = "KVP Other Service Visit";
    }

    interface FORMS {
        String KVP_PrEP_REGISTRATION = "kvp_prep_registration";
        String KVP_REGISTRATION = "kvp_confirmation";
        String KVP_FOLLOW_UP_VISIT = "kvp_followup_visit";
        String KVP_SCREENING_MALE = "kvp_screening_male";
        String KVP_SCREENING_FEMALE = "kvp_screening_female";
    }

    interface KVP_PrEP_FOLLOWUP_FORMS {
        String VISIT_TYPE = "kvp_prep_visit_type";
        String SBCC_SERVICES = "kvp_prep_sbcc_services";
        String PREVENTIVE_SERVICES = "kvp_prep_preventive_services";
        String STRUCTURAL_SERVICES = "kvp_prep_structural_services";
        String REFERRAL_SERVICES = "kvp_prep_referral_services";
    }

    interface KVP_BIO_MEDICAL_SERVICE_FORMS {
        String KVP_CLIENT_STATUS = "kvp_client_status";
        String KVP_CONDOM_PROVISION = "kvp_condom_provision";
        String KVP_HTS = "kvp_hts";
    }

    interface TABLES {
        String KVP_PrEP_REGISTER = "ec_kvp_prep_register";
        String KVP_PrEP_FOLLOWUP = "ec_kvp_prep_followup";
        String KVP_REGISTER = "ec_kvp_register";
        String PrEP_REGISTER = "ec_prep_register";
        String KVP_FOLLOW_UP = "ec_kvp_follow_up_visit";
    }

    interface ACTIVITY_PAYLOAD {
        String BASE_ENTITY_ID = "BASE_ENTITY_ID";
        String FAMILY_BASE_ENTITY_ID = "FAMILY_BASE_ENTITY_ID";
        String ACTION = "ACTION";
        String KVP_FORM_NAME = "KVP_FORM_NAME";
        String MEMBER_PROFILE_OBJECT = "MemberObject";
        String EDIT_MODE = "editMode";
        String GENDER = "gender";
        String AGE = "age";

        String PROFILE_TYPE = "profile_type";
    }

    interface ACTIVITY_PAYLOAD_TYPE {
        String REGISTRATION = "REGISTRATION";
        String FOLLOW_UP_VISIT = "FOLLOW_UP_VISIT";
    }

    interface CONFIGURATION {
        String KVP_CONFIRMATION = "kvp_confirmation";
    }

    interface KVP_MEMBER_OBJECT {
        String MEMBER_OBJECT = "memberObject";
    }

    interface JSON_FORM_KEY {
        String FACILITY_NAME = "facility_name";
    }

    interface PROFILE_TYPES {
        String KVP_PROFILE = "kvp_profile";
        String KVP_PrEP_PROFILE = "kvp_prep_profile";
        String PrEP_PROFILE = "prep_profile";
    }

    interface SERVICES {
        String KVP_BIO_MEDICAL = "kvp_bio_medical";
        String KVP_BEHAVIORAL = "kvp_behavioral";
        String KVP_OTHERS = "kvp_others";
        String KVP_STRUCTURAL = "kvp_structural";
    }


}