package com.phppoets.grievance.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.phppoets.grievance.R;

public class SignUpActivity extends AppCompatActivity {
    EditText editName, editMobile, editEmail, editLocation, editPass, editConfirmPass;
    Button btnCreateAccount;
    String username, mobileNo, email, location, password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnCreateAccount = (Button) findViewById(R.id.btn_account);
        editName = (EditText) findViewById(R.id.editName);
        editMobile = (EditText) findViewById(R.id.editMobile);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editLocation = (EditText) findViewById(R.id.editLocation);
        editPass = (EditText) findViewById(R.id.editCreatePass);
        editConfirmPass = (EditText) findViewById(R.id.editConfirmPass);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    finish();
                    Intent intent = new Intent(SignUpActivity.this, TestActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    private boolean isValid() {
        username = editName.getText().toString().trim();
        mobileNo = editMobile.getText().toString().trim();
        email = editEmail.getText().toString().trim();
        location = editLocation.getText().toString().trim();
        password = editPass.getText().toString().trim();
        confirmPassword = editConfirmPass.getText().toString().trim();


        String pattern = "^[A-Za-z _]*$";
        if (username.equalsIgnoreCase("")) {
            showMessage("Enter name");
            return false;
        }
        if (!username.matches(pattern)) {
            showMessage("invalid pattern of name");
            return false;
        }
        if (mobileNo.equalsIgnoreCase("")) {
            showMessage("Enter Mobile number");
            return false;
        }
        if (mobileNo.length() != 10) {
            showMessage("Invalid number");
            return false;
        }
        if (email.equalsIgnoreCase("")) {
            showMessage("Enter email");
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
                editEmail.getText().toString()).matches()) {
            showMessage("Invalid Email address.");
            return false;
        }

        if (location.equalsIgnoreCase("")) {
            showMessage("Enter location");
            return false;
        }
        if (password.equalsIgnoreCase("")) {
            showMessage("Enter password.");
            return false;

        }
        if (!password.equals(confirmPassword)) {
            showMessage("Password does not match ");
            return false;
        } else {
            return true;
        }

    }

    private void showMessage(String msg) {
        Toast.makeText(SignUpActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
    }
}
