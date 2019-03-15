package com.bourne.caesar.impextutors.UI_Components.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.bourne.caesar.impextutors.R;

public class AboutAppActivity extends AppCompatActivity {

    Toolbar aboutAppToolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_about_app);

        aboutAppToolBar = findViewById(R.id.aboutapptoolbar);
        setSupportActionBar(aboutAppToolBar);
        getSupportActionBar().setTitle("About App");

    }
}
