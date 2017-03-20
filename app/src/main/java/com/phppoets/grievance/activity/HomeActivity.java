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
    CardView cvPaymentService, cvPaymentHistroy, cvGrievanceServices, cvGrievanceHistroy;
    TextView txtGrievanceServices, txtPaymentService, txtPaymentHistory, txtGrievanceHistroy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imgNotification = (ImageView) findViewById(R.id.imgNotification);
        imgMore = (ImageView) findViewById(R.id.imgMore);
        cvPaymentService = (CardView) findViewById(R.id.cvPaymentService);
        cvGrievanceServices = (CardView) findViewById(R.id.cvGrievanceServices);
        cvPaymentHistroy = (CardView) findViewById(R.id.cvPaymentHistroy);
        cvGrievanceHistroy = (CardView) findViewById(R.id.cvGrievanceHistroy);
        txtGrievanceServices = (TextView) findViewById(R.id.txtGrievanceServices);
        txtPaymentService = (TextView) findViewById(R.id.txtPaymentService);
        txtPaymentHistory = (TextView) findViewById(R.id.txtPaymentHistory);
        txtGrievanceHistroy = (TextView) findViewById(R.id.txtGrievanceHistroy);

        txtGrievanceServices.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));
        txtPaymentService.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));
        txtPaymentHistory.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));
        txtGrievanceHistroy.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));
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
        cvGrievanceServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, GrievanceFormActivity.class));
            }
        });
        cvPaymentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PaymentListActivity.class));
            }
        });

        cvGrievanceHistroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        cvPaymentHistroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
