package com.phppoets.grievance.activity;

import android.content.SharedPreferences;
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
import com.phppoets.grievance.adapter.GrievanceHistoryAdapter;
import com.phppoets.grievance.model.grievanceHistory.GrievanceHIstoryREsponse;
import com.phppoets.grievance.rest.RestClient;
import com.phppoets.grievance.support.AppConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GrievanceHistoryActivity extends AppCompatActivity {
    RecyclerView rvGrievanceHistroy;
    ImageView imageViewBack;
    GrievanceHistoryAdapter grievanceHistoryAdapter;
    GrievanceHIstoryREsponse grievanceHIstoryREsponse;
    SharedPreferences preferences;
    String user_id;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grievance_history);
        rvGrievanceHistroy = (RecyclerView) findViewById(R.id.rvGrievanceHistroy);
        //paymentList = new ArrayList<PaymentService>();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        imageViewBack = (ImageView) findViewById(R.id.imageViewBack);
        rvGrievanceHistroy.setHasFixedSize(true);
        rvGrievanceHistroy.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvGrievanceHistroy.setLayoutManager((mLayoutManager));
        preferences = getSharedPreferences(AppConfig.KEY_PREFS_NAME, MODE_PRIVATE);
        user_id = preferences.getString(AppConfig.KEY_UNIQ_ID, "");

        getGrievanceHistory(user_id);
    }

    public void getGrievanceHistory(final String login_id) {
        /*dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Please wait ...");
        dialog.show();*/

        Call<GrievanceHIstoryREsponse> loginResponCall = RestClient.getClient().
                getGrievanceHistory(login_id);
        loginResponCall.enqueue(new Callback<GrievanceHIstoryREsponse>() {
            @Override
            public void onResponse(Call<GrievanceHIstoryREsponse> call, Response<GrievanceHIstoryREsponse> response) {
                Log.d("LoginActivity", "Status Code = " + response.code());
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    // request successful (status code 200, 201)
                    // dialog.dismiss();
                    grievanceHIstoryREsponse = response.body();
                    grievanceHistoryAdapter =
                            new GrievanceHistoryAdapter(GrievanceHistoryActivity.this, grievanceHIstoryREsponse.getResult());
                    rvGrievanceHistroy.setAdapter(grievanceHistoryAdapter);
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    showMessage(getResources().getString(R.string.invalid_credential));
                }
            }

            @Override
            public void onFailure(Call<GrievanceHIstoryREsponse> call, Throwable t) {
                showMessage(getResources().getString(R.string.invalid_credential));
            }
        });
    }

    public void showMessage(String msg) {
        Toast.makeText(GrievanceHistoryActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
