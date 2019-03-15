package com.bourne.caesar.impextutors.FirebaseTasksCore;

import android.support.annotation.NonNull;

import com.bourne.caesar.impextutors.Models.CourseFeaturesData;
import com.bourne.caesar.impextutors.Models.FeatureCoursesData;
import com.bourne.caesar.impextutors.UI_Components.Fragments.HomeFragment;
import com.bourne.caesar.impextutors.UI_Components.Fragments.StoresFragment;
import com.bourne.caesar.impextutors.Utilities.Constants;
import com.bourne.caesar.impextutors.Utilities.FixedData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GetProgramFeaturesForHomeFragment {
    HomeFragment homeFragmentImstance;
    String courseID;

    ArrayList<CourseFeaturesData> courseFeaturesDataArrayList;
    ArrayList<String> couraseimages = new ArrayList<>();

    public GetProgramFeaturesForHomeFragment(HomeFragment homeFragmentImstance) {
        this.homeFragmentImstance = homeFragmentImstance;
    }

    public void getFeaturedProgramFeatures(ArrayList<FeatureCoursesData> featureCoursesList){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.keepSynced(true);

        for (int i = 0; i < featureCoursesList.size(); i++) {
            courseID = featureCoursesList.get(i).featureCourseName;

            courseFeaturesDataArrayList = new ArrayList<>();
            databaseReference .child((Constants.IMPEX_PROGRAMS)).child(courseID).child(Constants.IMPEX_PROGRAM_FEATURES)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                           CourseFeaturesData courseFeaturesData = dataSnapshot.getValue(CourseFeaturesData.class);
                            courseFeaturesDataArrayList.add(courseFeaturesData);
                            homeFragmentImstance.getProgramFeaturesSuccess(courseFeaturesDataArrayList);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
        }

    }

}
