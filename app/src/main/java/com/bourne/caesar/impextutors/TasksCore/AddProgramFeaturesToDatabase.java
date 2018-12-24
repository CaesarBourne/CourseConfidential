package com.bourne.caesar.impextutors.TasksCore;

import android.support.annotation.NonNull;
import android.widget.Button;

import com.bourne.caesar.impextutors.MainActivity;
import com.bourne.caesar.impextutors.Models.CourseFeaturesData;
import com.bourne.caesar.impextutors.UI_Components.Activities.AddProgramFeaturesActivity;
import com.bourne.caesar.impextutors.Utilities.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddProgramFeaturesToDatabase {

    AddProgramFeaturesActivity addProgramFeaturesActivity;

    public AddProgramFeaturesToDatabase(AddProgramFeaturesActivity addProgramFeaturesActivity) {
        this.addProgramFeaturesActivity = addProgramFeaturesActivity;
    }

    public void AddProgamFeatures(String childID, CourseFeaturesData courseFeaturesData){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child(Constants.IMPEX_PROGRAMS)
                .child(childID).child(Constants.IMPEX_PROGRAM_FEATURES)
                .setValue(courseFeaturesData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    addProgramFeaturesActivity.addProgramsSuccess();
                }
            }
        });

        databaseReference.child(Constants.IMPEX_PROGRAMS)
                .child(childID).child(Constants.IMPEX_FEATURE_STORES)
                .setValue(courseFeaturesData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){

                        }
                    }
                });
    }
}
