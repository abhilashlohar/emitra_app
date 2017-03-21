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
import com.phppoets.grievance.adapter.NotificationAdapter;
import com.phppoets.grievance.model.notification.Notification;
import com.phppoets.grievance.model.notification.NotificationResponse;
import com.phppoets.grievance.rest.RestClient;
import com.phppoets.grievance.utility.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity implements NotificationAdapter.ClickListener {
    RecyclerView recyclerView;
    NotificationAdapter notificationAdapter;
    List<Notification> notificationList;
    String title[] = {"Emitra", "Bhamashah", "Block Chain"};
    String description[] = {"abcdef ghijkl mnop", "abcdef ghijkl mnop", "abcdef ghijkl mnop"};
    String timeStamp[] = {"monday,22,2017", "tuesday,23,2017", "wednesday,24,2017"};
    Notification notification;
    NotificationResponse notificationResponse;
    ProgressBar progressBar;
    ImageView imageViewBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        imageViewBack = (ImageView) findViewById(R.id.imageViewBack);
      /*  notificationList = new ArrayList<Notification>();
        for (int i = 0; i < timeStamp.length; i++) {
            notification = new Notification(title[i].toString(), description[i].toString(), timeStamp[i].toString());
            notificationList.add(notification);
        }*/
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getNotification(Utils.getUserId(this));

    }

    public void getNotification(final String login_id) {
        Call<NotificationResponse> loginResponCall = RestClient.getClient().
                getNotification(login_id);
        loginResponCall.enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                Log.d("LoginActivity", "Status Code = " + response.code());
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    // request successful (status code 200, 201)
                    // dialog.dismiss();
                    notificationResponse = response.body();
                    notificationAdapter = new NotificationAdapter(NotificationActivity.this, notificationResponse.getResult());
                    notificationAdapter.setClickListener(NotificationActivity.this);
                    recyclerView.setAdapter(notificationAdapter);
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    showMessage(getResources().getString(R.string.invalid_credential));
                }
            }

            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {
                showMessage(getResources().getString(R.string.invalid_credential));
            }
        });
    }

    private void showMessage(String msg) {
        Toast.makeText(NotificationActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ItemClicked(View view, int position, String Tag) {
        switch (view.getId()) {
            case R.id.rlMain:
                // Toast.makeText(NotificationActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
