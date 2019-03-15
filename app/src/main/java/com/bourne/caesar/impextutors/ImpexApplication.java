package com.bourne.caesar.impextutors;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import co.paystack.android.Paystack;
import co.paystack.android.PaystackSdk;

public class ImpexApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        PaystackSdk.initialize(getApplicationContext());
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this,Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(false);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);
    }
}
