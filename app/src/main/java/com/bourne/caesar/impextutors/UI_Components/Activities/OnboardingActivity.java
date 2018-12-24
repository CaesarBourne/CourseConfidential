package com.bourne.caesar.impextutors.UI_Components.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bourne.caesar.impextutors.MainActivity;
import com.bourne.caesar.impextutors.R;
import com.bourne.caesar.impextutors.UI_Components.OnboardingAdapter.SliderAdapter;

public class OnboardingActivity extends AppCompatActivity {

    SliderAdapter sliderAdapter;
    TextView[] textViewDots;
    LinearLayout dotslinearLayout;
    Button previousSkipButton, nextFinishButton;
    int currentPage;
    ViewPager onboardViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        sliderAdapter = new SliderAdapter(this);
        previousSkipButton = findViewById(R.id.previousskipbutton);
        nextFinishButton = findViewById(R.id.nextfinishbutton);
        dotslinearLayout = findViewById(R.id.dotslinearlayout);
        onboardViewPager = findViewById(R.id.slideview);
        onboardViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        onboardViewPager.addOnPageChangeListener(viewpagerlistener);

        nextFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //currentpage is selected by the om page listener of the viewpager
                if (currentPage == 4){
                    Intent intent = new Intent(OnboardingActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else {
                    onboardViewPager.setCurrentItem(currentPage + 1);
                }
            }
        });

        previousSkipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //currentpage is sekected by the om page listener of the viewpager
                if (currentPage == 0) {
                Intent intent = new Intent(OnboardingActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);}
                else {
                    onboardViewPager.setCurrentItem(currentPage - 1);
                }
            }
        });
    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context, OnboardingActivity.class);
        context.startActivity(intent);
    }

    public void addDotsIndicator(int position){
        textViewDots = new TextView[5];
        dotslinearLayout.removeAllViews();
        for (int i = 0; i< textViewDots.length; i++){
            textViewDots[i] = new TextView(this);
            textViewDots[i].setText(Html.fromHtml("&#8226;"));
            textViewDots[i].setTextSize(35);
            textViewDots[i].setTextColor(getResources().getColor(R.color.light_green_A200));
            dotslinearLayout.addView(textViewDots[i]);
        }

        if (textViewDots.length > 0){
            textViewDots[position].setTextColor(getResources().getColor(R.color.light_green_800));
        }
    }

    ViewPager.OnPageChangeListener viewpagerlistener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(final int position) {
            addDotsIndicator(position);
            currentPage = position;
            if (currentPage == 0){
                previousSkipButton.setText("Skip");
                nextFinishButton.setText("Next");

            }
            else if (currentPage == 4){
                previousSkipButton.setText("Previous");
                nextFinishButton.setText("Finish");
            }
            else{
                previousSkipButton.setText("Previous");
                nextFinishButton.setText("Next");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
