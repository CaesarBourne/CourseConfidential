package com.bourne.caesar.impextutors.FirebaseTasksCore;

import android.support.annotation.NonNull;

import com.bourne.caesar.impextutors.Models.CourseFeaturesData;
import com.bourne.caesar.impextutors.UI_Components.Activities.ProgramFeaturesActivity;
import com.bourne.caesar.impextutors.Utilities.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class GetProgramFeaturesFromFirebase {
    ProgramFeaturesActivity programFeaturesActivity;

    public GetProgramFeaturesFromFirebase(ProgramFeaturesActivity programFeaturesActivity) {
        this.programFeaturesActivity = programFeaturesActivity;
    }

    public void getProgramFeatures(String programID){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.keepSynced(true);
        databaseReference.child(Constants.IMPEX_PROGRAMS).child(programID).child(Constants.IMPEX_PROGRAM_FEATURES)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> dataSnapshotIterator = dataSnapshot.getChildren().iterator();
                       CourseFeaturesData courseFeaturesData = dataSnapshot.getValue(CourseFeaturesData.class);
                       programFeaturesActivity.getProgramFeaturesSuccess(courseFeaturesData);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {


                    }
                });
    }
}
