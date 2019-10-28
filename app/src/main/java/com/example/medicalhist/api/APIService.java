package com.example.medicalhist.api;

import com.example.medicalhist.model.Family;
import com.example.medicalhist.model.Hospital;
import com.example.medicalhist.model.HospitalList;
import com.example.medicalhist.model.Medication;
import com.example.medicalhist.model.Profile;
import com.example.medicalhist.model.Result;
import com.example.medicalhist.model.Treatment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

    //the token authentication call
//    @FormUrlEncoded
//    @POST("visitedHospitals")
//    Call<Result> visitedHospitals(
//            @Field("national_id") int national_id
//    );
//
//    @FormUrlEncoded
//    @POST("patient")
//    Call<List<Hospital>> getHospitals(@Field("national_id") int national_id);

    @GET("patient/{national_id}")
    Call<List<Hospital>> getHospitals(@Path("national_id") int national_id);


    @FormUrlEncoded
    @POST("login")
    Call<Result> userLogin(
            @Field("national_id") int national_id,
            @Field("password") String password,
            @Field("hospital_id") int hospital_id
    );

    @FormUrlEncoded
    @POST("patientInfo")
    Call<Profile> getPatientInfo(
            @Field("national_id") int national_id,
            @Field("password") String password,
            @Field("hospital_id") int hospital_id
    );

    @FormUrlEncoded
    @POST("patientFamily")
    Call<List<Family>> getFamilyInfo(
            @Field("national_id") int national_id,
            @Field("password") String password,
            @Field("hospital_id") int hospital_id
    );

    @FormUrlEncoded
    @POST("patientMedication")
    Call<List<Medication>> getMedicationInfo(
            @Field("national_id") int national_id,
            @Field("password") String password,
            @Field("hospital_id") int hospital_id
    );

    @FormUrlEncoded
    @POST("patientTreatment")
    Call<List<Treatment>> getTreatmentInfo(
            @Field("national_id") int national_id,
            @Field("password") String password,
            @Field("hospital_id") int hospital_id
    );


}
