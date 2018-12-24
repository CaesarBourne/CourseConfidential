package com.bourne.caesar.impextutors.TasksCore;

import android.support.annotation.NonNull;

import com.bourne.caesar.impextutors.Models.CourseFeaturesData;
import com.bourne.caesar.impextutors.UI_Components.Activities.ProgramFeaturesActivity;
import com.bourne.caesar.impextutors.UI_Components.Fragments.StoresFragment;
import com.bourne.caesar.impextutors.Utilities.Constants;
import com.bourne.caesar.impextutors.Utilities.FixedData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetProgramFeaturesForStores {
    StoresFragment storesFragmentInstaance;
    String courseID;

    List<CourseFeaturesData> courseFeaturesDataList;
    ArrayList<String> couraseimages = new ArrayList<>();

    public GetProgramFeaturesForStores(StoresFragment storesFragmentInstaance) {
        this.storesFragmentInstaance = storesFragmentInstaance;
    }

    public void getProgramFeatures(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.keepSynced(true);

        for (int i= 0; i < FixedData.CourseID.length;i++) {
            courseID = (FixedData.CourseID[i]);

            courseFeaturesDataList = new ArrayList<>();
            databaseReference.child((Constants.IMPEX_PROGRAMS)).child(courseID).child(Constants.IMPEX_PROGRAM_FEATURES)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            CourseFeaturesData courseFeaturesData = dataSnapshot.getValue(CourseFeaturesData.class);
                            courseFeaturesDataList.add(courseFeaturesData);
                            storesFragmentInstaance.getProgramFeaturesSuccess(courseFeaturesDataList);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
        }

    }
}
