package com.example.medicalhist.sharedManager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.medicalhist.model.Family;
import com.example.medicalhist.model.Medication;
import com.example.medicalhist.model.Profile;
import com.example.medicalhist.model.Treatment;

public class SharedPrefManager {
    private static SharedPrefManager mInstance;
    private static Context context;

    private static final String SHARED_PRED_NAME = "sharedpreretrofit";

    private static final String key_user_id = "keyuserid";
    private static final String key_user_surName = "keyusersurName";
    private static final String key_user_firstName = "keyuserfirstName";
    private static final String key_user_lastName = "keyuserlastName";
    private static final String key_user_dateOfBirth = "keyuserdateOfBirth";
    private static final String key_user_email = "keyuseremail";
    private static final String key_user_residence = "keyuserresidence";
    private static final String key_user_family_member = "keyuserfamily_member";
    private static final String key_user_hereditary_disease = "keyuserhereditary_disease";
    private static final String key_user_mental_condition = "keyusermental_condition";
    private static final String key_user_pregnancy_complications = "keyuserpregnancy_complications";
    private static final String key_user_DR_course_o_death = "keyuserDR_course_o_death";
    private static final String key_user_problem_list = "keyuserproblem_list";
    private static final String key_user_allergies = "keyuserallergies";
    private static final String key_user_drug_abuse = "keyuserdrug_abuse";
    private static final String key_user_current_medication = "keyusercurrent_medication";
    private static final String key_user_diagnostics_results = "keyuserdiagnostics_results";
    private static final String key_user_patient_type = "keyuserpatient_type";
    private static final String key_user_prescription = "keyuserprescription";
    private static final String key_user_consultation = "keyuserconsultation";
    private static final String key_user_advice = "keyuseradvice";

    private SharedPrefManager(Context context){ context = context;}

    public static synchronized SharedPrefManager getInstance(Context context1){
        if (mInstance == null){ mInstance = new SharedPrefManager(context1);}
        return mInstance;
    }

    public boolean profileInfo(Profile profile){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PRED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key_user_surName,profile.getSurName());
        editor.putString(key_user_firstName,profile.getFirstName());
        editor.putString(key_user_lastName,profile.getLastName());
        editor.putString(key_user_dateOfBirth,profile.getDateOfBirth());
        editor.putString(key_user_email,profile.getEmail());
        editor.putString(key_user_residence,profile.getResidence());
        editor.apply();
        return true;

    }

    public Profile getProfile(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PRED_NAME, Context.MODE_PRIVATE);
        return new Profile(
                sharedPreferences.getString(key_user_surName,null),
                sharedPreferences.getString(key_user_firstName,null),
                sharedPreferences.getString(key_user_lastName,null),
                sharedPreferences.getString(key_user_dateOfBirth,null),
                sharedPreferences.getString(key_user_email,null),
                sharedPreferences.getString(key_user_residence,null)
        );
    }

    public Family getFamily(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PRED_NAME, Context.MODE_PRIVATE);
        return new Family(
                sharedPreferences.getString(key_user_family_member,null),
                sharedPreferences.getString(key_user_hereditary_disease,null),
                sharedPreferences.getString(key_user_mental_condition,null),
                sharedPreferences.getString(key_user_pregnancy_complications,null),
                sharedPreferences.getString(key_user_DR_course_o_death,null)
        );
    }

    public Medication getMedication(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PRED_NAME, Context.MODE_PRIVATE);
        return new Medication(
                sharedPreferences.getString(key_user_problem_list,null),
                sharedPreferences.getString(key_user_allergies,null),
                sharedPreferences.getString(key_user_drug_abuse,null),
                sharedPreferences.getString(key_user_current_medication,null),
                sharedPreferences.getString(key_user_diagnostics_results,null),
                sharedPreferences.getString(key_user_patient_type,null)
        );
    }

    public Treatment getTreatment(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PRED_NAME, Context.MODE_PRIVATE);
        return new Treatment(
                sharedPreferences.getString(key_user_prescription,null),
                sharedPreferences.getString(key_user_consultation,null),
                sharedPreferences.getString(key_user_advice,null)
        );
    }

    public boolean logout(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PRED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }


}
