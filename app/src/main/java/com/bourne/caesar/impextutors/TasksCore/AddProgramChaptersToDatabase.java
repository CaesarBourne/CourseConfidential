package com.bourne.caesar.impextutors.TasksCore;

import android.support.annotation.NonNull;

import com.bourne.caesar.impextutors.MainActivity;
import com.bourne.caesar.impextutors.Models.CourseChaptersData;
import com.bourne.caesar.impextutors.Models.CourseFeaturesData;
import com.bourne.caesar.impextutors.UI_Components.Activities.AddProgramChaptersActivity;
import com.bourne.caesar.impextutors.Utilities.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class AddProgramChaptersToDatabase {

    AddProgramChaptersActivity addProgramChaptersActivity;

    public AddProgramChaptersToDatabase(AddProgramChaptersActivity addProgramChaptersActivity) {
        this.addProgramChaptersActivity = addProgramChaptersActivity;
    }

    public void AddProgamChapters(String chapterID, CourseChaptersData courseChaptersData, String ProgramID){

        FirebaseDatabase.getInstance().getReference().child(Constants.IMPEX_PROGRAMS)
                .child(ProgramID).child(Constants.IMPEX_PROGRAM_CHAPTERS).child(chapterID).setValue(courseChaptersData)
        .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                addProgramChaptersActivity.addProgramChaptersSuccess();
            }
        });
    }
}
