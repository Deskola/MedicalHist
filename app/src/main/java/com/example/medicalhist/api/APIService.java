package com.example.medicalhist.api;

import com.example.medicalhist.model.Hospital;

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
    @FormUrlEncoded
    @POST("patient")
    Call<List<Hospital>> getHospitals(@Field("national_id") int national_id);

}
