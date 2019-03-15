package com.bourne.caesar.impextutors.FirebaseTasksCore;

import android.support.annotation.NonNull;

import com.bourne.caesar.impextutors.Models.FeatureCoursesData;
import com.bourne.caesar.impextutors.UI_Components.Activities.AddFeaturedCoursesActivitry;
import com.bourne.caesar.impextutors.Utilities.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteFeaturedCoursesFromFirebase {

    AddFeaturedCoursesActivitry addFeaturedCoursesActivitry;

    public DeleteFeaturedCoursesFromFirebase(AddFeaturedCoursesActivitry addFeaturedCoursesActivitry) {
        this.addFeaturedCoursesActivitry = addFeaturedCoursesActivitry;
    }

    public void deleteFeatureCourses(final FeatureCoursesData featureCoursesData){
        FirebaseDatabase.getInstance().getReference()
                .child(Constants.IMPEX_FEATURED_COURSES)
                .child(Constants.IMPEX_FEATURED_LIST).child(featureCoursesData.featureCourseName)
                .removeValue()
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
