package com.phppoets.grievance.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.phppoets.grievance.R;
import com.phppoets.grievance.manager.NavigationManager;
import com.phppoets.grievance.support.UIUtils;
import com.phppoets.grievance.utility.Utils;

import java.util.Map;

/**
 * Created by Mahmoud on 10/11/2015.
 */
public class FCMNotificationsService extends FirebaseMessagingService
{
    String message;
    String buttonText;
    String deepLink;
    String notificationId;

    @Override
    public void onMessageReceived(RemoteMessage message)
    {

        Map data = message.getData();
        String from = message.getFrom();

        //process your app's notification messages
        handleMessage(getApplicationContext(), data);
    }

    private void handleMessage(final Context context, Map extras)
    {
        if(extras.containsKey("message") && extras.get("message") != null)
        {
            message = extras.get("message").toString();
        }
        if(extras.containsKey("button_text") && extras.get("button_text") != null)
        {
            buttonText = extras.get("button_text").toString();
        }
        if(extras.containsKey("deep_link") && extras.get("deep_link") != null)
        {
            deepLink = extras.get("deep_link").toString();
        }
        if(extras.containsKey("notification_id") && extras.get("notification_id") != null)
        {
            notificationId = extras.get("notification_id").toString();
        }

        if(message != null && !TextUtils.isEmpty(message))
        {
            if(Utils.getCurrentActivity() != null && (deepLink != null && !deepLink.contains("payment")))
            {
                Utils.getCurrentActivity().runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        showDialog(context, message, buttonText, deepLink, notificationId);
                    }
                });
            }
            else
            {
                showNotification(context, message, deepLink, notificationId);
            }
        }
    }

    private void showNotification(Context context, String message, String deepLink, String notificationId)
    {
        if(!TextUtils.isEmpty(message) && !TextUtils.isEmpty(deepLink))
        {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context);
            notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
            notificationBuilder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
            notificationBuilder.setContentText(message);
            notificationBuilder.setContentTitle(context.getString(R.string.app_name));
            notificationBuilder.setStyle(new NotificationCompat.BigTextStyle(notificationBuilder).bigText(message)
                                                                                                 .setBigContentTitle(context.getString(
                                                                                                         R.string.app_name)));
            Intent windowToOpenIntent = NavigationManager.getDeepLinkIntent(context, deepLink);
            if(windowToOpenIntent != null)
            {
                windowToOpenIntent.putExtra(Utils.IS_FROM_NOTIFICATION, true);
                windowToOpenIntent.putExtra(Utils.DEEP_LINK, deepLink);
                windowToOpenIntent.putExtra(Utils.NOTIFICATION_ID, notificationId);
                PendingIntent pendingIntent =
                        PendingIntent.getActivity(context, 100, windowToOpenIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                notificationBuilder.setContentIntent(pendingIntent);
                notificationBuilder.setAutoCancel(true);
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, notificationBuilder.build());
            }
        }
    }

    private void showDialog(final Context context, String message, String buttonText, final String deepLink, final String notificationId)
    {
        if(buttonText != null && !buttonText.isEmpty())
        {
            UIUtils.showOkCancelAlertDialog(Utils.getCurrentActivity(), buttonText.toUpperCase(), context.getString(R.string.cancel),
                                            context.getString(R.string.app_name), message, new MaterialDialog.Callback()
                    {
                        @Override
                        public void onPositive(MaterialDialog dialog)
                        {
                            openActivity(context, deepLink);
                        }

                        @Override
                        public void onNegative(MaterialDialog dialog)
                        {
                            dialog.dismiss();
                        }
                    });
        }
        else
        {
            if(Utils.getCurrentActivity() != null)
            {
                UIUtils.showOkAlertDialog(Utils.getCurrentActivity(), Utils.getCurrentActivity().getString(R.string.app_name), message,
                                          new MaterialDialog.SimpleCallback()
                                          {
                                              @Override
                                              public void onPositive(MaterialDialog dialog)
                                              {
                                                  dialog.dismiss();
                                              }
                                          });
            }
        }
    }

    private void openActivity(Context context, String deepLink)
    {
        Intent windowToOpenIntent = NavigationManager.getDeepLinkIntent(context, deepLink);
        if(windowToOpenIntent != null)
        {
            windowToOpenIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(windowToOpenIntent);
        }
    }
}