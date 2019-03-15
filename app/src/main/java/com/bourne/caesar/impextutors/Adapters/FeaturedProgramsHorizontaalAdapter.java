package com.bourne.caesar.impextutors.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bourne.caesar.impextutors.Models.CourseFeaturesData;
import com.bourne.caesar.impextutors.R;
import com.bourne.caesar.impextutors.Utilities.Constants;
import com.bourne.caesar.impextutors.Utilities.SharedPreferencesStorage;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FeaturedProgramsHorizontaalAdapter extends RecyclerView.Adapter<FeaturedProgramsHorizontaalAdapter.FeaturedCoursesViewHolder> {

    ArrayList<CourseFeaturesData> courseFeaturesDataArrayList;
    Listener initializedListener;
    Context context;

    public interface Listener{
        void onCourseClick(int position);
    }

    public  void setListener(Listener listener){
        this.initializedListener = listener;
    }

//    public FeaturedProgramsHorizontaalAdapter(ArrayList<CourseFeaturesData> courseFeaturesDataArrayList) {
//        this.courseFeaturesDataArrayList = courseFeaturesDataArrayList;
//    }


    public FeaturedProgramsHorizontaalAdapter(ArrayList<CourseFeaturesData> courseFeaturesDataArrayList, Context context) {
        this.courseFeaturesDataArrayList = courseFeaturesDataArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public FeaturedCoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featuredcoursesrow,null);


        return new FeaturedCoursesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FeaturedCoursesViewHolder featuredCoursesViewHolder, final int position) {

               Picasso.get().load(courseFeaturesDataArrayList.get(position).getProgramImageFeatured())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(featuredCoursesViewHolder.courseImageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get().load(courseFeaturesDataArrayList.get(position)
                                .getProgramImageFeatured()).into( featuredCoursesViewHolder.courseImageView);

                    }
                });
        //        featuredCoursesViewHolder.courseImageView.setImageResource(courseFeaturesDataArrayList.get(position).getProgramImageFeatured());
        if ((SharedPreferencesStorage.getSharedPrefInstance(context).getCurrency() == Constants.IMPEX_DOLLAR)){
            featuredCoursesViewHolder.coursePriceView.setText(courseFeaturesDataArrayList.get(position).getProgramfeeDollar());
            featuredCoursesViewHolder.coursePriceView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.dollarsymbol,0,0, 0);
        }
        else{
            featuredCoursesViewHolder.coursePriceView.setText(courseFeaturesDataArrayList.get(position).getProgramfeeNaira());
            featuredCoursesViewHolder.coursePriceView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.naira,0,0, 0);
        }
//        featuredCoursesViewHolder.courseTitleView.setText(courseFeaturesDataArrayList.get(position).getProgramTitle());
//        featuredCoursesViewHolder.coursePriceView.setText(courseFeaturesDataArrayList.get(position).getProgramfeeNaira());
        //  Glide.with(this)
        //                .asBitmap()
        //                .load(Uri.parse(userPic))
        //                .into(userProfileView);

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
        return courseFeaturesDataArrayList.size();
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
