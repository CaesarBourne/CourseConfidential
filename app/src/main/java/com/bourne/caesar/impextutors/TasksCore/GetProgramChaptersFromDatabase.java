package com.bourne.caesar.impextutors.TasksCore;

import android.support.annotation.NonNull;

import com.bourne.caesar.impextutors.Models.CourseChaptersData;
import com.bourne.caesar.impextutors.UI_Components.Activities.ProgramFeaturesActivity;
import com.bourne.caesar.impextutors.Utilities.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetProgramChaptersFromDatabase {
    ProgramFeaturesActivity programFeaturesActivity;

    public GetProgramChaptersFromDatabase(ProgramFeaturesActivity programFeaturesActivity) {
        this.programFeaturesActivity = programFeaturesActivity;
    }

    public void getProGramChapters(String childID){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.keepSynced(true);
        databaseReference.child(Constants.IMPEX_PROGRAMS).child(childID).child(Constants.IMPEX_PROGRAM_CHAPTERS)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<CourseChaptersData> chapterlist = new ArrayList<>();
                Iterator<DataSnapshot> programchapteriterator = dataSnapshot.getChildren().iterator();
                while (programchapteriterator.hasNext()){
                    DataSnapshot dataSnapshotChild = programchapteriterator.next();
                    CourseChaptersData courseChaptersDataInstance = dataSnapshotChild.getValue(CourseChaptersData.class);
                    chapterlist.add(courseChaptersDataInstance);
                }
                programFeaturesActivity.getCourseChapterSuccess(chapterlist);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
