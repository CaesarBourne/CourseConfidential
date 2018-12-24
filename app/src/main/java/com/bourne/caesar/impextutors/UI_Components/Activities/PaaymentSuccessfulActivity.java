package com.bourne.caesar.impextutors.UI_Components.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bourne.caesar.impextutors.R;

public class PaaymentSuccessfulActivity extends AppCompatActivity {

    public static final String TRANSACTION_ID = "transact";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paayment_successful);
    }

}
