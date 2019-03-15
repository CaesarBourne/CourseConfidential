package com.bourne.caesar.impextutors.UI_Components.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bourne.caesar.impextutors.MainActivity;
import com.bourne.caesar.impextutors.R;
import com.bourne.caesar.impextutors.Utilities.Constants;

public class PaaymentSuccessfulActivity extends AppCompatActivity {

    public static final String TRANSACTION_ID = "transact";
    public static final String COURSE_TITLE = "courseTitle";
    Button goHomeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paayment_successful);
        goHomeButton = findViewById(R.id.goHoe);
        goHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().getExtras() != null){
                    String courseTitle = getIntent().getExtras().getString(COURSE_TITLE);
                    Intent intent = new Intent(PaaymentSuccessfulActivity.this, ProgramFeaturesActivity.class);
                    intent.putExtra(ProgramFeaturesActivity.PROGRAM_ID, courseTitle);
                    startActivity(intent);
                }
            }
        });
    }

}
