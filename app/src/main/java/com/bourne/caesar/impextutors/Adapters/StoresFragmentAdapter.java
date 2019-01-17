package com.bourne.caesar.impextutors.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bourne.caesar.impextutors.Models.CourseFeaturesData;
import com.bourne.caesar.impextutors.R;
import com.bourne.caesar.impextutors.Utilities.SharedPreferencesStorage;

import java.util.List;

public class StoresFragmentAdapter extends RecyclerView.Adapter<StoresFragmentAdapter.StoresFragmentViewHolder> {
    List<CourseFeaturesData> courseFeaturesAdapterListInside;
    Listener listener;
    Context context;

    public StoresFragmentAdapter(List<CourseFeaturesData> courseFeaturesAdapterListInside, Context context) {
        this.courseFeaturesAdapterListInside = courseFeaturesAdapterListInside;
        this.context = context;
    }

    public interface Listener{
        void onCourseClicked(int position);
    }

    public void setListener(Listener listener) {

        this.listener = listener;
    }

    @NonNull
    @Override
    public StoresFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_stores_fragment_row, parent, false);

        return new StoresFragmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoresFragmentViewHolder storesFragmentViewHolder, final int position) {
        CourseFeaturesData courseFeaturesData = courseFeaturesAdapterListInside.get(position);


        if (SharedPreferencesStorage.getSharedPrefInstance(context).getCoursePayStatus(courseFeaturesData.getProgramTitle()) ==
                courseFeaturesData.getProgramTitle()){
            storesFragmentViewHolder.coursePriceView.setText("Purchased");
            storesFragmentViewHolder.courseTitleView.setText(courseFeaturesData.getProgramTitle());
            storesFragmentViewHolder.courseTitleView.setBackgroundColor( context.getResources().getColor(R.color.light_green_A700));
            storesFragmentViewHolder.coursePriceView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.purchase_success,0,0, 0);
        }
        else{
            storesFragmentViewHolder.courseTitleView.setText(courseFeaturesData.getProgramTitle());
            storesFragmentViewHolder.coursePriceView.setText(courseFeaturesData.getProgramfee());
        }

        storesFragmentViewHolder.storesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onCourseClicked(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return courseFeaturesAdapterListInside.size();
    }

    public class StoresFragmentViewHolder extends RecyclerView.ViewHolder{
        CardView storesCardView;
        TextView courseTitleView, coursePriceView;

        public StoresFragmentViewHolder(@NonNull View itemView) {
            super(itemView);
            storesCardView = itemView.findViewById(R.id.courseStoreCard);
            courseTitleView = itemView.findViewById(R.id.CourseTitle);
            coursePriceView = itemView.findViewById(R.id.coursePrice);
        }

    }
}
