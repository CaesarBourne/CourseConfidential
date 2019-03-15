package com.bourne.caesar.impextutors.FirebaseTasksCore;

import android.support.annotation.NonNull;

import com.bourne.caesar.impextutors.Models.CoursePayStatus;
import com.bourne.caesar.impextutors.UI_Components.Activities.MyPurchasesActivity;
import com.bourne.caesar.impextutors.Utilities.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class GetMyPurchasedCourses {

    MyPurchasesActivity myPurchasesActivity;

    public GetMyPurchasedCourses(MyPurchasesActivity myPurchasesActivity) {
        this.myPurchasesActivity = myPurchasesActivity;
    }

    public void getMyPurchases(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.keepSynced(true);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference.child(Constants.IMPEX_USER)
                .child(firebaseUser.getUid())
                .child(Constants.IMPEX_PAID_USER)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<CoursePayStatus> coursesPaidList = new ArrayList<>();
                        Iterator<DataSnapshot> programchapteriterator = dataSnapshot.getChildren().iterator();
                        while (programchapteriterator.hasNext()){
                            DataSnapshot dataSnapshotChild = programchapteriterator.next();
                            CoursePayStatus coursePayStatusDataInstance = dataSnapshotChild.getValue(CoursePayStatus.class);
                            coursesPaidList.add(coursePayStatusDataInstance);
                        }
                        myPurchasesActivity.getMyPurchasesSuccess(coursesPaidList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
