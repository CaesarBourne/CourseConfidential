package com.bourne.caesar.impextutors.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bourne.caesar.impextutors.Models.CoursePayStatus;
import com.bourne.caesar.impextutors.R;

import java.util.ArrayList;

public class MyPurchaseListAdapter extends RecyclerView.Adapter<MyPurchaseListAdapter.MyPurchasesViewHolder> {

    private ArrayList<CoursePayStatus> coursesPaidList;
    private Context context;

    PaidListener paidListener;

    public interface PaidListener{
        void paidCourseClick(int position);

    }

    public void setPaidListener(PaidListener paidListener) {
        this.paidListener = paidListener;
    }

    public MyPurchaseListAdapter(ArrayList<CoursePayStatus> coursesPaidList, Context context) {
        this.coursesPaidList = coursesPaidList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyPurchasesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        MyPurchasesViewHolder myPurchasesViewHolder =
                new MyPurchasesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mypurchases_row, parent, false));
        return myPurchasesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyPurchasesViewHolder myPurchasesViewHolder, final int position) {
        myPurchasesViewHolder.titleTextView.setText(coursesPaidList.get(position).getCourseTitle());
        myPurchasesViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paidListener != null){
                    paidListener.paidCourseClick(position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return coursesPaidList.size();
    }

    class MyPurchasesViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextView;
        CardView cardView;

        public MyPurchasesViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.CourseTitle);
            cardView = itemView.findViewById(R.id.coursePaidCard);
        }
    }
}
