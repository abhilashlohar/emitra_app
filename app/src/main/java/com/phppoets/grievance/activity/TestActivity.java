package com.phppoets.grievance.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.phppoets.grievance.R;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    // private WelcomeHelper sampleWelcomeScreen;
    private static final String SHOWCASE_ID = "sequence example";
    Button btnShowCase, btnShowCase1;
    ShowcaseView sv;
    TextView textViewHead;
    Toolbar toolbar;
    ImageView imageViewBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
       /* sampleWelcomeScreen = new WelcomeHelper(this, StartActivity.class);
        sampleWelcomeScreen.show(savedInstanceState);*/
        btnShowCase = (Button) findViewById(R.id.btnShowCase);
        textViewHead = (TextView) findViewById(R.id.textViewHead);
        btnShowCase.setOnClickListener(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        imageViewBack = (ImageView) findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(this);
        presentShowcaseSequence();


       /* ViewTarget target = new ViewTarget(R.id.btnShowCase, this);
        sv = new ShowcaseView.Builder(this)
                .withMaterialShowcase()
                .setTarget(target)
                .setContentTitle(R.string.showcase_main_title)
                .setContentText(R.string.showcase_main_message)
                .setStyle(R.style.CustomShowcaseTheme2)
                .replaceEndButton(R.layout.view_custom_button)
                .build();

        *//* target = new ViewTarget(R.id.btnShowCase1, this);
        sv = new ShowcaseView.Builder(this)
                .withMaterialShowcase()
                .setTarget(target)
                .setContentTitle(R.string.showcase_main_title)
                .setContentText(R.string.showcase_main_message)
                .setStyle(R.style.CustomShowcaseTheme2)
                .replaceEndButton(R.layout.view_custom_button)
                .build();*//*
        target = new ViewTarget(R.id.imageViewBack, this);
        sv = new ShowcaseView.Builder(this)
                .withMaterialShowcase()
                .setTarget(target)
                .setStyle(R.style.CustomShowcaseTheme2)
                .setContentText("Used to go back to the previous activity")
                .replaceEndButton(R.layout.view_custom_button)
                .build();

*/
    }


   /* @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        sampleWelcomeScreen.onSaveInstanceState(outState);
    }*/

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.textViewHead || v.getId() == R.id.btnShowCase) {

            presentShowcaseSequence();

        }

    }

    private void presentShowcaseSequence() {

        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, SHOWCASE_ID);

        sequence.setConfig(config);

        sequence.addSequenceItem(textViewHead,
                "This is button one", "GOT IT");

        sequence.addSequenceItem(btnShowCase,
                "This is button two", "GOT IT");


        sequence.start();

    }


}
