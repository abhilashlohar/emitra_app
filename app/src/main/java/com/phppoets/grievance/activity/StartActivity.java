package com.phppoets.grievance.activity;

import com.phppoets.grievance.R;
import com.stephentuso.welcome.BasicPage;
import com.stephentuso.welcome.TitlePage;
import com.stephentuso.welcome.WelcomeActivity;
import com.stephentuso.welcome.WelcomeConfiguration;

/**
 * Created by stephentuso on 10/5/16.
 */

public class StartActivity extends WelcomeActivity
{

    /*@Override
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this)
                .defaultBackgroundColor(R.color.colorPrimary)
                .page(new TitlePage(R.drawable.ic_image_white, "Default Welcome Screen"))
                .page(new BasicPage(R.drawable.ic_style_white, "Default style", "No custom styles are applied to this welcome screen"))
                .page(new BasicPage(R.drawable.ic_image_white, "Default properties", "No properties were set on the WelcomeScreenBuilder"))
                .build();
    }*/

    @Override
    protected WelcomeConfiguration configuration()
    {
        return new WelcomeConfiguration.Builder(this).defaultBackgroundColor(R.color.colorPrimaryDark)
                                                     .page(new TitlePage(R.drawable.ic_style_white,
                                                                         getResources().getString(R.string.fetch_util)))
                                                     .page(new BasicPage(R.drawable.ic_image_white,
                                                                         getResources().getString(R.string.make_pay),
                                                                         getResources().getString(R.string.make_pay_desc)).background(
                                                             R.color.colorPrimary))
                                                     .page(new BasicPage(R.drawable.ic_style_white,
                                                                         getResources().getString(R.string.Grievance),
                                                                         getResources().getString(R.string.regist_track_grievance)))
                                                     .swipeToDismiss(true)
                                                     .build();
    }
}