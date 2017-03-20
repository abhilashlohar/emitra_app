package com.phppoets.grievance.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.phppoets.grievance.R;
import com.phppoets.grievance.model.login.LoginResponse;
import com.phppoets.grievance.rest.RestClient;
import com.phppoets.grievance.support.AppConfig;
import com.stephentuso.welcome.WelcomeHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText editUserName, editPassword;
    Button btnLogin;
    TextView txtForgot, txtSignUp;
    String username, password, gcm_id;
    LoginResponse loginResponse;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    private WelcomeHelper sampleWelcomeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sampleWelcomeScreen = new WelcomeHelper(this, StartActivity.class);
        sampleWelcomeScreen.show(savedInstanceState);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        editUserName = (EditText) findViewById(R.id.editTextUserName);
        editPassword = (EditText) findViewById(R.id.editTextPassword);
        txtForgot = (TextView) findViewById(R.id.txtForgot);
        txtSignUp = (TextView) findViewById(R.id.txtSignUp);
        sharedPreferences = getSharedPreferences(AppConfig.KEY_PREFS_NAME, 0); // 0 - for private mode

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    gcm_id = FirebaseInstanceId.getInstance().getToken();
                    doLogin(username, password, gcm_id);
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);


            }
        });
    }

    private boolean isValid() {
        username = editUserName.getText().toString().trim();
        password = editPassword.getText().toString().trim();

        String pattern = "^[A-Za-z _]*$";
        if (username.equalsIgnoreCase("")) {
            showMessage("Enter name");
            return false;
        }
        if (!username.matches(pattern)) {
            showMessage("invalid pattern of name");
            return false;
        }
        if (password.equalsIgnoreCase("")) {
            showMessage("Enter password.");
            return false;
        } else
            return true;


    }

    private void showMessage(String msg) {
        Toast.makeText(LoginActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        sampleWelcomeScreen.onSaveInstanceState(outState);
    }

    public void doLogin(final String username, final String password, final String gcm_id) {
        /*dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Please wait ...");
        dialog.show();*/

        Call<LoginResponse> loginResponCall = RestClient.getClient().
                getLogin(username, password, gcm_id);
        loginResponCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d("LoginActivity", "Status Code = " + response.code());
                if (response.isSuccessful()) {
                    // request successful (status code 200, 201)
                    // dialog.dismiss();
                    progressBar.setVisibility(View.GONE);
                    loginResponse = response.body();
                    if (loginResponse.getResult().getLoginStatus()) {
                        int userID = loginResponse.getResult().getUserData().getId();
                        String name = loginResponse.getResult().getUserData().getName();
                        sharedPreferences.edit().putBoolean(AppConfig.KEY_PREFS_ISLOGGED, true)
                                .putString(AppConfig.KEY_UNIQ_ID, String.valueOf(userID))
                                .apply();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                        /*String studentName = loginResponse.getLogin().getUsername();
                        String studentImage = loginResponse.getLogin().getUserimage();
                        String uniq_id = loginResponse.getLogin().getUniq_id();
                        String about_us = loginResponse.getLogin().getAboutUs();
*/
                       /* StudentDetails studentDetails = new StudentDetails();
                        studentDetails.setStudentImage(studentImage);
                        studentDetails.setStudentName(studentName);
                        studentDetails.setUserID(userID);
                        studentDetails.setEnrollment_no(enrollment_no);
                        studentDetails.setAboutUs(about_us);*/

//                        sharedPreferences.edit().putString(AppConfig.KEY_ID,id)
//                                .putString(AppConfig.KEY_NAME,name)
//                                .putString(AppConfig.KEY_ADDRESS,address)
//                                .putString(AppConfig.KEY_BLOOD_GROUP,blood_group)
//                                .putString(AppConfig.KEY_EMAIL,email)
//                                .putString(AppConfig.KEY_MOBILE,mobile)
//                                .putString(AppConfig.KEY_ISLOGGEDIN,isloggedin).apply();
                      /*  Gson gson = new Gson();
                        sharedPreferences.edit().putBoolean(AppConfig.KEY_PREFS_ISLOGGED, true)
                                .putString(AppConfig.KEY_UNIQ_ID, uniq_id)
                                .putString(AppConfig.KEY_STUDENT_DATA, gson.toJson(studentDetails))
                                .putString(AppConfig.KEY_USER_ID, userID)
                                .apply();

                        fcmDeviceRegistration(deviceToken, userID);*/
                       /* finish();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);*/


                    } else {
                        // String error = loginResponse.getError();
                        //  showMessage(error);
                    }
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }

}
