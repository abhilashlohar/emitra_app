package com.phppoets.grievance.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.phppoets.grievance.R;
import com.stephentuso.welcome.WelcomeHelper;

public class LoginActivity extends AppCompatActivity {
    EditText editUserName, editPassword;
    Button btnLogin;
    TextView txtForgot, txtSignUp;
    String username, password;
    private WelcomeHelper sampleWelcomeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sampleWelcomeScreen = new WelcomeHelper(this, StartActivity.class);
        sampleWelcomeScreen.show(savedInstanceState);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        editUserName = (EditText) findViewById(R.id.editTextUserName);
        editPassword = (EditText) findViewById(R.id.editTextPassword);
        txtForgot = (TextView) findViewById(R.id.txtForgot);
        txtSignUp = (TextView) findViewById(R.id.txtSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    finish();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
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
}
