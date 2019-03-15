package com.bourne.caesar.impextutors.UI_Components.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bourne.caesar.impextutors.Adapters.MyPurchaseListAdapter;
import com.bourne.caesar.impextutors.FirebaseTasksCore.GetMyPurchasedCourses;
import com.bourne.caesar.impextutors.Models.CoursePayStatus;
import com.bourne.caesar.impextutors.R;

import java.util.ArrayList;

public class MyPurchasesActivity extends AppCompatActivity {

    private RecyclerView paidRecycler;
    private MyPurchaseListAdapter myPurchaseListAdapter;
    private GetMyPurchasedCourses getMyPurchasedCourses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_purchases);
        initialization();
        getMyPurchasedCourses.getMyPurchases();
    }

    private void initialization() {
        paidRecycler = findViewById(R.id.myPurchasesList);
        getMyPurchasedCourses = new GetMyPurchasedCourses(this);
    }

    public void getMyPurchasesSuccess(final ArrayList<CoursePayStatus> coursesPaidList){

        myPurchaseListAdapter = new MyPurchaseListAdapter(coursesPaidList, this);
        myPurchaseListAdapter.setPaidListener(new MyPurchaseListAdapter.PaidListener() {
            @Override
            public void paidCourseClick(int position) {
                Intent intent = new Intent(MyPurchasesActivity.this, ProgramFeaturesActivity.class);
                intent.putExtra(ProgramFeaturesActivity.PROGRAM_ID, coursesPaidList.get(position).getCourseID());
                startActivity(intent);
            }
        });
        paidRecycler.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        paidRecycler.setLayoutManager(linearLayoutManager);
        paidRecycler.setAdapter(myPurchaseListAdapter);
    }
}
