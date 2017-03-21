package com.phppoets.grievance.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.phppoets.grievance.R;
import com.phppoets.grievance.adapter.PaymentHistoryAdapter;
import com.phppoets.grievance.model.paymentHistory.PaymentHistoryResponse;
import com.phppoets.grievance.rest.RestClient;
import com.phppoets.grievance.utility.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentHistoryActivity extends AppCompatActivity {
    RecyclerView rvPaymentHistory;
    PaymentHistoryAdapter paymentHistoryAdapter;
    ProgressBar progressBar;
    ImageView imageViewBack;
    PaymentHistoryResponse paymentHistoryResponse;

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
        getPaymentHistory(Utils.getUserId(this));
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void getPaymentHistory(final String login_id) {
        Call<PaymentHistoryResponse> loginResponCall = RestClient.getClient().
                getPaymentHistory(login_id);
        loginResponCall.enqueue(new Callback<PaymentHistoryResponse>() {
            @Override
            public void onResponse(Call<PaymentHistoryResponse> call, Response<PaymentHistoryResponse> response) {
                Log.d("LoginActivity", "Status Code = " + response.code());
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    // request successful (status code 200, 201)
                    // dialog.dismiss();
                    paymentHistoryResponse = response.body();
                    paymentHistoryAdapter = new PaymentHistoryAdapter(PaymentHistoryActivity.this, paymentHistoryResponse.getResult());
                    // paymentHistoryAdapter.setClickListener(PaymentHistoryActivity.this);
                    rvPaymentHistory.setAdapter(paymentHistoryAdapter);
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    showMessage(getResources().getString(R.string.invalid_credential));
                }
            }

            @Override
            public void onFailure(Call<PaymentHistoryResponse> call, Throwable t) {
                showMessage(getResources().getString(R.string.invalid_credential));
            }
        });
    }

    public void showMessage(String msg) {
        Toast.makeText(PaymentHistoryActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
