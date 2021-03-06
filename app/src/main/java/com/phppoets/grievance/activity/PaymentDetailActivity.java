package com.phppoets.grievance.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.phppoets.grievance.R;
import com.phppoets.grievance.adapter.BillDetailAdapter;
import com.phppoets.grievance.model.notification.fetchdetail.BillDetail;
import com.phppoets.grievance.model.notification.fetchdetail.FetchDetailResult;
import com.phppoets.grievance.model.notification.fetchdetail.Result;
import com.phppoets.grievance.model.notification.fetchdetail.TransactionDetails;
import com.phppoets.grievance.rest.RestClient;
import com.phppoets.grievance.support.UIUtils;
import com.phppoets.grievance.utility.TSTypeface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 3/20/2017.
 */
public class PaymentDetailActivity extends AppCompatActivity
{
    FetchDetailResult fetchDetailResult;
    Result result;
    TransactionDetails transactionDetails;
    String id, data;
    TextView txtPNRNo, txtAmountShow, txtUsername, txtMobile, txtPurpose, txtTimestamp, txtEmail;
    RecyclerView rvPaymentDetail;
    BillDetailAdapter billDetailAdapter;
    BillDetail billDetail;
    List<BillDetail> billDetailList;
    CardView card_view;
    Button makePayment;
    TextView txtNoRecordsFound;
    ProgressBar progressBar;
    ImageView imageViewBack;

    @Override
    protected void onCreate(
            @Nullable
            Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_payment_status);
        card_view = (CardView) findViewById(R.id.card_view);
        txtPNRNo = (TextView) findViewById(R.id.txtPNRNo);
        txtAmountShow = (TextView) findViewById(R.id.txtAmountShow);
        txtUsername = (TextView) findViewById(R.id.txtUsername);
        txtMobile = (TextView) findViewById(R.id.txtMobile);
        txtPurpose = (TextView) findViewById(R.id.txtPurpose);
        txtTimestamp = (TextView) findViewById(R.id.txtTimestamp);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        makePayment = (Button) findViewById(R.id.makePayment);
        txtNoRecordsFound = (TextView) findViewById(R.id.txtNoRecordsFound);
        imageViewBack = (ImageView) findViewById(R.id.imageViewBack);
        txtAmountShow.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));

        txtUsername.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));
        txtMobile.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));
        txtPurpose.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));
        txtEmail.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));

        makePayment.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));

        txtNoRecordsFound.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        makePayment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(PaymentDetailActivity.this, WebActivity.class);
                intent.putExtra("data", data);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
            }
        });
        rvPaymentDetail = (RecyclerView) findViewById(R.id.rvPaymentDetail);
        rvPaymentDetail.setHasFixedSize(true);
        rvPaymentDetail.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvPaymentDetail.setLayoutManager((mLayoutManager));
        billDetail = new BillDetail();
        if(getIntent().hasExtra("id"))
        {
            id = getIntent().getStringExtra("id");
            data = getIntent().getStringExtra("data");
        }
        imageViewBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
        fetchDetail(id, data);
    }

    public void fetchDetail(final String id, final String data)
    {
        /*dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Please wait ...");
        dialog.show();*/

        Call<FetchDetailResult> loginResponCall = RestClient.getClient().
                getFatchDetail(id, data);
        loginResponCall.enqueue(new Callback<FetchDetailResult>()
        {
            @Override
            public void onResponse(Call<FetchDetailResult> call, Response<FetchDetailResult> response)
            {
                Log.d("LoginActivity", "Status Code = " + response.code());
                if(response.isSuccessful())
                {
                    // request successful (status code 200, 201)
                    // dialog.dismiss();
                    progressBar.setVisibility(View.GONE);
                    fetchDetailResult = response.body();

                    //Log.d("LoginActivity", "Status Code = " + fetchDetailResult.getResult().getFetchDetails().getBillDetails().toString());
                    result = fetchDetailResult.getResult();
                    if(result != null)
                    {
                        if(fetchDetailResult.getResult().getFetchDetails().getTransactionDetails() != null &&
                                fetchDetailResult.getResult().getFetchDetails().getTransactionDetails().getBillAmount() != null)
                        {
                            card_view.setVisibility(View.VISIBLE);
                            makePayment.setVisibility(View.VISIBLE);
                            transactionDetails = fetchDetailResult.getResult().getFetchDetails().getTransactionDetails();
                            txtPNRNo.setText(transactionDetails.getLookUpId());
                            txtAmountShow.setText(transactionDetails.getBillAmount());
                            //txtEmail.setText("vaibhav@gmail.com");
                            txtMobile.setText(transactionDetails.getConsumerKeysValues());
                            txtUsername.setText(transactionDetails.getConsumerName());
                            txtPurpose.setText(transactionDetails.getServiceName());
                            if(fetchDetailResult.getResult().getFetchDetails().getBillDetails() != null)
                            {

                                billDetailList = fetchDetailResult.getResult().getFetchDetails().getBillDetails();
                                billDetailAdapter = new BillDetailAdapter(PaymentDetailActivity.this, billDetailList);
                                rvPaymentDetail.setAdapter(billDetailAdapter);
                            }
                            else
                            {

                            }
                        }
                        else
                        {
                            // response received but request not successful (like 400,401,403 etc)
                            //Handle error
                            txtNoRecordsFound.setVisibility(View.VISIBLE);
                            card_view.setVisibility(View.GONE);
                            Log.d("PaymentDetailActivity", "Error Code = " + "errors");
                        }
                    }
                    else
                    {
                        txtNoRecordsFound.setVisibility(View.VISIBLE);
                        card_view.setVisibility(View.GONE);
                        //UIUtils.showOkAlertDialog(PaymentDetailActivity.this,getString(R.string.app_name),getString(R.string.noDataFound));
                    }
                }
            }

            @Override
            public void onFailure(Call<FetchDetailResult> call, Throwable t)
            {
                Log.d("PaymentDetailActivity", "Throwable = " + t.toString());
            }
        });
    }
}
