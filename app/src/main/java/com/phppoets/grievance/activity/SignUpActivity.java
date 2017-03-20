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
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.phppoets.grievance.R;
import com.phppoets.grievance.model.base.BaseClass;
import com.phppoets.grievance.rest.RestClient;
import com.phppoets.grievance.support.AppConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity
{
    EditText editName, editMobile, editEmail, editLocation, editPass, editConfirmPass;
    Button btnCreateAccount;
    String username, mobileNo, email, location, password, confirmPassword;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(AppConfig.KEY_PREFS_NAME, 0);
        setContentView(R.layout.activity_sign_up);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnCreateAccount = (Button) findViewById(R.id.btn_account);
        editName = (EditText) findViewById(R.id.editName);
        editMobile = (EditText) findViewById(R.id.editMobile);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editLocation = (EditText) findViewById(R.id.editLocation);
        editPass = (EditText) findViewById(R.id.editCreatePass);
        editConfirmPass = (EditText) findViewById(R.id.editConfirmPass);
        btnCreateAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(isValid())
                {
                    Call<BaseClass> loginResponCall = RestClient.getClient().
                            getSignUp(editName.getText().toString(), editPass.getText().toString(), editEmail.getText().toString(),
                                      editLocation.getText().toString(), FirebaseInstanceId.getInstance().getToken(),
                                      editMobile.getText().toString());
                    loginResponCall.enqueue(new Callback<BaseClass>()
                    {
                        @Override
                        public void onResponse(Call<BaseClass> call, Response<BaseClass> response)
                        {
                            Log.d("LoginActivity", "Status Code = " + response.code());
                            if(response.isSuccessful())
                            {
                                // request successful (status code 200, 201)
                                // dialog.dismiss();
                                progressBar.setVisibility(View.GONE);
                                if(response.body().getResult().isStatus())
                                {
                                    String userID = response.body().getResult().getUserId();

                                    sharedPreferences.edit()
                                                     .putBoolean(AppConfig.KEY_PREFS_ISLOGGED, true)
                                                     .putString(AppConfig.KEY_UNIQ_ID, userID)
                                                     .apply();
                                    Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                            else
                            {
                                // response received but request not successful (like 400,401,403 etc)
                                //Handle errors

                            }
                        }

                        @Override
                        public void onFailure(Call<BaseClass> call, Throwable t)
                        {

                        }
                    });
                }
            }
        });
    }

    private boolean isValid()
    {
        username = editName.getText().toString().trim();
        mobileNo = editMobile.getText().toString().trim();
        email = editEmail.getText().toString().trim();
        location = editLocation.getText().toString().trim();
        password = editPass.getText().toString().trim();
        confirmPassword = editConfirmPass.getText().toString().trim();

        String pattern = "^[A-Za-z _]*$";
        if(username.equalsIgnoreCase(""))
        {
            showMessage("Enter name");
            return false;
        }
        if(!username.matches(pattern))
        {
            showMessage("invalid pattern of name");
            return false;
        }
        if(mobileNo.equalsIgnoreCase(""))
        {
            showMessage("Enter Mobile number");
            return false;
        }
        if(mobileNo.length() != 10)
        {
            showMessage("Invalid number");
            return false;
        }
        if(email.equalsIgnoreCase(""))
        {
            showMessage("Enter email");
            return false;
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(editEmail.getText().toString()).matches())
        {
            showMessage("Invalid Email address.");
            return false;
        }

        if(location.equalsIgnoreCase(""))
        {
            showMessage("Enter location");
            return false;
        }
        if(password.equalsIgnoreCase(""))
        {
            showMessage("Enter password.");
            return false;
        }
        if(!password.equals(confirmPassword))
        {
            showMessage("Password does not match ");
            return false;
        }
        else
        {
            return true;
        }
    }

    private void showMessage(String msg)
    {
        Toast.makeText(SignUpActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
    }
}
