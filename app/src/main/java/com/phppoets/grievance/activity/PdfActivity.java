package com.phppoets.grievance.activity;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.phppoets.grievance.R;
import com.phppoets.grievance.manager.NavigationManager;
import com.phppoets.grievance.permission.PermissionsActivity;
import com.phppoets.grievance.permission.PermissionsChecker;
import com.phppoets.grievance.support.AppConfig;
import com.phppoets.grievance.support.InternetStatus;
import com.phppoets.grievance.support.StudentDetails;
import com.phppoets.grievance.support.UIUtils;
import com.phppoets.grievance.utility.TSTypeface;
import com.phppoets.grievance.utility.Utils;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class PdfActivity extends AppCompatActivity
{
    public static final int progress_bar_type = 0;
    private static final String[] PERMISSIONS_WRITE_STORAGE = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
    PermissionsChecker checker;
    TextView txtStudentName, txtLastUpdated, txtDate, textViewHead, txtDownloadPdf, textViewName;
    ImageView imageViewBack, imgCloudDownload;
    String notice_Id, pdfName;
    //SearchDatum searchDatum;
    WebView wv;
    SharedPreferences preferences;
    //ResultResponse resultResponse;
    LinearLayout lldownload;
    Integer pageNumber = 1;
    String eno_no, school_id, pdfUrl;
    // List<SearchDatum> searchDatumList;
    CoordinatorLayout coordinatorLayout;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        checker = new PermissionsChecker(this);
        pdfName = "example.pdf";
        setContentView(R.layout.activity_pdf);
        //searchDatumList = new ArrayList<SearchDatum>();
        // pdfView = (PDFView) findViewById(R.id.pdfView);
        txtStudentName = (TextView) findViewById(R.id.txtStudentName);
        txtDate = (TextView) findViewById(R.id.txtDate);
        imageViewBack = (ImageView) findViewById(R.id.imageViewBack);
        txtDownloadPdf = (TextView) findViewById(R.id.txtDownloadPdf);
        textViewHead = (TextView) findViewById(R.id.textViewHead);
        imgCloudDownload = (ImageView) findViewById(R.id.imgCloud);
        txtLastUpdated = (TextView) findViewById(R.id.txtLastUpdated);
        textViewName = (TextView) findViewById(R.id.textViewName);
        lldownload = (LinearLayout) findViewById(R.id.lldownload);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        wv = (WebView) findViewById(R.id.webView);
        wv.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setScrollbarFadingEnabled(false);
        wv.setBackgroundColor(Color.WHITE);
        pdfUrl = getIntent().getStringExtra("url");
        //handleDeepLink(data);
        setFonts();
        imageViewBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        imgCloudDownload.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(checker.lacksPermissions(PERMISSIONS_WRITE_STORAGE))
                {
                    startPermissionsActivity(PERMISSIONS_WRITE_STORAGE);
                }
                else
                {
                    new DownloadFileFromURL().execute(pdfUrl);
                }
                //new DownloadFileFromURL().execute(pdfUrl);

            }
        });
    }

    /* private void getResultData(final String eno_no, final String school_id) {
         Call<ResultResponse> resultResponseCall = RestClient.getClient().
                 getResult(eno_no, school_id);
         resultResponseCall.enqueue(new Callback<ResultResponse>() {
             @Override
             public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                 Log.d("ResultActivity", "Status Code = " + response.code());
                 if (response.isSuccessful()) {
                     // request successful (status code 200, 201)
                     resultResponse = response.body();
                     if (resultResponse.getStatus() == true) {
                         searchDatumList = resultResponse.getSearchData();
                         setData(searchDatumList);
                     }
                 }
             }

             @Override
             public void onFailure(Call<ResultResponse> call, Throwable t) {

             }

         });

     }

     private void setData(List<SearchDatum> searchDatum) {
         for (int i = 0; i < searchDatum.size(); i++) {
             txtDate.setText(searchDatum.get(i).getPdfUpdateDate());
             wv.getSettings().setJavaScriptEnabled(true);
 //        wv.getSettings().setDomStorageEnabled(true);
 //        wv.getSettings().setAllowFileAccess(true);
             pdfUrl = searchDatum.get(i).getPdfPath();
             wv.loadUrl("https://docs.google.com/gview?embedded=true&url=" + searchDatum.get(i).getPdfPath());
         }

     }
 */
    private void DownloadFiles()
    {
        try
        {
            //URL u = new URL(searchDatum.getPdfPath());
            URL u = new URL("");
            InputStream is = u.openStream();

            DataInputStream dis = new DataInputStream(is);

            byte[] buffer = new byte[1024];
            int length;

            FileOutputStream fos = new FileOutputStream(new File(Environment.getExternalStorageDirectory() + "/" + "data/test.pdf"));
            while((length = dis.read(buffer)) > 0)
            {
                fos.write(buffer, 0, length);
            }
        }
        catch(MalformedURLException mue)
        {
            Log.e("SYNC getUpdate", "malformed url error", mue);
        }
        catch(IOException ioe)
        {
            Log.e("SYNC getUpdate", "io error", ioe);
        }
        catch(SecurityException se)
        {
            Log.e("SYNC getUpdate", "security error", se);
        }
    }

    public void setFonts()
    {
        textViewName.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));
        txtStudentName.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));
        txtDate.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));
        txtLastUpdated.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));
        txtDownloadPdf.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));
        textViewHead.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));
    }

    private void startPermissionsActivity(String[] permission)
    {
        PermissionsActivity.startActivityForResult(this, 0, permission);
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch(id)
        {
            case progress_bar_type: // we set this to 0
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }

    private void handleDeepLink(Uri data)
    {
        if(Utils.isLoggedIn(getApplicationContext()))
        {
            if(InternetStatus.getInstance(this).isOnline())
            {
                preferences = getSharedPreferences(AppConfig.KEY_PREFS_NAME, MODE_PRIVATE);
                String dataValue = preferences.getString(AppConfig.KEY_STUDENT_DATA, "");
                StudentDetails studentDetails = new Gson().fromJson(dataValue, StudentDetails.class);
                eno_no = studentDetails.getEnrollment_no();
                school_id = preferences.getString(AppConfig.KEY_UNIQ_ID, "");
                textViewName.setText(studentDetails.getStudentName());
                //getResultData(eno_no, school_id);

            }
            else
            {
                UIUtils.showSnackBar(this, coordinatorLayout, R.string.no_connection, R.string.ok);
            }
        }
        else
        {
            if(data != null)
            {
                Utils.setCurrentDeepLink(data.toString());
            }
            NavigationManager.openActivity(this, LoginActivity.class);
        }
    }

    /**
     * Background Async Task to download file
     */
    class DownloadFileFromURL extends AsyncTask<String, String, String>
    {

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            showDialog(progress_bar_type);
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url)
        {
            try
            {
                URL url = new URL(pdfUrl);
                URLConnection conexion = url.openConnection();
                conexion.connect();

                int lenghtOfFile = conexion.getContentLength();
                Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);
                InputStream input = new BufferedInputStream(url.openStream());

                byte[] buffer = new byte[1024];
                int length;
                long total = 0;

                FileOutputStream fos =
                        new FileOutputStream(new File(Environment.getExternalStorageDirectory() + "/" + "Download/test.pdf"));
                while((length = input.read(buffer)) != -1)
                {
                    total += length;
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));
                    fos.write(buffer, 0, length);
                }
            }
            catch(MalformedURLException mue)
            {
                Log.e("SYNC getUpdate", "malformed url error", mue);
            }
            catch(IOException ioe)
            {
                Log.e("SYNC getUpdate", "io error", ioe);
            }
            catch(SecurityException se)
            {
                Log.e("SYNC getUpdate", "security error", se);
            }
            return null;
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress)
        {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task
         * Dismiss the progress dialog
         **/
        @Override
        protected void onPostExecute(String file_url)
        {
            // dismiss the dialog after the file was downloaded
            dismissDialog(progress_bar_type);
        }
    }
}
