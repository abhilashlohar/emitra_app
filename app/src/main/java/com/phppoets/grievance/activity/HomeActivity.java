package com.phppoets.grievance.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.phppoets.grievance.R;

public class HomeActivity extends AppCompatActivity {
    ImageView imgNotification, imgMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imgNotification = (ImageView) findViewById(R.id.imgNotification);
        imgMore = (ImageView) findViewById(R.id.imgMore);

        imgNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, NotificationActivity.class));
            }
        });
    }
}
