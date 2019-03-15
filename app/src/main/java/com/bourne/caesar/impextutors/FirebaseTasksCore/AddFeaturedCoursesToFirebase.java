package com.bourne.caesar.impextutors.FirebaseTasksCore;

import android.support.annotation.NonNull;

import com.bourne.caesar.impextutors.Models.CoursePayStatus;
import com.bourne.caesar.impextutors.Models.FeatureCoursesData;
import com.bourne.caesar.impextutors.UI_Components.Activities.AddFeaturedCoursesActivitry;
import com.bourne.caesar.impextutors.UI_Components.Activities.CheckoutPayActiviy;
import com.bourne.caesar.impextutors.Utilities.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class AddFeaturedCoursesToFirebase {

    AddFeaturedCoursesActivitry addFeaturedCoursesActivitry;

    public AddFeaturedCoursesToFirebase(AddFeaturedCoursesActivitry addFeaturedCoursesActivitry) {
        this.addFeaturedCoursesActivitry = addFeaturedCoursesActivitry;
    }

    public void addFeaturedCourses(final FeatureCoursesData featureCoursesData){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase.getInstance().getReference()
                .child(Constants.IMPEX_FEATURED_COURSES)
                .child(Constants.IMPEX_FEATURED_LIST).child(featureCoursesData.featureCourseName)
                .setValue(featureCoursesData)
                .addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        addFeaturedCoursesActivitry.addFeatureCourseSuccesfull(featureCoursesData.featureCourseName);
                    }
                }
        );
    }

}
