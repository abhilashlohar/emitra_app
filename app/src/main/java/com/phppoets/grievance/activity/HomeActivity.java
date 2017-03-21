package com.phppoets.grievance.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.phppoets.grievance.R;
import com.phppoets.grievance.adapter.SlideAdapter;
import com.phppoets.grievance.application.MyApplication;
import com.phppoets.grievance.manager.NavigationManager;
import com.phppoets.grievance.support.CirclePageIndicator;
import com.phppoets.grievance.support.SmoothViewPager;
import com.phppoets.grievance.support.UIUtils;
import com.phppoets.grievance.utility.TSTypeface;
import com.phppoets.grievance.utility.Utils;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.honorato.multistatetogglebutton.ToggleButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final String SHOWCASE_ID = "sequence_home";
    ImageView imgNotification;
    CardView cvPaymentService, cvPaymentHistroy, cvGrievanceServices, cvGrievanceHistroy;
    TextView txtGrievanceServices, txtPaymentService, txtPaymentHistory, txtGrievanceHistroy;
    SmoothViewPager viewpagerGallery;
    MultiStateToggleButton button;
    CirclePageIndicator circlePageIndicator;

    SlideAdapter slideAdapter;
    Timer swipeTimer;
    int currentPage;
    int NUM_PAGES;
    List<Drawable> sliderList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imgNotification = (ImageView) findViewById(R.id.imgNotification);
        cvPaymentService = (CardView) findViewById(R.id.cvPaymentService);
        cvGrievanceServices = (CardView) findViewById(R.id.cvGrievanceServices);
        cvPaymentHistroy = (CardView) findViewById(R.id.cvPaymentHistroy);
        cvGrievanceHistroy = (CardView) findViewById(R.id.cvGrievanceHistroy);
        txtGrievanceServices = (TextView) findViewById(R.id.txtGrievanceServices);
        txtPaymentService = (TextView) findViewById(R.id.txtPaymentService);
        txtPaymentHistory = (TextView) findViewById(R.id.txtPaymentHistory);
        txtGrievanceHistroy = (TextView) findViewById(R.id.txtGrievanceHistroy);
        viewpagerGallery = (SmoothViewPager) findViewById(R.id.viewpager_slide);
        circlePageIndicator = (CirclePageIndicator) findViewById(R.id.circlePageIndicator);

        sliderList = new ArrayList<Drawable>();
        sliderList.add(getResources().getDrawable(R.drawable.images));
        sliderList.add(getResources().getDrawable(R.drawable.images_2));
        sliderList.add(getResources().getDrawable(R.drawable.images_3));
        sliderList.add(getResources().getDrawable(R.drawable.images_4));
        txtGrievanceServices.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));
        txtPaymentService.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));
        txtPaymentHistory.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));
        txtGrievanceHistroy.setTypeface(UIUtils.getTypeface(this, TSTypeface.MEDIUM));
        imgNotification.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(HomeActivity.this, NotificationActivity.class));
            }
        });

        viewpagerGallery.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });

        button = (MultiStateToggleButton) this.findViewById(R.id.mstb_multi_id);
        button.setOnValueChangedListener(new ToggleButton.OnValueChangedListener()
        {
            @Override
            public void onValueChanged(int position)
            {
                if(position == 0)
                {
                    ((MyApplication) getApplication()).changeAppLanguage(new Locale("en"));
                    Utils.saveAppLanguage(HomeActivity.this, "English");
                }
                else
                {
                    ((MyApplication) getApplication()).changeAppLanguage(new Locale("hi"));
                    Utils.saveAppLanguage(HomeActivity.this, "Hindi");
                }

                NavigationManager.openActivity(HomeActivity.this, HomeActivity.class);
                finish();
            }
        });

        cvGrievanceServices.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(HomeActivity.this, GrievanceFormActivity.class));
            }
        });
        cvPaymentService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(HomeActivity.this, PaymentListActivity.class));
            }
        });

        cvGrievanceHistroy.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(HomeActivity.this, GrievanceHistoryActivity.class));
            }
        });

        cvPaymentHistroy.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(HomeActivity.this, PaymentHistoryActivity.class));
            }
        });

        if(slideAdapter == null)
        {
            slideAdapter = new SlideAdapter(HomeActivity.this, sliderList);
        }
        else
        {
            slideAdapter.notifyDataSetChanged();
        }
        NUM_PAGES = sliderList.size() + 1;
        viewpagerGallery.setAdapter(slideAdapter);
        circlePageIndicator.setViewPager(viewpagerGallery);
        //viewpagerGallery.setScrollDurationFactor(4);
        autoPlayAdvertise();

        presentShowcaseSequence();
    }

    private void autoPlayAdvertise()
    {
        final Handler handler = new Handler();
        final Runnable Update = new Runnable()
        {
            public void run()
            {
                if(currentPage == NUM_PAGES - 1)
                {
                    currentPage = 0;
                }
                currentPage = (currentPage + 1) % NUM_PAGES;
                viewpagerGallery.setCurrentItem(currentPage, true);
                //advAdapter.notifyDataSetChanged();
            }
        };
        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask()
        {

            @Override
            public void run()
            {
                handler.post(Update);
            }
        }, 0, 5000);
    }

    public void change()
    {
        //        if(options[which].equals(getString(R.string.english_lbl)))
        //        {
        //            ((MyApplication) getApplication()).changeAppLanguage(new Locale("en"));
        //            Utils.saveAppLanguage(HomeActivity.this, "English");
        //        }
        //        else if(options[which].equals(getString(R.string.hindi_lbl)))
        //        {
        //            ((MyApplication) getApplication()).changeAppLanguage(new Locale("hi"));
        //            Utils.saveAppLanguage(HomeActivity.this, "Hindi");
        //        }
        //        NavigationManager.openActivity(HomeActivity.this, HomeActivity.class);
        //        finish();
    }

    @Override
    public void onClick(View view)
    {

    }

    private void presentShowcaseSequence()
    {

        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, SHOWCASE_ID);

        sequence.setConfig(config);

        sequence.addSequenceItem(button, getResources().getString(R.string.change_lang), getResources().getString(R.string.got_it));

        sequence.addSequenceItem(imgNotification, getResources().getString(R.string.click_noti), getResources().getString(R.string.got_it));

        sequence.start();
    }
}
