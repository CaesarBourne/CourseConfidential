package com.bourne.caesar.impextutors.UI_Components.Activities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bourne.caesar.impextutors.MainActivity;
import com.bourne.caesar.impextutors.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_MS =2000;
    Handler myHandler;
    Runnable myRunable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);

        myHandler = new Handler();
        myRunable = new Runnable() {
            @Override
            public void run() {
                if (FirebaseAuth.getInstance().getCurrentUser() != null){
                    MainActivity.startActivity(SplashActivity.this);
                }
                else {
                    LoginActivity.startIntent(SplashActivity.this);
                }
                finish();
            }
        };
        myHandler.postDelayed(myRunable, SPLASH_TIME_MS);
    }


}
