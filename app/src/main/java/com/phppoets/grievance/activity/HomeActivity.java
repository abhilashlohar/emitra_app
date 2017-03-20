package com.phppoets.grievance.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.phppoets.grievance.R;
import com.phppoets.grievance.support.UIUtils;
import com.phppoets.grievance.utility.TSTypeface;

public class HomeActivity extends AppCompatActivity {
    ImageView imgNotification, imgMore;
    CardView cvPayment, cvGrievance;
    TextView txtGrievance, txtPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imgNotification = (ImageView) findViewById(R.id.imgNotification);
        imgMore = (ImageView) findViewById(R.id.imgMore);
        cvPayment = (CardView) findViewById(R.id.cvPayment);
        cvGrievance = (CardView) findViewById(R.id.cvGrievance);
        txtGrievance = (TextView) findViewById(R.id.txtGrievance);
        txtPayment = (TextView) findViewById(R.id.txtPayment);
        txtGrievance.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));
        txtPayment.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));
        imgNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, NotificationActivity.class));
            }
        });

        imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PdfActivity.class));
            }
        });
        cvGrievance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, GrievanceFormActivity.class));
            }
        });
        cvPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PaymentListActivity.class));
            }
        });
    }
}
