package com.phppoets.grievance.rest;

import com.phppoets.grievance.model.addForm.AddGrievance;
import com.phppoets.grievance.model.addImage.AddImage;
import com.phppoets.grievance.model.base.BaseClass;
import com.phppoets.grievance.model.department.Department;
import com.phppoets.grievance.model.grievanceHistory.GrievanceHIstoryREsponse;
import com.phppoets.grievance.model.grievanceHistoryDetail.GrievanceHIstoryDetailREsponse;
import com.phppoets.grievance.model.login.LoginResponse;
import com.phppoets.grievance.model.makepayment.MakePaymentRequestData;
import com.phppoets.grievance.model.notification.NotificationResponse;
import com.phppoets.grievance.model.notification.fetchdetail.FetchDetailResult;
import com.phppoets.grievance.model.paymentHistory.PaymentHistoryResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ApiInterface {
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
    Call<LoginResponse> getLogin(
            @Field("mobile")
            String mobile,
            @Field("password")
            String password,
            @Field("gcm_id")
            String gcm_id);

    @Headers("Accept:application/json")
    @GET("grievance/fetchDetailMakePayment")
    Call<MakePaymentRequestData> makePayment(
            @Query("user_id")
            String userId,
            @Query("data")
            String data,
            @Query("make_payment")
            boolean makePayment);

    @Headers("Accept:application/json")
    @GET("/grievance/Logins/updateGcm")
    Call<BaseClass> updateGCM(
            @Query("user_id")
            String userId,
            @Query("gcm_id")
            String gcmId);

    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("grievance/Logins/signup")
    Call<BaseClass> getSignUp(
            @Field("name")
            String name,
            @Field("password")
            String password,
            @Field("email")
            String email,
            @Field("address")
            String address,
            @Field("gcm_id")
            String gcmId,
            @Field("mobile")
            String mobile);


    @Headers("Accept:application/json")
    @GET("grievance/grievances/listDepartment")
    Call<Department> getDepartment(
    );

    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("grievance/grievances/addGrievance")
    Call<AddGrievance> addGrievance(@Field("subject")
                                    String subject,
                                    @Field("description")
                                    String description,
                                    @Field("department_id")
                                    String department_id, @Field("login_id")
                                    String login_id);

    @Headers("Accept:application/json")
    @Multipart
    @POST("grievance/grievances/addFile")
    Call<AddImage> addImage(@PartMap Map<String, RequestBody> params);

    @Headers("Accept:application/json")
    @GET("grievance/grievances/userGrievances")
    Call<GrievanceHIstoryREsponse> getGrievanceHistory(
            @Query("login_id")
            String login_id
    );

    @Headers("Accept:application/json")
    @GET("grievance/grievances/grievanceHistory")
    Call<GrievanceHIstoryDetailREsponse> getGrievanceHistoryDetail(
            @Query("grievance_id")
            String grievance_id
    );

    @Headers("Accept:application/json")
    @GET("grievance/grievances/paymentDetails")
    Call<PaymentHistoryResponse> getPaymentHistory(
            @Query("login_id")
            String login_id
    );

    @Headers("Accept:application/json")
    @GET("grievance/grievances/notifications")
    Call<NotificationResponse> getNotification(
            @Query("login_id")
            String login_id
    );

    //    @FormUrlEncoded
    //    @POST("/jainthela/app/api/login")
    //    Call<LoginResult> getLLogin(@Field("email") String email, @Field("password") String password);

    //    @FormUrlEncoded
    //    @POST("/notice/api/login")
    //    Call<LoginResponse> getLogin(@Field("enrollment_no") String enrollment_no, @Field("password") String password);
}