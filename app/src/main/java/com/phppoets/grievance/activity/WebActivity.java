package com.phppoets.grievance.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.phppoets.grievance.R;
import com.phppoets.grievance.model.makepayment.MakePaymentRequestData;
import com.phppoets.grievance.rest.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by dharmaraj on 20/3/17.
 */
public class WebActivity extends BaseActivity
{
    WebView webView;
    String id, data;
    MakePaymentRequestData makePaymentRequestData;

    @Override

    protected void onCreate(
            @Nullable
            Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_webview);
        webView = (WebView) findViewById(R.id.webView);
        if(getIntent().hasExtra("id"))
        {
            id = getIntent().getStringExtra("id");
            data = getIntent().getStringExtra("data");
        }
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setBuiltInZoomControls(true);

        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(getCacheDir().getAbsolutePath());
        settings.setDatabaseEnabled(true);
        settings.setSupportMultipleWindows(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccess(true);
        settings.setSaveFormData(true);
        webView.addJavascriptInterface(new WebViewJavaScriptInterface(this), "Android");
        webView.loadUrl("http://www.jeelwaterpark.com/grievance/grievances/paymentForm");
    }

    public void makePaymentRequest(final String id, final String data)
    {
        /*dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Please wait ...");
        dialog.show();*/

        Call<MakePaymentRequestData> loginResponCall = RestClient.getClient().
                makePayment(id, data, true);
        loginResponCall.enqueue(new Callback<MakePaymentRequestData>()
        {
            @Override
            public void onResponse(Call<MakePaymentRequestData> call, Response<MakePaymentRequestData> response)
            {
                Log.d("LoginActivity", "Status Code = " + response.code());
                if(response.isSuccessful())
                {
                    makePaymentRequestData = response.body();
                    String data = "'" + makePaymentRequestData.getResult().getPRN() + "'," +
                            "'" + makePaymentRequestData.getResult().getREQTIMESTAMP() + "'," +
                            "'" + makePaymentRequestData.getResult().getPURPOSE() + "'," +
                            "'" + makePaymentRequestData.getResult().getAMOUNT() + "'," +
                            "'" + makePaymentRequestData.getResult().getUSERNAME() + "'," +
                            "'" + makePaymentRequestData.getResult().getUSERMOBILE() + "'," +
                            "'" + makePaymentRequestData.getResult().getUSEREMAIL() + "'," +
                            "'" + makePaymentRequestData.getResult().getCHECKSUM() + "'";
                    webView.loadUrl("javascript:setValueinForm(" + data + ")");
                    webView.setWebViewClient(new WebViewClient()
                    {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url)
                        {
                            view.loadUrl(url);
                            Log.d("url", url);
                            return true;
                        }
                    });
                    //                    webView.loadUrl("javascript:submitForm()");
                }
                else
                {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    Log.d("PaymentDetailActivity", "Error Code = " + "errors");
                }
            }

            @Override
            public void onFailure(Call<MakePaymentRequestData> call, Throwable t)
            {
                Log.d("PaymentDetailActivity", "Throwable = " + t.toString());
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        makePaymentRequest(id, data);
    }

    /*
     * JavaScript Interface. Web code can access methods in here
     * (as long as they have the @JavascriptInterface annotation)
     */
    public class WebViewJavaScriptInterface
    {

        private Context context;

        /*
         * Need a reference to the context in order to sent a post message
         */
        public WebViewJavaScriptInterface(Context context)
        {
            this.context = context;
        }

        /*
         * This method can be called from Android. @JavascriptInterface
         * required after SDK version 17.
         */
        @JavascriptInterface
        public void showPDfToDownLoad(String pdfUrl)
        {
            Intent intent = new Intent(WebActivity.this, PdfActivity.class);
            intent.putExtra("url", pdfUrl);
            startActivity(intent);
            finish();
        }
    }
}
