package com.bourne.caesar.impextutors.FirebaseTasksCore;

import android.support.annotation.NonNull;

import com.bourne.caesar.impextutors.Models.RadioPodcastData;
import com.bourne.caesar.impextutors.UI_Components.Activities.RadioPodcastsActivity;
import com.bourne.caesar.impextutors.Utilities.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class GetRadioPodcastsFromFirebase {

    RadioPodcastsActivity radioPodcastsActivity;

    public GetRadioPodcastsFromFirebase(RadioPodcastsActivity radioPodcastsActivity) {
        this.radioPodcastsActivity = radioPodcastsActivity;
    }

    public void getRadioPodcasts(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.keepSynced(true);
        databaseReference.child(Constants.IMPEX_RADIO_PODCASTS).child(Constants.IMPEX_RADIO_FILES)
            .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ArrayList<RadioPodcastData> radioPodcastList = new ArrayList<>();
                    Iterator<DataSnapshot> raadiopodcastiterator = dataSnapshot.getChildren().iterator();
                    while (raadiopodcastiterator.hasNext()){
                        DataSnapshot dataSnapshotChildren = raadiopodcastiterator.next();
                        RadioPodcastData radioPodcastDataInstance = dataSnapshotChildren.getValue(RadioPodcastData.class);
                        radioPodcastList.add(radioPodcastDataInstance);
                    }
                    radioPodcastsActivity.getRadioPodcastSuccess(radioPodcastList);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
    }
}
