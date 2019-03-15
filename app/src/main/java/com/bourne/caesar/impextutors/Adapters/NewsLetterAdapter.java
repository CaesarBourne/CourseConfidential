package com.bourne.caesar.impextutors.Adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bourne.caesar.impextutors.Models.NewsLetterData;
import com.bourne.caesar.impextutors.Models.RadioPodcastData;
import com.bourne.caesar.impextutors.R;
import com.bourne.caesar.impextutors.UI_Components.Activities.NewsLetterDetailsActivity;
import com.bourne.caesar.impextutors.UI_Components.Activities.NewsLetterListActivity;

import java.util.ArrayList;

public class NewsLetterAdapter extends RecyclerView.Adapter<NewsLetterAdapter.NewsLetterViewHolder>  {
    ArrayList<NewsLetterData> newsLetterAdapterArrayList ;
    Context context;
    NewsListener listenerChild;

    public NewsLetterAdapter(ArrayList<NewsLetterData> newsLetterAdapterArrayList, Context context) {
        this.newsLetterAdapterArrayList = newsLetterAdapterArrayList;
        this.context = context;
    }

    public interface NewsListener{
        void newsOnClick(int position);
    }

    public void setListenerChild(NewsListener listenerChild) {
        this.listenerChild = listenerChild;
    }

    @NonNull
    @Override
    public NewsLetterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.newsletter_list_row, viewGroup, false);
        NewsLetterViewHolder newssLetterViewHolder = new NewsLetterViewHolder(view);
        return newssLetterViewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull  NewsLetterViewHolder newssLetterViewHolder, int position) {
        final String newsTitle = newsLetterAdapterArrayList.get(position).newsTitle;
        String newsContentPreview =newsLetterAdapterArrayList.get(position).newsContentPreView;
        final String newsDate = newsLetterAdapterArrayList.get(position).newsDate;
        final String newsReadDuration = newsLetterAdapterArrayList.get(position).newsReadDuration;
        final String newsContent = newsLetterAdapterArrayList.get(position).newsContent;

        newssLetterViewHolder.newsTitleView.setText(newsTitle);
        newssLetterViewHolder.newsContentPreviewView.setText(newsContentPreview);
        newssLetterViewHolder.newsDateView.setText(newsDate);
        newssLetterViewHolder.newsReadDurationView.setText(newsReadDuration);

        final TextView titleText = newssLetterViewHolder.newsTitleView;
        final TextView contentPreview = newssLetterViewHolder.newsContentPreviewView;
        final TextView dateText =newssLetterViewHolder.newsDateView;
        final TextView duratioText = newssLetterViewHolder.newsReadDurationView;

        newssLetterViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsLetterDetailsActivity.class);
                intent.putExtra(NewsLetterDetailsActivity.NEWS_TITLE, newsTitle);
                intent.putExtra(NewsLetterDetailsActivity.NEWS_CONTENT, newsContent );
                intent.putExtra(NewsLetterDetailsActivity.NEWS_DATE, newsDate);
                intent.putExtra(NewsLetterDetailsActivity.NEWS_DURATION_READ, newsReadDuration);
                Pair[] pair = new Pair[4];

                pair[0]= new Pair< View, String>( titleText,
                        context.getResources().getString(R.string.newstitle_transition));

                pair[1]= new Pair< View, String>(contentPreview ,
               context.getResources().getString(R.string.newsdescription_transition));

                pair[2]= new Pair< View, String>(dateText ,
                        context.getResources().getString(R.string.newsdate_transition));

                pair[3]= new Pair< View, String>( duratioText,
                        context.getResources().getString(R.string.newsreaduration_transition));
                Activity activity = (Activity) context;

                ActivityOptions animateOption  = ActivityOptions.makeSceneTransitionAnimation(activity, pair);
                context.startActivity(intent, animateOption.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsLetterAdapterArrayList.size();
    }

    class NewsLetterViewHolder extends RecyclerView.ViewHolder{
        TextView newsTitleView, newsReadDurationView, newsContentPreviewView, newsDateView;
        CardView cardView;
        public NewsLetterViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitleView = itemView.findViewById(R.id.newsTitleView);
            newsReadDurationView = itemView.findViewById(R.id.readDurationView);
            newsContentPreviewView = itemView.findViewById(R.id.newsContentView);
            newsDateView = itemView.findViewById(R.id.newsDateView);
            cardView = itemView.findViewById(R.id.newsLetterCard);
        }
    }
}
