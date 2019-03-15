package com.bourne.caesar.impextutors.FirebaseTasksCore;

import android.support.annotation.NonNull;

import com.bourne.caesar.impextutors.Models.NewsLetterData;
import com.bourne.caesar.impextutors.UI_Components.Activities.NewsLetterListActivity;
import com.bourne.caesar.impextutors.Utilities.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class GetNewsletterFromFirebase {
    NewsLetterListActivity newsLetterListActivity;

    public GetNewsletterFromFirebase(NewsLetterListActivity newsLetterListActivity) {
        this.newsLetterListActivity = newsLetterListActivity;
    }

    public void getNewsLetter(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.keepSynced(true);
        databaseReference.child(Constants.IMPEX_NEWSLETTERS).child(Constants.IMPEX_NEWS)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<NewsLetterData> newsLetterDataArrayList = new ArrayList<>();
                        Iterator<DataSnapshot> raadiopodcastiterator = dataSnapshot.getChildren().iterator();
                        while (raadiopodcastiterator.hasNext()){
                            DataSnapshot dataSnapshotChildren = raadiopodcastiterator.next();
                            NewsLetterData newsLetterDataInstance = dataSnapshotChildren.getValue(NewsLetterData.class);
                            newsLetterDataArrayList.add(newsLetterDataInstance);
                        }
                        newsLetterListActivity.getNewsLetterSuccess(newsLetterDataArrayList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
