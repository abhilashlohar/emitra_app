package com.phppoets.grievance.service;


import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by vaibhav on 10/1/2016.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();
    SharedPreferences preferences, preference;

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "token no" + refreshedToken);

        //fcmDeviceRegistration(refreshedToken, Utils.getUserId(getApplicationContext()));
        // sending reg id to your server
      /*  if(TextUtils.isEmpty(refreshedToken)){
            sendRegistrationToServer(getTokenFromPrefs());
        }
        else
        sendRegistrationToServer(refreshedToken);*/

    }

    public void fcmDeviceRegistration(String device_token, String id) {
       /* Call<FCMDeviceRegistrationResponse> fcmDeviceRegistrationResponseCall = RestClient.getClient().
                getMessage(device_token, id);
        fcmDeviceRegistrationResponseCall.enqueue(new Callback<FCMDeviceRegistrationResponse>() {
            @Override
            public void onResponse(Call<FCMDeviceRegistrationResponse> call, Response<FCMDeviceRegistrationResponse> response) {
                Log.d("LoginActivity", "Status Code = " + response.code());
                if (response.isSuccessful()) {

                }
            }

            @Override
            public void onFailure(Call<FCMDeviceRegistrationResponse> call, Throwable t) {
            }
        });
    */
    }
//    private void sendRegistrationToServer(final String token) {
//        // sending gcm token to server
//        Log.e(TAG, "sendRegistrationToServer: " + token);
//        String tag_string_req = "req_login";
//
//
//        StringRequest strReq = new StringRequest(Request.Method.POST,
//                "http://getlogo.in/jainthela/app/api/push_token_update", new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//
//                Log.d(TAG, "Response: " + response.toString());
//
//                //Log.i(TAG, "URL " + AppConfig.url_login);
//                try {
//
//                    JSONObject jsonObject = new JSONObject(response);
//                    boolean status = jsonObject.getBoolean("status");
//                    //String Error = jsonObject.getString("Error");
//                    if (status) {
//                       // JSONObject Responce = jsonObject.getJSONObject("Responce");
//
//                    } else {
//                        String Error = jsonObject.getString("Error");
//
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "Error: " + error.getMessage());
//              /*  Toast.makeText(MyFirebaseInstanceIDService.this,
//                        "There is No Order" ,
//                        Toast.LENGTH_LONG).show();*/
//
//            }
//        }) {
//
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("id", getUserIdFromPrefs());
//                params.put("token",token);
//                Log.d("params","  "+params);
//                return params;
//            }
//
//        };
//        strReq.setRetryPolicy(new RetryPolicy() {
//
//            @Override
//            public void retry(VolleyError arg0) throws VolleyError {
//                // TODO Auto-generated method stub
//                Log.e("", "RE-TRY -: " + arg0);
//            }
//
//            @Override
//            public int getCurrentTimeout() {
//                // TODO Auto-generated method stub
//                return 0;
//            }
//
//            @Override
//            public int getCurrentRetryCount() {
//                // TODO Auto-generated method stub
//                return 0;
//            }
//        });
//        strReq.setShouldCache(false);
//        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
//    }


}

