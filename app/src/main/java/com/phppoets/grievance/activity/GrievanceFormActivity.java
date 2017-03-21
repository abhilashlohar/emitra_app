package com.phppoets.grievance.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.phppoets.grievance.Interface.DataTransferInterface;
import com.phppoets.grievance.R;
import com.phppoets.grievance.adapter.ImageViewAdapter;
import com.phppoets.grievance.adapter.ObjectAdapter;
import com.phppoets.grievance.model.addForm.AddGrievance;
import com.phppoets.grievance.model.addImage.AddImage;
import com.phppoets.grievance.model.department.Department;
import com.phppoets.grievance.model.department.Result;
import com.phppoets.grievance.permission.PermissionsActivity;
import com.phppoets.grievance.permission.PermissionsChecker;
import com.phppoets.grievance.rest.RestClient;
import com.phppoets.grievance.support.AppConfig;
import com.phppoets.grievance.support.UIUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.myinnos.awesomeimagepicker.activities.AlbumSelectActivity;
import in.myinnos.awesomeimagepicker.helpers.ConstantsCustomGallery;
import in.myinnos.awesomeimagepicker.models.Image;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    Result result;
    Department department;
    List<Result> departmentList;
    List<String> originalList;
    Button btnAccept;
    SharedPreferences preferences;
    String subject, departmentName, description;
    AddGrievance addGrievance;
    AddImage addImage;
    ArrayList<Image> images;
    String departmentId, user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grievance_form);
        departmentList = new ArrayList<Result>();
        originalList = new ArrayList<String>();
        btnAccept = (Button) findViewById(R.id.btnAccept);
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
        preferences = getSharedPreferences(AppConfig.KEY_PREFS_NAME, MODE_PRIVATE);
        user_id = preferences.getString(AppConfig.KEY_UNIQ_ID, "");

        getDepartment();
        dtInterface = new DataTransferInterface() {
            @Override
            public void setValues(String al, int pos) {
                autoTextViewDepartment.setText(al);
                departmentId = String.valueOf(departmentList.get(pos).getId());
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

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    addGrievances(subject, departmentId, description);
                }
            }
        });


    }

    public void addGrievances(final String subject, final String departmentId, final String description) {


        Call<AddGrievance> loginResponCall = RestClient.getClient().
                addGrievance(subject, description, departmentId, user_id);
        loginResponCall.enqueue(new Callback<AddGrievance>() {
            @Override
            public void onResponse(Call<AddGrievance> call, Response<AddGrievance> response) {
                Log.d("LoginActivity", "Status Code = " + response.code());
                //progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    // request successful (status code 200, 201)
                    // dialog.dismiss();
                    addGrievance = response.body();
                    String grievance_id = String.valueOf(addGrievance.getResult().getGrievanceId());
                    addImage(grievance_id);

                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    showMessage(getResources().getString(R.string.invalid_credential));
                }
            }

            @Override
            public void onFailure(Call<AddGrievance> call, Throwable t) {
                showMessage(getResources().getString(R.string.invalid_credential));
            }
        });
    }

    public void addImage(final String grievance_id) {
        RequestBody grievance_id1 = RequestBody.create(MediaType.parse("text/plain"), grievance_id);
        Map<String, RequestBody> map = new HashMap<>();
        map.put("grievance_id", grievance_id1);
        String types = images.get(0).path.substring((images.get(0).path.length() - 3), (images.get(0).path.length()));

        if (images.get(0).path != null) {
            File file2 = new File(images.get(0).path);
            RequestBody fileBody = RequestBody.create(MediaType.parse(images.get(0).path), file2);
            map.put("file\"; filename=\"cobalt." + types + "\"", fileBody);
        }

        Call<AddImage> loginResponCall = RestClient.getClient().
                addImage(map);
        loginResponCall.enqueue(new Callback<AddImage>() {
            @Override
            public void onResponse(Call<AddImage> call, Response<AddImage> response) {
                Log.d("LoginActivity", "Status Code = " + response.code());
                //progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    // request successful (status code 200, 201)
                    // dialog.dismiss();
                    addImage = response.body();
                    if (addImage.getResult().getStatus()) {
                        showMessage(addImage.getResult().getMessage());
                    }
                    //String grievance_id = String.valueOf(addGrievance.getResult().getGrievanceId());

                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    showMessage(getResources().getString(R.string.invalid_credential));
                }
            }

            @Override
            public void onFailure(Call<AddImage> call, Throwable t) {
                showMessage(getResources().getString(R.string.invalid_credential));
            }
        });
    }


    private boolean isValid() {
        subject = editSub.getText().toString().trim();
        description = editDescription.getText().toString().trim();
        departmentName = autoTextViewDepartment.getText().toString().trim();


        String pattern = "^[A-Za-z _]*$";
        if (subject.equalsIgnoreCase("")) {
            showMessage("Enter subject");
            return false;
        }
        if (departmentName.equalsIgnoreCase("")) {
            showMessage("Enter department name");
            return false;
        }

        if (description.equalsIgnoreCase("")) {
            showMessage("Enter description");
            return false;
        } else {
            return true;
        }

    }

    private void showMessage(String msg) {
        Toast.makeText(GrievanceFormActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ConstantsCustomGallery.REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            //The array list has the image paths of the selected images
            images = data.getParcelableArrayListExtra(ConstantsCustomGallery.INTENT_EXTRA_IMAGES);
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

    public void getDepartment() {


        Call<Department> loginResponCall = RestClient.getClient().
                getDepartment();
        loginResponCall.enqueue(new Callback<Department>() {
            @Override
            public void onResponse(Call<Department> call, Response<Department> response) {
                Log.d("LoginActivity", "Status Code = " + response.code());
                if (response.isSuccessful()) {

                    department = response.body();
                    departmentList = department.getResult();
                    for (int i = 0; i < departmentList.size(); i++) {
                        originalList.add(departmentList.get(i).getName());
                    }

                    objectAdapter = new ObjectAdapter(GrievanceFormActivity.this, originalList, dtInterface);
                    autoTextViewDepartment.setThreshold(1);//will start working from first character
                    autoTextViewDepartment.setAdapter(objectAdapter);

                    //                    webView.loadUrl("javascript:submitForm()");
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    Log.d("PaymentDetailActivity", "Error Code = " + "errors");
                }
            }

            @Override
            public void onFailure(Call<Department> call, Throwable t) {
                Log.d("PaymentDetailActivity", "Throwable = " + t.toString());
            }
        });
    }
}


