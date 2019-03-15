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

import com.bourne.caesar.impextutors.FirebaseTasksCore.AddProgramChaptersToDatabase;
import com.bourne.caesar.impextutors.FirebaseTasksCore.AddRadioPodcastsToFirebase;
import com.bourne.caesar.impextutors.Models.CourseChaptersData;
import com.bourne.caesar.impextutors.Models.RadioPodcastData;
import com.bourne.caesar.impextutors.R;
import com.bourne.caesar.impextutors.Utilities.Constants;

public class AddRadioPodcaastActivityToFirebase extends AppCompatActivity {

    EditText podcastTitleView;
    EditText podcastIDView;
    EditText podcaststreamUrlView;
    EditText podcastDurationView;
    EditText podcastDateView;


    private String  podcastTitleString, podcastIDString, podcaststraemUrlString, podcastDurationString,
    podcastDateString;

    AddRadioPodcastsToFirebase addRadioPodcastsToFirebase;

    Button sendPodcastToFirebase;
    ProgressBar mProgressView;
    LinearLayout linearLayoutview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_radio_podcaast_to_firebase);
        initialization();

        sendPodcastToFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chapterID = String.valueOf(System.currentTimeMillis());
                attemptUploadToFirebase();
            }
        });


    }

    private void initialization() {
        addRadioPodcastsToFirebase = new AddRadioPodcastsToFirebase(this);
        //buttons
       sendPodcastToFirebase = findViewById(R.id.addPodcastToFirebase);
        linearLayoutview = findViewById(R.id.addchapterslayout);
        mProgressView = findViewById(R.id.chapters_progressbar);

        podcastTitleView = findViewById(R.id.podcastTitle);
        podcastIDView = findViewById(R.id.podcastID);
        podcaststreamUrlView = findViewById(R.id.podcastUrlLink);
        podcastDurationView = findViewById(R.id.podcasDuration);
        podcastDateView= findViewById(R.id.podcastDate);

    }

    public boolean attemptUploadToFirebase( ) {
        boolean valid = true;


        podcastTitleString =  podcastTitleView.getText().toString().trim();

        podcastIDString = podcastIDView.getText().toString().trim();
        podcaststraemUrlString = podcaststreamUrlView.getText().toString().trim();
        podcastDurationString =  podcastDurationView .getText().toString().trim();
        podcastDateString = podcastDateView.getText().toString().trim();



        View focusview = null;


        if (TextUtils.isEmpty(podcastTitleString)){
            valid = false;
            podcastTitleView.setError("you must put in name");
            focusview = podcastTitleView;
        }



        if (TextUtils.isEmpty(podcastIDString)){
            valid = false;
            podcastIDView.setError("you must put in name");
            focusview = podcastIDView;
        }
        if (TextUtils.isEmpty(podcaststraemUrlString)){
            valid = false;
            podcaststreamUrlView.setError("you must put in name");
            focusview = podcaststreamUrlView;
        }

        if (TextUtils.isEmpty(podcastDurationString)){
            valid = false;
            podcastDurationView.setError("you must put in name");
            focusview = podcastDurationView;
        }



        if (TextUtils.isEmpty(podcastDateString)){
            valid = false;
            podcastDateView.setError("you must put in name");
            focusview = podcastDateView;
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

            RadioPodcastData radioPodcastData = new RadioPodcastData(podcastTitleString, podcaststraemUrlString, podcastDateString,
                    podcastDurationString,podcastIDString
            );
            String RadioPodcastID = String.valueOf( System.currentTimeMillis());
            addRadioPodcastsToFirebase.AddRadioPodcasts(radioPodcastData, RadioPodcastID);
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
    public void addRadioPodcastSuccess(){
        showProgress(false);
        Toast.makeText(this, "Your Radio Podcasts were succesfuly added to the database", Toast.LENGTH_LONG);

    }
}
