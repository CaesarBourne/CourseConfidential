package com.bourne.caesar.impextutors.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bourne.caesar.impextutors.Models.CourseChaptersData;
import com.bourne.caesar.impextutors.Models.RadioPodcastData;
import com.bourne.caesar.impextutors.R;

import java.util.ArrayList;
import java.util.List;

public class RadioPodcastsAdapter extends RecyclerView.Adapter<RadioPodcastsAdapter.RadioPodcastViewHolder> {
    ArrayList<RadioPodcastData> radioPodcastDataList ;
    Context context;
    Listener listenerChild;

    public RadioPodcastsAdapter(ArrayList<RadioPodcastData> radioPodcastDataList, Context context) {
        this.radioPodcastDataList = radioPodcastDataList;
        this.context = context;
    }

    public interface Listener{
        void podcastOnClick(int position);
    }

    public void setRadioListener(RadioPodcastsAdapter.Listener listenerChild) {
        this.listenerChild = listenerChild;
    }



    @NonNull
    @Override
    public RadioPodcastViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.radio_podcast_rows, viewGroup, false);
        RadioPodcastViewHolder radioPodcastViewHolder = new RadioPodcastViewHolder(view);
        return radioPodcastViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RadioPodcastViewHolder radioPodcastViewHolder, final int position) {
        TextView titleview = radioPodcastViewHolder.radioTitleView;
        titleview.setText(radioPodcastDataList.get(position).radioPodcastTitle);
        radioPodcastViewHolder.radioDateView.setText(radioPodcastDataList.get(position).radioPodcastDate);
        radioPodcastViewHolder.radioAudioDurationView.setText(radioPodcastDataList.get(position).radioPodcastDuration);
        int radionumber = 0;

//        for (int i=0; i <radioPodcastDataList.size(); i++){
//            radionumber = i+1;
//            break;
//        }
        radioPodcastViewHolder.radioNumberView.setText(String.valueOf(position+1));


        radioPodcastViewHolder.constraintLayoutParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listenerChild != null){
                    listenerChild.podcastOnClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return radioPodcastDataList.size();
    }

    class RadioPodcastViewHolder extends RecyclerView.ViewHolder{
        TextView radioTitleView, radioNumberView, radioAudioDurationView, radioDateView;
        ConstraintLayout constraintLayoutParent;

        public RadioPodcastViewHolder(@NonNull View itemView) {
            super(itemView);
            radioTitleView= itemView.findViewById(R.id.radioTitle);
            radioNumberView = itemView.findViewById(R.id.radionumber);
            radioDateView = itemView.findViewById(R.id.radioDate);
            constraintLayoutParent = itemView.findViewById(R.id.radioParentRow);
            radioAudioDurationView = itemView.findViewById(R.id.radioPodcastDuration);
        }
    }
}
