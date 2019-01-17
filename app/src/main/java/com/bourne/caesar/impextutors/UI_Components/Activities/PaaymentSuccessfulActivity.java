package com.bourne.caesar.impextutors.UI_Components.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bourne.caesar.impextutors.MainActivity;
import com.bourne.caesar.impextutors.R;

public class PaaymentSuccessfulActivity extends AppCompatActivity {

    public static final String TRANSACTION_ID = "transact";
    Button goHomeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paayment_successful);
        goHomeButton = findViewById(R.id.goHoe);
        goHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaaymentSuccessfulActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
