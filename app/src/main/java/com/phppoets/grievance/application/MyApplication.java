package com.phppoets.grievance.application;

import android.app.Application;
import android.content.Context;

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
//        Branch.getAutoInstance(this);
//        Fabric.with(this, new Crashlytics());
//        FontsOverride.setDefaultFont(this, "MONOSPACE","font/raleway_medium.ttf");
//        /*Configuration dbConfiguration = new Configuration.Builder(this).setDatabaseName("xxx.db").create();
//        ActiveAndroid.initialize(dbConfiguration);*/
//        ActiveAndroid.initialize(this);
    }

}
