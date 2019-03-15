package com.bourne.caesar.impextutors.UI_Components.Activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;

import com.bourne.caesar.impextutors.Adapters.NewsLetterAdapter;
import com.bourne.caesar.impextutors.FirebaseTasksCore.GetNewsletterFromFirebase;
import com.bourne.caesar.impextutors.Models.NewsLetterData;
import com.bourne.caesar.impextutors.R;

import java.util.ArrayList;

public class NewsLetterListActivity extends AppCompatActivity {


    private RecyclerView newsLetterRecyclerView;
    private NewsLetterAdapter newsLetterAdapter;

    private GetNewsletterFromFirebase getNewsletterFromFirebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_letter);
        initialization();
        getNewsletterFromFirebase.getNewsLetter();
    }

    private void initialization() {
        newsLetterRecyclerView = findViewById(R.id.newsLetterRecycler);
        getNewsletterFromFirebase = new GetNewsletterFromFirebase(this);
    }


    public void getNewsLetterSuccess(final ArrayList<NewsLetterData> newsLetterDataArrayList){

        newsLetterAdapter = new NewsLetterAdapter(newsLetterDataArrayList, NewsLetterListActivity.this);



//        newsLetterAdapter.setListenerChild(new NewsLetterAdapter.NewsListener() {
//            @Override
//            public void newsOnClick(int position) {
//                Intent intent = new Intent(NewsLetterListActivity.this, NewsLetterDetailsActivity.class);
//                Pair [] pair = new Pair[4];
//
//                ActivityOptions animateOption  = ActivityOptions.makeSceneTransitionAnimation(NewsLetterListActivity.this,pair);
//                startActivity(intent);
//
//            }
//        });
        newsLetterAdapter.notifyDataSetChanged();
        newsLetterRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this, LinearLayoutManager.VERTICAL,false);
        newsLetterRecyclerView.setLayoutManager(linearLayoutManager);
        newsLetterRecyclerView.setAdapter(newsLetterAdapter);
    }

}

