package com.phppoets.grievance.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.phppoets.grievance.R;
import com.phppoets.grievance.adapter.PaymentHistoryAdapter;

public class PaymentHistoryActivity extends AppCompatActivity {
    RecyclerView rvPaymentHistory;
    PaymentHistoryAdapter paymentHistoryAdapter;
    ProgressBar progressBar;
    ImageView imageViewBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);
        rvPaymentHistory = (RecyclerView) findViewById(R.id.rvPaymentHistory);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        // paymentList = new ArrayList<PaymentService>();
        imageViewBack = (ImageView) findViewById(R.id.imageViewBack);
        //rvPaymentDetail = (RecyclerView) findViewById(R.id.rvPaymentDetail);
        rvPaymentHistory.setHasFixedSize(true);
        rvPaymentHistory.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvPaymentHistory.setLayoutManager((mLayoutManager));
    }
}
