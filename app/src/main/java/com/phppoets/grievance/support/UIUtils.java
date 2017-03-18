package com.phppoets.grievance.support;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.phppoets.grievance.R;
import com.phppoets.grievance.utility.TSTypeface;

import java.util.Hashtable;


/**

 */
public class UIUtils {


    private static ProgressDialog globalProgressDialog;

    private static Hashtable<TSTypeface, Typeface> mCache = new Hashtable<>();

    public static ProgressDialog getGlobalProgressDialog() {
        return globalProgressDialog;
    }

    public static void setGlobalProgressDialog(ProgressDialog globalProgressDialog) {
        UIUtils.globalProgressDialog = globalProgressDialog;
    }

    public static void showToast(Context context, int msgId) {
        if (context != null) {
            showToast(context, context.getString(msgId));
        }
    }

    public static void showToast(Context context, String msg) {
        if (context != null) {
            Toast toast = new Toast(context);
            //  View view = LayoutInflater.from(context).inflate(R.layout.toast, null);
            //  TextView tvToastText = (TextView) view.findViewById(R.id.tvToastText);
            // tvToastText.setTextColor(context.getResources().getColor(R.color.white));

            // tvToastText.setTypeface(UIUtils.getTypeface(context, TSTypeface.LIGHT));

            //  tvToastText.setText(msg);
            //  toast.setView(view);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
            toast.setMargin(0, 0);
            toast.show();
        }
    }

    public static void showToast(Context context, String msg, int colorRes) {
        Toast toast = new Toast(context);
        //  View view = LayoutInflater.from(context).inflate(R.layout.toast, null);
        //view.setBackgroundColor(context.getResources().getColor(colorRes));
        // TextView tvToastText = (TextView) view.findViewById(tvToastText);

        //  tvToastText.setTypeface(UIUtils.getTypeface(context, TSTypeface.LIGHT));

        // tvToastText.setText(msg);
        // toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setMargin(0, 0);
        toast.show();
    }

    public static void showSnackBar(Context context, View coordinatorLayout, int msgId, int actionNameId) {
        if (context != null) {
            showSnackBar(context, coordinatorLayout, context.getString(msgId), context.getString(actionNameId));
        }
    }

    public static void showSnackBar(Context context, View coordinatorLayout, String msg, String actionName) {
        final Snackbar snackbar = Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(actionName, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.setActionTextColor(context.getResources().getColor(android.R.color.white));
        View view = snackbar.getView();
        view.setBackgroundColor(context.getResources().getColor(R.color.red_toast));
        TextView tvToastText = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tvToastText.setTextColor(context.getResources().getColor(R.color.white));
        tvToastText.setMaxLines(3);

        tvToastText.setTypeface(UIUtils.getTypeface(context, TSTypeface.LIGHT));


        snackbar.show();
    }

    public static void showLocationSnackBar(final Context context, View coordinatorLayout, String msg, String actionName) {
        final Snackbar snackbar = Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(actionName, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(callGPSSettingIntent);
            }
        });
        snackbar.setActionTextColor(context.getResources().getColor(android.R.color.white));
        View view = snackbar.getView();
        view.setBackgroundColor(context.getResources().getColor(R.color.red_toast));
        TextView tvToastText = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tvToastText.setTextColor(context.getResources().getColor(R.color.white));
        tvToastText.setMaxLines(3);

        tvToastText.setTypeface(UIUtils.getTypeface(context, TSTypeface.LIGHT));


        snackbar.show();
    }

    public static ProgressDialog showProgressDialog(Context context) {
        if (context != null) {
            ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.show();
            ProgressBar progressBar = new ProgressBar(context);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            progressBar.getIndeterminateDrawable().setColorFilter(context.getResources().getColor(R.color.cyan), PorterDuff.Mode.MULTIPLY);
            progressDialog.setContentView(progressBar);
            return progressDialog;
        } else {
            return null;
        }
    }

    public static void showGlobalProgressDialog(Context context) {

        globalProgressDialog = new ProgressDialog(context);
        globalProgressDialog.setIndeterminate(true);
        globalProgressDialog.setCancelable(false);
        globalProgressDialog.show();
        ProgressBar progressBar = new ProgressBar(context);
        globalProgressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressBar.getIndeterminateDrawable().setColorFilter(context.getResources().getColor(R.color.cyan), PorterDuff.Mode.MULTIPLY);
        globalProgressDialog.setContentView(progressBar);
    }

    public static void dismissGlobalProgressDialog() {
        if (globalProgressDialog != null && globalProgressDialog.isShowing()) {
            globalProgressDialog.dismiss();
        }
    }

    public static void showAlertDialog(Context context, String title, String message) {
        MaterialDialog alertDialog = new MaterialDialog.Builder((Activity) context).cancelable(false)
                .negativeText(R.string.cancel)
                .positiveText(R.string.ok)
                .cancelable(false)
                .content(message)
                .title(title)
                .positiveColor(
                        context.getResources().getColor(R.color.cyan))
                .callback(new MaterialDialog.Callback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        dialog.dismiss();
                    }
                })
                .build();
        alertDialog.show();
    }


    public static Typeface getTypeface(Context context, TSTypeface tsTypefac) {
        Typeface typeface = null;
        if (mCache.containsKey(tsTypefac)) {
            return mCache.get(tsTypefac);
        }
        switch (tsTypefac) {
            case BOLD:
                typeface = Typeface.createFromAsset(context.getAssets(), "font/raleway_bold.ttf");
                mCache.put(tsTypefac, typeface);
                break;
            case MEDIUM:
                typeface = Typeface.createFromAsset(context.getAssets(), "font/raleway_medium.ttf");
                mCache.put(tsTypefac, typeface);
                break;
            case LIGHT:
                typeface = Typeface.createFromAsset(context.getAssets(), "font/raleway_light.ttf");
                mCache.put(tsTypefac, typeface);
                break;
            default:
                typeface = Typeface.DEFAULT;
                mCache.put(tsTypefac, typeface);
        }
        return typeface;
    }

    public static MaterialDialog showOkCancelAlertDialog(Activity activity, String title, String message,
                                                         final MaterialDialog.Callback materialDialogCallback) {
        MaterialDialog materialDialog =
                showOkCancelAlertDialog(activity, activity.getString(R.string.ok), activity.getString(R.string.cancel), title, message,
                        materialDialogCallback);
        return materialDialog;
    }

    public static MaterialDialog showOkCancelAlertDialog(Activity activity, String ok, String cancel, String title, String message,
                                                         final MaterialDialog.Callback materialDialogCallback) {
        if (activity != null && !activity.isFinishing()) {


            MaterialDialog alertDialog = new MaterialDialog.Builder(activity).title(title)
                    .content(message)
                    .negativeText(cancel)
                    .positiveText(ok)
                    .cancelable(false)
                    .positiveColor(activity.getResources().getColor(R.color.cyan))
                    .callback(materialDialogCallback)
                    .build();
            alertDialog.show();
            return alertDialog;
        }
        return null;
    }

    public static MaterialDialog showOkAlertDialog(Activity activity, String title, String message,
                                                   final MaterialDialog.SimpleCallback materialDialogCallback) {
        if (activity != null && activity.isFinishing()) {


            MaterialDialog alertDialog = new MaterialDialog.Builder(activity).title(title)
                    .content(message)
                    .positiveText(R.string.ok)
                    .cancelable(false)
                    .positiveColor(activity.getResources().getColor(R.color.cyan))
                    .callback(materialDialogCallback)
                    .build();
            alertDialog.show();
            return alertDialog;
        }
        return null;
    }


    public static void showKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void toggleKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public static Bitmap changeBitmapColorToCyan(Context context, Bitmap bitmap) {
        int length = bitmap.getWidth() * bitmap.getHeight();
        int[] pixels = new int[length];
        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        Bitmap bmpResult = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        for (int col = 0; col < bitmap.getWidth(); col++) {
            for (int row = 0; row < bitmap.getHeight(); row++) {
                int pixelIndex = row * bitmap.getHeight() + col;
                if (pixelIndex < length && pixels[pixelIndex] != 0) {
                    pixels[pixelIndex] = context.getResources().getColor(R.color.cyan);
                }
            }
        }
        bmpResult.setPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        return bmpResult;
    }

    @NonNull
    public static Bitmap changeBitmapToWhite(Bitmap bitmapMissionIcon) {
        if (bitmapMissionIcon != null) {
            Paint paint = new Paint();
            paint.setColorFilter(new PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN));
            Bitmap mutableBitmapMissionIcon = bitmapMissionIcon.copy(bitmapMissionIcon.getConfig(), true);
            Canvas canvas = new Canvas(mutableBitmapMissionIcon);
            canvas.drawBitmap(mutableBitmapMissionIcon, 0, 0, paint);
            return mutableBitmapMissionIcon;
        } else {
            return null;
        }
    }

    public static Bitmap changeBitmapTintColor(Bitmap bitmap, String colorString) {

        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(Color.parseColor("#" + colorString), PorterDuff.Mode.SRC_IN));
        Bitmap bmpResult = bitmap.copy(bitmap.getConfig(), true);
        Canvas canvas = new Canvas(bmpResult);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return bmpResult;
    }


//    public static void onPermissionShowRationale(final Activity activity, String message) {
//        UIUtils.showOkCancelAlertDialog(activity, activity.getString(R.string.task_spotting), message, new MaterialDialog.Callback() {
//            @Override
//            public void onPositive(MaterialDialog dialog) {
//                UIUtils.openAppSettingsScreen(activity);
//            }
//
//            @Override
//            public void onNegative(MaterialDialog dialog) {
//                dialog.dismiss();
//            }
//        });
//    }


}
