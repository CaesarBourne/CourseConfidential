package com.bourne.caesar.impextutors.FirebaseTasksCore;

import android.support.annotation.NonNull;

import com.bourne.caesar.impextutors.Models.FeatureCoursesData;
import com.bourne.caesar.impextutors.UI_Components.Activities.AddFeaturedCoursesActivitry;
import com.bourne.caesar.impextutors.Utilities.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class GetFeatruredCouraseListForAddFeatured {

    AddFeaturedCoursesActivitry addFeaturedCoursesActivitry;

    public GetFeatruredCouraseListForAddFeatured(AddFeaturedCoursesActivitry addFeaturedCoursesActivitry) {
        this.addFeaturedCoursesActivitry = addFeaturedCoursesActivitry;
    }

    public void getFeaturedCourses(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.keepSynced(true);
        databaseReference .child(Constants.IMPEX_FEATURED_COURSES)
                .child(Constants.IMPEX_FEATURED_LIST)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<FeatureCoursesData> featureCoursesList = new ArrayList<>();
                        Iterator<DataSnapshot> raadiopodcastiterator = dataSnapshot.getChildren().iterator();
                        while (raadiopodcastiterator.hasNext()){
                            DataSnapshot dataSnapshotChildren = raadiopodcastiterator.next();
                            FeatureCoursesData featureCoursesDataInstance = dataSnapshotChildren.getValue(FeatureCoursesData.class);
                            featureCoursesList.add(featureCoursesDataInstance);
                        }
                        addFeaturedCoursesActivitry.getFeaturedCourseList(featureCoursesList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
