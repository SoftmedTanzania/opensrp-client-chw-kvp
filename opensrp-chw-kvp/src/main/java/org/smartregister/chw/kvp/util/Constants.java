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
        String KVP_FOLLOW_UP_VISIT = "Kvp Follow-up Visit";
        String VOID_EVENT = "Void Event";
    }

    interface FORMS {
        String KVP_REGISTRATION = "kvp_confirmation";
        String KVP_FOLLOW_UP_VISIT = "kvp_followup_visit";
    }

    interface TABLES {
        String KVP_PrEP_REGISTER = "ec_kvp_prep_register";
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

}