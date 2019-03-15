package com.bourne.caesar.impextutors.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bourne.caesar.impextutors.Models.CourseChaptersData;
import com.bourne.caesar.impextutors.R;
import com.bourne.caesar.impextutors.UI_Components.Activities.ChaptersDetailActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ProgramChaptersAdapter extends RecyclerView.Adapter<ProgramChaptersAdapter.ProgramChaptersViewHolder> {

    ArrayList<CourseChaptersData> courseChaptersDataList ;
    Context context;
    Listener listenerChild;


    public interface Listener{
        void chapterOnClick(int position);
    }

    public void setListenerChild(Listener listenerChild) {
        this.listenerChild = listenerChild;
    }

    public ProgramChaptersAdapter(ArrayList<CourseChaptersData> courseChaptersDataList, Context context) {
        this.courseChaptersDataList = courseChaptersDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProgramChaptersViewHolder onCreateViewHolder(@NonNull ViewGroup viewgroupparent, int i) {
        View view = LayoutInflater.from(viewgroupparent.getContext()).inflate(R.layout.course_chapter_rows, viewgroupparent, false);
        ProgramChaptersViewHolder programChaptersViewHolder = new ProgramChaptersViewHolder(view);
        return programChaptersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramChaptersViewHolder programChaptersViewHolder, final int position) {
         TextView titleview = programChaptersViewHolder.chapterTitleView;
        programChaptersViewHolder.chapterTitleView.setText(courseChaptersDataList.get(position).CourseTitle);
        programChaptersViewHolder.chapterNumberView.setText(String.valueOf(position+1));
        programChaptersViewHolder.chapterVideoDurationView.setText(courseChaptersDataList.get(position).CourseVideoDuration);

        programChaptersViewHolder.constraintLayoutParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listenerChild != null){
                    listenerChild.chapterOnClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (courseChaptersDataList != null){
            return courseChaptersDataList.size();
        }

        return 0;
    }

    class ProgramChaptersViewHolder extends RecyclerView.ViewHolder{
        TextView chapterTitleView, chapterNumberView, chapterVideoDurationView;
        ConstraintLayout constraintLayoutParent;

        public ProgramChaptersViewHolder(@NonNull View itemView) {
            super(itemView);
            chapterTitleView = itemView.findViewById(R.id.chapterTitle);
            chapterNumberView = itemView.findViewById(R.id.chapternumber);
            chapterVideoDurationView = itemView.findViewById(R.id.chapterVideoDuration);
            constraintLayoutParent = itemView.findViewById(R.id.constraintParent);

        }
    }
}
