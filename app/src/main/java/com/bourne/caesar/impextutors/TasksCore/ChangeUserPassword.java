package com.bourne.caesar.impextutors.TasksCore;

import android.support.annotation.NonNull;

import com.bourne.caesar.impextutors.UI_Components.Fragments.MyAccountFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ChangeUserPassword {
    MyAccountFragment myAccountFragment;

    public ChangeUserPassword(MyAccountFragment myAccountFragment) {
        this.myAccountFragment = myAccountFragment;
    }

    public void sendResetPasswordEmail(String email){
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            myAccountFragment.passwordResetEmailSent();
                        }
                    }
                }
        );

    }

}
