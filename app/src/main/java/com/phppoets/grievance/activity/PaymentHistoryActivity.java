package com.phppoets.grievance.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.phppoets.grievance.R;
import com.phppoets.grievance.adapter.PaymentHistoryAdapter;

public class PaymentHistoryActivity extends AppCompatActivity {
    RecyclerView rvPaymentHistory;
    PaymentHistoryAdapter paymentHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);

    }
}
