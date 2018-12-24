package com.bourne.caesar.impextutors.TasksCore;

import android.support.annotation.NonNull;

import com.bourne.caesar.impextutors.Models.User;
import com.bourne.caesar.impextutors.UI_Components.Activities.RegisterActivity;
import com.bourne.caesar.impextutors.Utilities.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddImpexUserToFirebase {

    RegisterActivity registerActivityInstance;

    public AddImpexUserToFirebase(RegisterActivity registerActivityInstance) {
        this.registerActivityInstance = registerActivityInstance;
    }

    public void addUserToFirebaseDatabase(FirebaseUser firebaseUser, String username, String dateofbirth){

        User user = new User(firebaseUser.getUid(), firebaseUser.getEmail(), username, dateofbirth);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

                databaseReference.child(Constants.IMPEX_USER)
                .child(firebaseUser.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            registerActivityInstance.addUserSuccess("You have been succesfully added as a Impex User");
                        }
                        else {
                            registerActivityInstance.addUserFailure();
                        }
                    }
                });
    }
}
