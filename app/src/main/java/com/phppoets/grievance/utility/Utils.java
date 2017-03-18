package com.phppoets.grievance.utility;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.phppoets.grievance.BuildConfig;
import com.phppoets.grievance.R;
import com.phppoets.grievance.activity.BaseActivity;
import com.phppoets.grievance.support.AppConfig;


/**
 * Created by vaibhav on 11/8/2016.
 */

public class Utils {
    public static final String IS_FROM_NOTIFICATION = "IsFromNotification";
    public static final String DEEP_LINK = "DeepLink";
    public static final String NOTIFICATION_ID = "notification_id";
    public static final String ID = "id";
    public static final String NOTICE = "notice";

    private static BaseActivity currentActivity;
    private static String currentDeepLink;

    public static float dbToPx(Context context, float db) {
        if (context != null) {
            float px = db * context.getResources().getDisplayMetrics().density;
            return px;
        } else return db;
    }

    public static void printLog(String TAG, String Value) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "  " + Value);
        }
    }

    public static MaterialDialog showOkCancelAlertDialog(Activity activity, String ok, String cancel, String title, String message, final MaterialDialog.Callback materialDialogCallback) {
        MaterialDialog alertDialog = new MaterialDialog.Builder(activity)
                .title(title)
                .content(message)
                .negativeText(cancel)
                .positiveText(ok)
                .cancelable(false)
                .positiveColor(activity.getResources().getColor(R.color.colorPrimary))
                .callback(materialDialogCallback).build();
        alertDialog.show();
        return alertDialog;
    }

    public static MaterialDialog showOkAlertDialog(Activity activity, String title, String message, final MaterialDialog.SimpleCallback materialDialogCallback) {
        MaterialDialog alertDialog = new MaterialDialog.Builder(activity)
                .title(title)
                .content(message)
                .positiveText("OK")
                .cancelable(false)
                .positiveColor(activity.getResources().getColor(R.color.colorPrimary))
                .callback(materialDialogCallback).build();
        alertDialog.show();
        return alertDialog;
    }

    public static void hideKeyboard(Activity activity) {
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }


    }

    public static BaseActivity getCurrentActivity() {
        return currentActivity;
    }

    public static void setCurrentActivity(BaseActivity currentActivity) {
        Utils.currentActivity = currentActivity;
    }

    public static String getCurrentDeepLink() {
        return currentDeepLink;
    }

    public static void setCurrentDeepLink(String currentDeepLink) {
        Utils.currentDeepLink = currentDeepLink;
    }

    public static boolean isLoggedIn(Context context) {
        return context.getSharedPreferences(AppConfig.KEY_PREFS_NAME, 0).getBoolean(AppConfig.KEY_PREFS_ISLOGGED, false);
    }

    public static String getUserId(Context context) {
        return context.getSharedPreferences(AppConfig.KEY_PREFS_NAME, 0).getString(AppConfig.KEY_USER_ID, "");
    }

}
