package com.phppoets.grievance.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;

import com.phppoets.grievance.Interface.DataTransferInterface;
import com.phppoets.grievance.R;
import com.phppoets.grievance.adapter.ImageViewAdapter;
import com.phppoets.grievance.adapter.ObjectAdapter;
import com.phppoets.grievance.permission.PermissionsActivity;
import com.phppoets.grievance.permission.PermissionsChecker;
import com.phppoets.grievance.support.UIUtils;

import java.util.ArrayList;

import in.myinnos.awesomeimagepicker.activities.AlbumSelectActivity;
import in.myinnos.awesomeimagepicker.helpers.ConstantsCustomGallery;
import in.myinnos.awesomeimagepicker.models.Image;

public class GrievanceFormActivity extends AppCompatActivity {
    private static final String[] PERMISSIONS_READ_STORAGE = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int LIMIT = 5;
    PermissionsChecker checker;
    AutoCompleteTextView autoTextViewDepartment;
    AutoCompleteTextView autoTextViewSubDepartment;
    DataTransferInterface dtInterface;
    ImageButton btnAttachFile;
    RecyclerView rvImageView;
    EditText editSub, editDescription;
    ObjectAdapter objectAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grievance_form);
        btnAttachFile = (ImageButton) findViewById(R.id.btnAttachFile);
        rvImageView = (RecyclerView) findViewById(R.id.rvImageView);
        editSub = (EditText) findViewById(R.id.editSub);
        editDescription = (EditText) findViewById(R.id.editDescription);
        rvImageView.setHasFixedSize(true);
        rvImageView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rvImageView.setLayoutManager((mLayoutManager));
        autoTextViewDepartment = (AutoCompleteTextView) findViewById(R.id.autoTextViewDepartment);
        autoTextViewSubDepartment = (AutoCompleteTextView) findViewById(R.id.autoTextViewSubDepartment);
        checker = new PermissionsChecker(this);
        dtInterface = new DataTransferInterface() {
            @Override
            public void setValues(String al) {
                autoTextViewDepartment.setText(al);
                autoTextViewDepartment.dismissDropDown();
                UIUtils.hideKeyboard(GrievanceFormActivity.this, autoTextViewDepartment);
            }
        };
        btnAttachFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (checker.lacksPermissions(PERMISSIONS_READ_STORAGE)) {
                        startPermissionsActivity(PERMISSIONS_READ_STORAGE);
                    } else {
                        Intent intent = new Intent(GrievanceFormActivity.this, AlbumSelectActivity.class);
                        intent.putExtra(ConstantsCustomGallery.INTENT_EXTRA_LIMIT, LIMIT);
                        startActivityForResult(intent, ConstantsCustomGallery.REQUEST_CODE);
                    }
                } else {
                    Intent intent = new Intent(GrievanceFormActivity.this, AlbumSelectActivity.class);
                    intent.putExtra(ConstantsCustomGallery.INTENT_EXTRA_LIMIT, LIMIT);
                    startActivityForResult(intent, ConstantsCustomGallery.REQUEST_CODE);

                }
            }
        });

      /*  objectAdapter = new ObjectAdapter(GrievanceFormActivity.this, originalList, dtInterface);
        autoTextViewDepartment.setThreshold(1);//will start working from first character
        autoTextViewDepartment.setAdapter(objectAdapter);*/
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ConstantsCustomGallery.REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            //The array list has the image paths of the selected images
            ArrayList<Image> images = data.getParcelableArrayListExtra(ConstantsCustomGallery.INTENT_EXTRA_IMAGES);
            if (!images.isEmpty()) {
                rvImageView.setVisibility(View.VISIBLE);
                ImageViewAdapter imageViewAdapter = new ImageViewAdapter(GrievanceFormActivity.this, images);
                rvImageView.setAdapter(imageViewAdapter);
            }

            /*for (int i = 0; i < images.size(); i++) {
                Uri uri = Uri.fromFile(new File(images.get(i).path));
                // start play with image uri

            }*/
        }
    }

    private void startPermissionsActivity(String[] permission) {
        PermissionsActivity.startActivityForResult(this, 0, permission);
    }
}


