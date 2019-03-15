package com.bourne.caesar.impextutors.FirebaseTasksCore;

import android.support.annotation.NonNull;

import com.bourne.caesar.impextutors.Models.CourseChaptersData;
import com.bourne.caesar.impextutors.Models.CourseFeaturesData;
import com.bourne.caesar.impextutors.Models.RadioPodcastData;
import com.bourne.caesar.impextutors.UI_Components.Activities.AddProgramFeaturesActivity;
import com.bourne.caesar.impextutors.UI_Components.Activities.AddRadioPodcaastActivityToFirebase;
import com.bourne.caesar.impextutors.Utilities.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddRadioPodcastsToFirebase {

AddRadioPodcaastActivityToFirebase addRadioPodcaastActivityToFirebase;

    public AddRadioPodcastsToFirebase(AddRadioPodcaastActivityToFirebase addRadioPodcaastActivityToFirebase) {
        this.addRadioPodcaastActivityToFirebase = addRadioPodcaastActivityToFirebase;
    }

    public void AddRadioPodcasts( RadioPodcastData radioPodcastData, String podcastID){


        FirebaseDatabase.getInstance().getReference()
                .child(Constants.IMPEX_RADIO_PODCASTS).child(Constants.IMPEX_RADIO_FILES).child( podcastID)
                .setValue(radioPodcastData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        addRadioPodcaastActivityToFirebase.addRadioPodcastSuccess();

                    }
                });
    }
}
