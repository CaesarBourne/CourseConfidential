package com.bourne.caesar.impextutors.UI_Components.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bourne.caesar.impextutors.R;

public class NewsLetterDetailsActivity extends AppCompatActivity {

    public static final String NEWS_TITLE="newstitle";
    public static final String NEWS_IMAGE="newsimage";
    public static final String NEWS_CONTENT="newscontent";
    public static final String NEWS_DURATION_READ="newsduration";
    public static final String NEWS_DATE="newsdate";

    private TextView newsTitleView, newsContentView, newsDateView, newsReadDurationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_letter_details);
        initialization();
        if (getIntent().getExtras() != null){
            newsTitleView.setText(getIntent().getStringExtra(NEWS_TITLE));
            newsContentView.setText(getIntent().getStringExtra(NEWS_CONTENT));
            newsDateView.setText(getIntent().getStringExtra(NEWS_DATE));
            newsReadDurationView.setText(getIntent().getStringExtra(NEWS_DURATION_READ));
        }
    }

    private void initialization() {

        newsTitleView = findViewById(R.id.newsTitleDetail);
        newsContentView = findViewById(R.id.newsContentDetail);
        newsDateView = findViewById(R.id.newsDateDetail);
        newsReadDurationView = findViewById(R.id.newsReadDurationDetail);
    }
}
