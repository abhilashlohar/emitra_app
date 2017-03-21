package com.phppoets.grievance.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.phppoets.grievance.utility.Utils;

import java.util.Locale;

/**
 * Created by vaibhav on 2/7/2017.
 */

public class MyApplication extends Application {
    public static Context context;

    public static Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        configureAppLanguage();
    }

    private void configureAppLanguage()
    {
        Locale locale = getResources().getConfiguration().locale;
        if(Utils.isAppLanguageHindi(this) || locale.getLanguage().equals("hi"))
        {
            locale = new Locale("hi");
        }
        else
        {
            locale = new Locale("en");
        }
        changeAppLanguage(locale);
        Utils.setCurrentLocale(locale);
    }

    public void changeAppLanguage(Locale locale)
    {
        Utils.setCurrentLocale(locale);
        Configuration configuration = new Configuration();
        configuration.setLayoutDirection(locale);
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        configureAppLanguage();
    }

}
