package com.phppoets.grievance.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.phppoets.grievance.R;
import com.phppoets.grievance.adapter.PaymentDetailAdapter;
import com.phppoets.grievance.model.payment.PaymentService;

import java.util.ArrayList;
import java.util.List;

public class PaymentListActivity extends AppCompatActivity
{
    RecyclerView rvPaymentDetail;
    // String payments[] = {"a", "b", "c", "d", "e", "f", "g"};
    String serviceName[] = {"Airtel Mobile Bill Postpaid", "Idea Mobile Postpaid Bill", "Idea Mobile Postpaid Bill", "Vodafone Mobile Postpaid Bill", "Phed Water Bill", "BSNL Mobile Postpaid Bill"};
    String serviceId[] = {"1214", "1220", "1216", "1219", "1807", "1222"};
    String sampleDataDec[] = {"{\"SRVID\":\"1214\",\"searchKey\":\"9352423664\",\"SSOID\":\"SSOTESTKIOSK\"}", "{\"SRVID\":\"1220\",\"searchKey\":\"8440042182\",\"SSOID\":\"SSOTESTKIOSK\"}",
            "{\"SRVID\":\"1216\",\"searchKey\":\"8432926694\",\"SSOID\":\"SSOTESTKIOSK\"}", "{\"SRVID\":\"1219\",\"searchKey\":\"998234559\",\"SSOID\":\"SSOTESTKIOSK\"}"
            , "{\"SRVID\":\"1807\",\"searchKey\":\"16-14062- 04535\",\"SSOID\":\"SSOTESTKIOSK\"}", "{\"SRVID\":\"1222\",\"searchKey\":\"9530084675\",\"SSOID\":\"SSOTESTKIOSK\"}"};
    List<PaymentService> paymentList;
    PaymentDetailAdapter paymentDetailAdapter;
    PaymentService paymentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_detail);
        paymentList = new ArrayList<PaymentService>();
        rvPaymentDetail = (RecyclerView) findViewById(R.id.rvPaymentDetail);
        rvPaymentDetail.setHasFixedSize(true);
        rvPaymentDetail.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvPaymentDetail.setLayoutManager((mLayoutManager));
        for (int i = 0; i < serviceName.length; i++) {
            // paymentList.add(payments[i].toString());
            paymentService = new PaymentService(serviceName[i].toString(), serviceId[i].toString(), sampleDataDec[i].toString());
            paymentList.add(paymentService);
        }
        paymentDetailAdapter = new PaymentDetailAdapter(PaymentListActivity.this, paymentList);
        rvPaymentDetail.setAdapter(paymentDetailAdapter);

    }
}
