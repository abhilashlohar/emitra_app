package com.phppoets.grievance.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.phppoets.grievance.R;
import com.phppoets.grievance.adapter.NotificationAdapter;
import com.phppoets.grievance.model.notification.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity implements NotificationAdapter.ClickListener {
    RecyclerView recyclerView;
    NotificationAdapter notificationAdapter;
    List<Notification> notificationList;
    String title[] = {"Emitra", "Bhamashah", "Block Chain"};
    String description[] = {"abcdef ghijkl mnop", "abcdef ghijkl mnop", "abcdef ghijkl mnop"};
    String timeStamp[] = {"monday,22,2017", "tuesday,23,2017", "wednesday,24,2017"};
    Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        notificationList = new ArrayList<Notification>();
        for (int i = 0; i < timeStamp.length; i++) {
            notification = new Notification(title[i].toString(), description[i].toString(), timeStamp[i].toString());
            notificationList.add(notification);
        }
        notificationAdapter = new NotificationAdapter(NotificationActivity.this, notificationList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        notificationAdapter.setClickListener(this);
        recyclerView.setAdapter(notificationAdapter);
    }

    @Override
    public void ItemClicked(View view, int position, String Tag) {
        switch (view.getId()) {
            case R.id.rlMain:
                Toast.makeText(NotificationActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
