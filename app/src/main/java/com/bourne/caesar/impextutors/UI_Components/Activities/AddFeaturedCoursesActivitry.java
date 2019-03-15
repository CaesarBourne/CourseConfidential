package com.bourne.caesar.impextutors.UI_Components.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.bourne.caesar.impextutors.FirebaseTasksCore.AddFeaturedCoursesToFirebase;
import com.bourne.caesar.impextutors.FirebaseTasksCore.DeleteFeaturedCoursesFromFirebase;
import com.bourne.caesar.impextutors.FirebaseTasksCore.GetFeatruredCouraseListForAddFeatured;
import com.bourne.caesar.impextutors.Models.FeatureCoursesData;
import com.bourne.caesar.impextutors.R;
import com.bourne.caesar.impextutors.Utilities.Constants;

import java.util.ArrayList;

public class AddFeaturedCoursesActivitry extends AppCompatActivity {

    CheckBox basicCheckbox, intermidiateCheckbox, advancedCheckbox,
            customerServiceCheckox, tradeFinanceCheckbox, businessManagementCheckbox;
    boolean fearturedCourseGotten;
    private View mLoginFormView;
    private View mProgressView;
    private Toolbar featureToolbar;
    private AddFeaturedCoursesToFirebase addFeaturedCoursesToFirebase;
    private DeleteFeaturedCoursesFromFirebase deleteFeaturedCoursesFromFirebase;
    private GetFeatruredCouraseListForAddFeatured getFeatruredCouraseListForAddFeatured;
    private  ArrayList<FeatureCoursesData> featureCoursesDataInsideList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_featured_courses_activitry);
        initialization();
        getFeatruredCouraseListForAddFeatured.getFeaturedCourses();
//        showProgress(true);



        setSupportActionBar(featureToolbar);
        getSupportActionBar().setTitle("Add Featured Courses");

    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private void initialization() {
        featureToolbar = findViewById(R.id.addfeatureCoursreToolBar);
        basicCheckbox = findViewById( R.id.basicCheckbox);
        intermidiateCheckbox = findViewById(R.id.intermidiateCheckbox);
        advancedCheckbox =findViewById(R.id.advancedCheckbox);
        customerServiceCheckox = findViewById(R.id.customerServicCheckbox);
        tradeFinanceCheckbox = findViewById(R.id.tradefinaceCheckbox);
        businessManagementCheckbox = findViewById(R.id.businessManagementCheckbox);
        addFeaturedCoursesToFirebase = new AddFeaturedCoursesToFirebase(this);

        mLoginFormView = findViewById(R.id.checkboxform);
        mProgressView = findViewById(R.id.featured_progress);

        getFeatruredCouraseListForAddFeatured = new GetFeatruredCouraseListForAddFeatured(this);
        deleteFeaturedCoursesFromFirebase = new DeleteFeaturedCoursesFromFirebase(this);
    }
    public void addFeatureCourseSuccesfull(String courseName){
        Toast.makeText(this, "The COURSE: "+ courseName+ " is added succesfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

//        if (fearturedCourseGotten){
//            showProgress(false);
//            if (featureCoursesDataInsideList.contains(Constants.IMPEX_BASIC));{
//                basicCheckbox.setChecked(true);
//            }
//            if (featureCoursesDataInsideList.contains(Constants.IMPEX_INTERMIDIATE));{
//                intermidiateCheckbox.setChecked(true);
//            }
//            if (featureCoursesDataInsideList.contains(Constants.IMPEX_ADVANCE));{
//                advancedCheckbox.setChecked(true);
//            }
//            if (featureCoursesDataInsideList.contains(Constants.IMPEX_CUSTOMER_SERVICE));{
//                customerServiceCheckox.setChecked(true);
//            }
//            if (featureCoursesDataInsideList.contains(Constants.IMPEX_TRADE_FINANCE));{
//                tradeFinanceCheckbox.setChecked(true);
//            }
//            if (featureCoursesDataInsideList.contains(Constants.IMPEX_BUSINESS_MANAGEMENT));{
//                businessManagementCheckbox.setChecked(true);
//            }

     //   }
    }

    public void getFeaturedCourseList(final ArrayList<FeatureCoursesData> featureCoursesDataArrayList){
        fearturedCourseGotten= true;
        featureCoursesDataInsideList  = featureCoursesDataArrayList;
    }

    public void onFeatureCourse(View view){
        boolean checked = ((CheckBox) view).isChecked();

        FeatureCoursesData featureCoursesData;
        switch (view.getId()){
            case R.id.basicCheckbox:
                featureCoursesData = new FeatureCoursesData(Constants.IMPEX_BASIC);
                if (checked){
                    addFeaturedCoursesToFirebase.addFeaturedCourses(featureCoursesData);
                }else {
                    deleteFeaturedCoursesFromFirebase.deleteFeatureCourses(featureCoursesData);
                }
                break;
            case R.id.intermidiateCheckbox:
                featureCoursesData = new FeatureCoursesData(Constants.IMPEX_INTERMIDIATE);
                if (checked){
                    addFeaturedCoursesToFirebase.addFeaturedCourses(featureCoursesData);
                }else {
                    deleteFeaturedCoursesFromFirebase.deleteFeatureCourses(featureCoursesData);
                }
                break;
            case R.id.advancedCheckbox:
                featureCoursesData = new FeatureCoursesData(Constants.IMPEX_ADVANCE);
                if (checked){
                    addFeaturedCoursesToFirebase.addFeaturedCourses(featureCoursesData);
                }else {
                    deleteFeaturedCoursesFromFirebase.deleteFeatureCourses(featureCoursesData);
                }
                break;
            case R.id.customerServicCheckbox:
                featureCoursesData = new FeatureCoursesData(Constants.IMPEX_CUSTOMER_SERVICE);
                if (checked){
                    addFeaturedCoursesToFirebase.addFeaturedCourses(featureCoursesData);
                }else {
                    deleteFeaturedCoursesFromFirebase.deleteFeatureCourses(featureCoursesData);
                }
                break;
            case R.id.tradefinaceCheckbox:
                featureCoursesData = new FeatureCoursesData(Constants.IMPEX_TRADE_FINANCE);
                if (checked){
                    addFeaturedCoursesToFirebase.addFeaturedCourses(featureCoursesData);
                }else {
                    deleteFeaturedCoursesFromFirebase.deleteFeatureCourses(featureCoursesData);
                }
                break;
            case R.id.businessManagementCheckbox:
                featureCoursesData = new FeatureCoursesData(Constants.IMPEX_BUSINESS_MANAGEMENT);
                if (checked){
                    addFeaturedCoursesToFirebase.addFeaturedCourses(featureCoursesData);
                }else {
                    deleteFeaturedCoursesFromFirebase.deleteFeatureCourses(featureCoursesData);
                }
                break;
        }
    }

}
