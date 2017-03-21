package com.phppoets.grievance.manager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.phppoets.grievance.activity.PaymentDetailActivity;
import com.phppoets.grievance.utility.Utils;

/**
 *
 */
public class NavigationManager
{

    public static void openActivity(Context context, Class activity)
    {
        Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Nullable
    public static Intent getDeepLinkIntent(Context context, String deepLink)
    {
        if(deepLink == null || deepLink.isEmpty())
        {
            return null;
        }

        Utils.setCurrentDeepLink(deepLink);

        Intent intent = null;
        Uri uri = new Uri.Builder().authority(deepLink.substring(deepLink.lastIndexOf("/") + 1,
                                                                 deepLink.lastIndexOf("?") != -1 ? deepLink.lastIndexOf("?") :
                                                                         deepLink.length()))
                                   .scheme(deepLink.substring(0, deepLink.indexOf(":")))
                                   .build();

        if(uri.getAuthority().equals("paymentDetail"))
        {
            intent = new Intent(context, PaymentDetailActivity.class);
            int length = deepLink.lastIndexOf("&");
            if(length == -1)
            {
                length = deepLink.length();
            }
            uri = uri.buildUpon().appendQueryParameter(Utils.ID, deepLink.substring(deepLink.lastIndexOf("id=") + 3, length)).build();
        }

        if(intent != null)
        {
            intent.setData(uri);
        }
        return intent;
    }
}
