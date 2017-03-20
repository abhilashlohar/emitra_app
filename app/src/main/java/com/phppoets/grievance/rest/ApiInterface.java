package com.phppoets.grievance.rest;

import com.phppoets.grievance.model.notification.fetchdetail.FetchDetailResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface ApiInterface
{

    @GET("/fetchDetail")
    Call<FetchDetailResult> getLLogin(
            @Query("user_id")
            String userId,
            @Query("data")
            String data);

    //    @FormUrlEncoded
    //    @POST("/jainthela/app/api/login")
    //    Call<LoginResult> getLLogin(@Field("email") String email, @Field("password") String password);

    //    @FormUrlEncoded
    //    @POST("/notice/api/login")
    //    Call<LoginResponse> getLogin(@Field("enrollment_no") String enrollment_no, @Field("password") String password);
}