package com.example.medicalhist.api;

import com.example.medicalhist.model.DataList;
import com.example.medicalhist.model.Datum;
import com.example.medicalhist.model.Family;
import com.example.medicalhist.model.Hospital;
import com.example.medicalhist.model.HospitalList;
import com.example.medicalhist.model.Medication;
import com.example.medicalhist.model.MlPrediction;
import com.example.medicalhist.model.Profile;
import com.example.medicalhist.model.Result;
import com.example.medicalhist.model.Treatment;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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

//    @GET("patient/{national_id}")
//    Call<List<Hospital>> getHospitals(@Path("national_id") int national_id);

    @FormUrlEncoded
    @POST("tokenAuth")
    Call<Result> authUer(
            @Field("national_id") int national_id,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("hospitalList")
    Call<List<Hospital>> getHospitals(
            @Field("national_id") int national_id,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("userlogin")
    Call<Result> userLogin(
            @Field("national_id") int national_id,
            @Field("password") String password,
            @Field("hospital_id") int hospital_id
    );

    @FormUrlEncoded
    @POST("profile")
    Call<Profile> getPatientInfo(
            @Field("national_id") int national_id,
            @Field("password") String password,
            @Field("hospital_id") int hospital_id
    );

    @FormUrlEncoded
    @POST("pfamily")
    Call<List<Family>> getFamilyInfo(
            @Field("national_id") int national_id,
            @Field("password") String password,
            @Field("hospital_id") int hospital_id
    );

    @FormUrlEncoded
    @POST("pmedication")
    Call<List<Medication>> getMedicationInfo(
            @Field("national_id") int national_id,
            @Field("password") String password,
            @Field("hospital_id") int hospital_id
    );

    @FormUrlEncoded
    @POST("ptreatment")
    Call<List<Treatment>> getTreatmentInfo(
            @Field("national_id") int national_id,
            @Field("password") String password,
            @Field("hospital_id") int hospital_id
    );

    @FormUrlEncoded
    @POST("pshare")
    Call<Result> shareData(
            @Field("hospto") int hospto,
            @Field("hospfrom") int hospfrom,
            @Field("national_id") int national_id,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("analytics")
    Call<DataList> getAnalysisData(
            @Field("national_id") int national_id,
            @Field("password") String password,
            @Field("hospital_id") int hospital_id
    );

    @Headers("Content-type: application/json")
    @POST("predict_api")
    Call<MlPrediction> getMLPrediction(
           @Body JsonObject body
    );

}
