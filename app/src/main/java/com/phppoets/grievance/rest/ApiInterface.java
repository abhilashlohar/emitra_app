package com.phppoets.grievance.rest;

import com.phppoets.grievance.model.login.LoginResponse;
import com.phppoets.grievance.model.notification.fetchdetail.FetchDetailResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
public interface ApiInterface
{
    @Headers("Accept:application/json")
    @GET("grievance/fetchDetail")
    Call<FetchDetailResult> getFatchDetail(
            @Query("user_id")
            String userId,
            @Query("data")
            String data);

    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("/grievance/Logins/login")
    Call<LoginResponse> getLogin(@Field("username") String username, @Field("password") String password, @Field("gcm_id") String gcm_id);

    //    @FormUrlEncoded
    //    @POST("/jainthela/app/api/login")
    //    Call<LoginResult> getLLogin(@Field("email") String email, @Field("password") String password);

    //    @FormUrlEncoded
    //    @POST("/notice/api/login")
    //    Call<LoginResponse> getLogin(@Field("enrollment_no") String enrollment_no, @Field("password") String password);
}