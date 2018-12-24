package com.bourne.caesar.impextutors.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bourne.caesar.impextutors.R;

import java.util.ArrayList;

public class FeaturedProgramsHorizontaalAdapter extends RecyclerView.Adapter<FeaturedProgramsHorizontaalAdapter.FeaturedCoursesViewHolder> {

    ArrayList<String> titlestring, coursepricestring;
    ArrayList<Integer> couraseimagedrawable;
    Listener initializedListener;

    public interface Listener{
        void onCourseClick(int position);
    }

    public  void setListener(Listener listener){
        this.initializedListener = listener;
    }

    public FeaturedProgramsHorizontaalAdapter(ArrayList<String> titlestring, ArrayList<String> coursepricestring,
                                              ArrayList<Integer> couraseimagedrawable) {
        this.titlestring = titlestring;
        this.coursepricestring = coursepricestring;
        this.couraseimagedrawable = couraseimagedrawable;
    }

    @NonNull
    @Override
    public FeaturedCoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featuredcoursesrow,null);


        return new FeaturedCoursesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedCoursesViewHolder featuredCoursesViewHolder, final int position) {
        featuredCoursesViewHolder.courseImageView.setImageResource(couraseimagedrawable.get(position));
        featuredCoursesViewHolder.courseTitleView.setText(titlestring.get(position));
        featuredCoursesViewHolder.coursePriceView.setText(coursepricestring.get(position));

        featuredCoursesViewHolder.coursesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (initializedListener != null){
                    initializedListener.onCourseClick(position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return titlestring.size();
    }

    class FeaturedCoursesViewHolder extends RecyclerView.ViewHolder{
//        View view;
        TextView courseTitleView, coursePriceView;
        ImageView courseImageView;
        CardView coursesCardView;


        public FeaturedCoursesViewHolder(@NonNull View view) {
            super(view);
            courseTitleView = view.findViewById(R.id.featuredtitles);
            coursePriceView = view.findViewById(R.id.featurecoursesprice);
            courseImageView = view.findViewById(R.id.featuredimageviews);
            coursesCardView = view.findViewById(R.id.coursescardView);
        }
    }
}
