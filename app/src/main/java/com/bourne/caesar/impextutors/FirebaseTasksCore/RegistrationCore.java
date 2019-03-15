package com.bourne.caesar.impextutors.FirebaseTasksCore;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.bourne.caesar.impextutors.UI_Components.Activities.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationCore {

    RegisterActivity registerActivityInstance;

    public RegistrationCore(RegisterActivity registerActivityInstance) {
        this.registerActivityInstance = registerActivityInstance;
    }

    public  void registerImpexUser(String email, String password, final String username, Activity activity){

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            registerActivityInstance.registrationSuccessful(task.getResult().getUser(), username);
                        }
                        else {
                            registerActivityInstance.registerationfailure();
                        }
                    }
                }
        );
    }
}
