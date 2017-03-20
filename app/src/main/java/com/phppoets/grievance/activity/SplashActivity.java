package com.phppoets.grievance.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.phppoets.grievance.R;
import com.phppoets.grievance.support.AppConfig;
import com.phppoets.grievance.support.UIUtils;
import com.phppoets.grievance.utility.TSTypeface;

/**
 * Created by user on 3/20/2017.
 */
public class SplashActivity extends AppCompatActivity
{
    Context context = SplashActivity.this;
    SharedPreferences preferences;
    Handler handler;
    Runnable noticeRunnable;
    TextView txtAppname;

    @Override
    protected void onCreate(
            @Nullable
            Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_splash);
        txtAppname = (TextView) findViewById(R.id.txtAppname);
        txtAppname.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));

        preferences = getSharedPreferences(AppConfig.KEY_PREFS_NAME, MODE_PRIVATE);
        handler = new Handler();
        noticeRunnable = new Runnable()
        {
            @Override
            public void run()
            {
                // TODO Auto-generated method stub
                if(isLoggedIn())
                {
                    startActivity(new Intent(context, HomeActivity.class));
                    finish();
                }
                else
                {
                    startActivity(new Intent(context, LoginActivity.class));
                    finish();
                }
            }
        };
        handler.postDelayed(noticeRunnable, 3000);
    }

    public boolean isLoggedIn()
    {
        return preferences.getBoolean(AppConfig.KEY_PREFS_ISLOGGED, false);
    }
}
