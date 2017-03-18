package com.phppoets.grievance.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.phppoets.grievance.application.MyApplication;
import com.phppoets.grievance.support.InternetStatus;
import com.phppoets.grievance.utility.Utils;


public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setCurrentActivity(this);
        ((MyApplication) getApplication()).setContext(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!InternetStatus.getInstance(this).isOnline()) {
            internetCheckDialog();
        }
    }

    public void internetCheckDialog() {

//        Utils.showOkCancelAlertDialog(this, getString(R.string.settings), getString(R.string.exit),
//                getString(R.string.app_name), getString(R.string.internet_msg),
//                new MaterialDialog.Callback() {
//                    @Override
//                    public void onPositive(MaterialDialog dialog) {
//
//                        startActivity(new Intent(
//                                Settings.ACTION_SETTINGS));
//                    }
//
//                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//                    @Override
//                    public void onNegative(MaterialDialog dialog) {
//                        //startActivity(new Intent(BaseActivity.this, StartActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                        // .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).putExtra("EXIT", true));
//                        //overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
//                        finishAffinity();
//                    }
//                });
    }
}
