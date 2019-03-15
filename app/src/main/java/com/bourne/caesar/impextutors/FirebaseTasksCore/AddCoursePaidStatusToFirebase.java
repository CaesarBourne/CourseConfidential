package com.bourne.caesar.impextutors.FirebaseTasksCore;

import android.support.annotation.NonNull;

import com.bourne.caesar.impextutors.Models.CoursePayStatus;
import com.bourne.caesar.impextutors.UI_Components.Activities.CheckoutPayActiviy;
import com.bourne.caesar.impextutors.Utilities.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class AddCoursePaidStatusToFirebase {
    CheckoutPayActiviy checkoutPayActiviy;

    public AddCoursePaidStatusToFirebase(CheckoutPayActiviy checkoutPayActiviy) {
        this.checkoutPayActiviy = checkoutPayActiviy;
    }

    public void addCoursePaidStatus(final String courseTitle, final CoursePayStatus courseID){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase.getInstance().getReference().child(Constants.IMPEX_USER)
                .child(firebaseUser.getUid()).child(Constants.IMPEX_PAID_USER)
                .child(courseTitle).setValue(courseID).addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        checkoutPayActiviy.addCoursesPayStatusSuccesful(courseTitle);
                    }
                }
        );
    }
}
