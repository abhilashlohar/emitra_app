package com.phppoets.grievance.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.phppoets.grievance.R;

public class GrievanceHistoryDetailActivity extends AppCompatActivity {
    RecyclerView rvMyGrievanceDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grievance_history_detail);
        rvMyGrievanceDetail = (RecyclerView) findViewById(R.id.rvMyGrievanceDetail);
    }
}
