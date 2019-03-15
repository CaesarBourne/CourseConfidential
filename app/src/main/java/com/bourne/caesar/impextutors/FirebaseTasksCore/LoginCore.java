package com.bourne.caesar.impextutors.FirebaseTasksCore;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.bourne.caesar.impextutors.UI_Components.Activities.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginCore {
    LoginActivity loginActivityIanstance;

    public LoginCore(LoginActivity loginActivityIanstance) {
        this.loginActivityIanstance = loginActivityIanstance;
    }

    public void loginImpexUser(String email, String password, Activity activity){

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener(activity,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            loginActivityIanstance.loginUserSuccesful();
                        }else {
                            loginActivityIanstance.loginFailure();
                        }
                    }
                });
    }
}
