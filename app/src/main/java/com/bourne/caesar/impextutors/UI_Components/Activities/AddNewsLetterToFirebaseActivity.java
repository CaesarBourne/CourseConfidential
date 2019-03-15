package com.bourne.caesar.impextutors.UI_Components.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bourne.caesar.impextutors.FirebaseTasksCore.AddNewsLetterToFirebase;
import com.bourne.caesar.impextutors.Models.NewsLetterData;
import com.bourne.caesar.impextutors.R;

public class AddNewsLetterToFirebaseActivity extends AppCompatActivity {

    EditText newsTitleView;
    EditText newsIDView;
    EditText newsReadDurationView;
    EditText newsContentView;
    EditText newsContentPreviewView;
    EditText newsDateView;


    private String  newsTitleString, newsIDString, newsReadDurationString, newsContentString,
            newsContentPreviewString, newsDateString;

    AddNewsLetterToFirebase addNewsLetterToFirebase;

    Button sendNewsToFirebase;
    ProgressBar mProgressView;
    LinearLayout linearLayoutview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news_letter_to_firebase);
        initialization();

        sendNewsToFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chapterID = String.valueOf(System.currentTimeMillis());
                attemptUploadToFirebase();
            }
        });


    }

    private void initialization() {
        addNewsLetterToFirebase = new AddNewsLetterToFirebase(this);
        //buttons
        sendNewsToFirebase = findViewById(R.id.addNewsLettrerToFirebase);
        linearLayoutview = findViewById(R.id.addNewsLayout);
        mProgressView = findViewById(R.id.news_progressbar);

        newsTitleView = findViewById(R.id.newsTitle);
        newsIDView = findViewById(R.id.newsID);
        newsReadDurationView = findViewById(R.id.newsContent);
        newsContentView= findViewById(R.id.newsContent);
        newsContentPreviewView= findViewById(R.id.newsPreviewsContent);
        newsDateView = findViewById(R.id.newsDate);

         newsIDView.setText(String.valueOf( System.currentTimeMillis()));

    }

    public boolean attemptUploadToFirebase( ) {
        boolean valid = true;


        newsTitleString =  newsTitleView .getText().toString().trim();

        newsIDString = newsIDView.getText().toString().trim();
        newsReadDurationString = newsReadDurationView.getText().toString().trim();
        newsContentString =  newsContentView .getText().toString().trim();
        newsContentPreviewString = newsContentPreviewView.getText().toString().trim();
        newsDateString = newsDateView.getText().toString().trim();



        View focusview = null;


        if (TextUtils.isEmpty(newsTitleString)){
            valid = false;
            newsTitleView.setError("you must put in name");
            focusview = newsTitleView;
        }



        if (TextUtils.isEmpty(newsIDString)){
            valid = false;
            newsIDView.setError("you must put in name");
            focusview = newsIDView;
        }
        if (TextUtils.isEmpty(newsReadDurationString)){
            valid = false;
            newsReadDurationView.setError("you must put in name");
            focusview = newsReadDurationView;
        }

        if (TextUtils.isEmpty(newsContentString)){
            valid = false;
            newsContentView.setError("you must put in name");
            focusview = newsContentView;
        }



        if (TextUtils.isEmpty(newsContentPreviewString)){
            valid = false;
            newsContentPreviewView.setError("you must put in name");
            focusview = newsContentView;
        }

        if (TextUtils.isEmpty(newsDateString)){
            valid = false;
            newsDateView.setError("you must put in name");
            focusview = newsDateView;
        }






        if (!valid){
            focusview.requestFocus();
        }

        else{
//            ProgramIdView.setError(null);
//            showProgress(true);
//            this.ProgramIdView = ProgramIdView;
//            this.programTitleView = programTitleView;
//            this.programTargetView = programTargetView;
//            this.programDescriptionView = programDescriptionView;
//            this.programfeeView = programfeeView;
//            this.programTimeDurationView = programTimeDurationView;
//            this.programFacilitatorContactNumberView = programFacilitatorContactNumberView;
//            this.programFacilitatorEmailView = programFacilitatorEmailView;
//            this.programPreviewVideoView = programPreviewVideoView;
//            this.programImagePreviewView = programImagePreviewView;
//            this.programImageFeaturedView = programImageFeaturedView;
//            this.programfeeDollars = programfeeDollars;



            NewsLetterData newsLetterData = new NewsLetterData(newsTitleString, newsIDString , newsReadDurationString,
                    newsContentString,newsContentPreviewString, newsDateString
            );
            String newsLetterID = newsIDString;
            addNewsLetterToFirebase.AddNewsLetter(newsLetterData, newsLetterID);
            showProgress(true);

        }



        return valid;
    }
    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            linearLayoutview.setVisibility(show ? View.GONE : View.VISIBLE);
            linearLayoutview.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    linearLayoutview.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            linearLayoutview.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    public void addNewsLetterSuccess(){
        showProgress(false);
        Toast.makeText(this, "Your Radio Podcasts were succesfuly added to the database", Toast.LENGTH_LONG);

    }

}
