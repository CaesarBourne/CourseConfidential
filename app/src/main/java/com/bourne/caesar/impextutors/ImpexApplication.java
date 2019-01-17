package com.bourne.caesar.impextutors;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.google.firebase.database.FirebaseDatabase;

import co.paystack.android.Paystack;
import co.paystack.android.PaystackSdk;

public class ImpexApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        PaystackSdk.initialize(getApplicationContext());
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
