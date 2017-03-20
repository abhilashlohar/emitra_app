package com.phppoets.grievance.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

public class LoginActivity extends AppCompatActivity
{
    EditText editTextMobileNo, editPassword;
    Button btnLogin;
    TextView txtForgot, txtSignUp;
    String mobileNo, password, gcm_id;
    LoginResponse loginResponse;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    private WelcomeHelper sampleWelcomeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sampleWelcomeScreen = new WelcomeHelper(this, StartActivity.class);
        sampleWelcomeScreen.show(savedInstanceState);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        editTextMobileNo = (EditText) findViewById(R.id.editTextMobileNo);
        editPassword = (EditText) findViewById(R.id.editTextPassword);
        txtForgot = (TextView) findViewById(R.id.txtForgot);
        txtSignUp = (TextView) findViewById(R.id.txtSignUp);
        sharedPreferences = getSharedPreferences(AppConfig.KEY_PREFS_NAME, 0); // 0 - for private mode

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(isValid())
                {
                    gcm_id = FirebaseInstanceId.getInstance().getToken();
                    doLogin(mobileNo, password, gcm_id);
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        txtSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValid()
    {
        mobileNo = editTextMobileNo.getText().toString().trim();
        password = editPassword.getText().toString().trim();

        if(TextUtils.isEmpty(mobileNo))
        {
            showMessage("Enter name");
            return false;
        }
        if(password.equalsIgnoreCase(""))
        {
            showMessage("Enter password.");
            return false;
        }
        else
        {
            return true;
        }
    }

    private void showMessage(String msg)
    {
        Toast.makeText(LoginActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        sampleWelcomeScreen.onSaveInstanceState(outState);
    }

    public void doLogin(final String mobile, final String password, final String gcm_id)
    {
        /*dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Please wait ...");
        dialog.show();*/

        Call<LoginResponse> loginResponCall = RestClient.getClient().
                getLogin(mobile, password, gcm_id);
        loginResponCall.enqueue(new Callback<LoginResponse>()
        {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response)
            {
                Log.d("LoginActivity", "Status Code = " + response.code());
                progressBar.setVisibility(View.GONE);
                if(response.isSuccessful())
                {
                    // request successful (status code 200, 201)
                    // dialog.dismiss();
                    loginResponse = response.body();
                    if(loginResponse.getResult().getLoginStatus())
                    {
                        int userID = loginResponse.getResult().getUserData().getId();
                        String name = loginResponse.getResult().getUserData().getName();
                        sharedPreferences.edit()
                                         .putBoolean(AppConfig.KEY_PREFS_ISLOGGED, true)
                                         .putString(AppConfig.KEY_UNIQ_ID, String.valueOf(userID))
                                         .apply();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {

                        // String error = loginResponse.getError();
                        showMessage(getResources().getString(R.string.invalid_credential));
                    }
                }
                else
                {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    showMessage(getResources().getString(R.string.invalid_credential));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t)
            {
                showMessage(getResources().getString(R.string.invalid_credential));
            }
        });
    }
}
