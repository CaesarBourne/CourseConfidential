package com.bourne.caesar.impextutors.FirebaseTasksCore;

import android.support.annotation.NonNull;

import com.bourne.caesar.impextutors.Models.NewsLetterData;
import com.bourne.caesar.impextutors.Models.RadioPodcastData;
import com.bourne.caesar.impextutors.UI_Components.Activities.AddNewsLetterToFirebaseActivity;
import com.bourne.caesar.impextutors.Utilities.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewsLetterToFirebase {
    AddNewsLetterToFirebaseActivity addNewsLetterToFirebaseActivity;

    public AddNewsLetterToFirebase(AddNewsLetterToFirebaseActivity addNewsLetterToFirebaseActivity) {
        this.addNewsLetterToFirebaseActivity = addNewsLetterToFirebaseActivity;
    }

    public void AddNewsLetter(NewsLetterData newsLetterData, String podcastID){


        FirebaseDatabase.getInstance().getReference()
                .child(Constants.IMPEX_NEWSLETTERS).child(Constants.IMPEX_NEWS).child( podcastID)
                .setValue(newsLetterData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        addNewsLetterToFirebaseActivity.addNewsLetterSuccess();

                    }
                });
    }
}


