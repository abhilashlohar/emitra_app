package com.phppoets.grievance.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;

import com.phppoets.grievance.Interface.DataTransferInterface;
import com.phppoets.grievance.R;
import com.phppoets.grievance.support.UIUtils;

public class GrievanceFormActivity extends AppCompatActivity {
    AutoCompleteTextView autoTextViewDepartment;
    AutoCompleteTextView autoTextViewSubDepartment;
    DataTransferInterface dtInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grievance_form);
        autoTextViewDepartment = (AutoCompleteTextView) findViewById(R.id.autoTextViewDepartment);
        dtInterface = new DataTransferInterface() {
            @Override
            public void setValues(String al) {
                autoTextViewDepartment.setText(al);
                autoTextViewDepartment.dismissDropDown();
                UIUtils.hideKeyboard(GrievanceFormActivity.this, autoTextViewDepartment);
            }
        };
    }
}
