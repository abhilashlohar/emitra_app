package com.phppoets.grievance.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.phppoets.grievance.R;
import com.phppoets.grievance.adapter.GrievanceHIstoryDetailAdapter;
import com.phppoets.grievance.model.grievanceHistoryDetail.GrievanceHIstoryDetailREsponse;
import com.phppoets.grievance.rest.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GrievanceHistoryDetailActivity extends AppCompatActivity {
    RecyclerView rvMyGrievanceDetail;
    GrievanceHIstoryDetailAdapter grievanceHIstoryDetailAdapter;
    GrievanceHIstoryDetailREsponse grievanceHIstoryDetailREsponse;
    String grievance_id;
    ImageView imageViewBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grievance_history_detail);
        imageViewBack = (ImageView) findViewById(R.id.imageViewBack);
        rvMyGrievanceDetail = (RecyclerView) findViewById(R.id.rvMyGrievanceDetail);
        if (getIntent().hasExtra("id")) {
            grievance_id = String.valueOf(getIntent().getIntExtra("id", 0));
        }
        getGrievanceHistoryDetail("1");
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void getGrievanceHistoryDetail(final String grievance_id) {
        /*dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Please wait ...");
        dialog.show();*/

        Call<GrievanceHIstoryDetailREsponse> loginResponCall = RestClient.getClient().
                getGrievanceHistoryDetail(grievance_id);
        loginResponCall.enqueue(new Callback<GrievanceHIstoryDetailREsponse>() {
            @Override
            public void onResponse(Call<GrievanceHIstoryDetailREsponse> call, Response<GrievanceHIstoryDetailREsponse> response) {
                Log.d("LoginActivity", "Status Code = " + response.code());

                if (response.isSuccessful()) {
                    // request successful (status code 200, 201)
                    // dialog.dismiss();
                    grievanceHIstoryDetailREsponse = response.body();
                    grievanceHIstoryDetailAdapter = new GrievanceHIstoryDetailAdapter(GrievanceHistoryDetailActivity.this, grievanceHIstoryDetailREsponse.getResult());
                    rvMyGrievanceDetail.setAdapter(grievanceHIstoryDetailAdapter);
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    showMessage(getResources().getString(R.string.invalid_credential));
                }
            }

            @Override
            public void onFailure(Call<GrievanceHIstoryDetailREsponse> call, Throwable t) {
                showMessage(getResources().getString(R.string.invalid_credential));
            }
        });
    }

    public void showMessage(String msg) {
        Toast.makeText(GrievanceHistoryDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
