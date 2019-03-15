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

import com.bourne.caesar.impextutors.Models.CourseChaptersData;
import com.bourne.caesar.impextutors.R;
import com.bourne.caesar.impextutors.FirebaseTasksCore.AddProgramChaptersToDatabase;
import com.bourne.caesar.impextutors.Utilities.Constants;

public class AddProgramChaptersActivity extends AppCompatActivity {


    EditText programChapterTitle;
    EditText programChapterDescription;
    EditText programChapterNumber;
    EditText programChapterVideoDuration;
    EditText programChapterVideoStreamLink;
    EditText programchapterAudio;
    EditText programChapterID;


   private String  programChapterTitleString, programChapterDescriptionString, programChapterNumberString, programChapterVideoDurationString,
            programChapterVideoStreamLinkString, programchapterAudioString, programChapterIDString;

    AddProgramChaptersToDatabase addProgramChaptersToDatabase;

    Button sendBasicChapetrDetailsToFirebase, sendIntermediateChapetrDetailsToFirebase, sendAdvancedChapetrDetailsToFirebase,
            sendCustomerChapterDetailsToFirebase, sendTradeFinanceChapterDetailsToFirebase, sendBusinessManagementChapterDetailsToFirebase;
    ProgressBar mProgressView;
    LinearLayout linearLayoutview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_program_chapters);
        initialization();

        sendBasicChapetrDetailsToFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chapterID = String.valueOf(System.currentTimeMillis());
                attemptUploadToFirebase( Constants.IMPEX_BASIC);
            }
        });

        sendIntermediateChapetrDetailsToFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chapterID = String.valueOf(System.currentTimeMillis());
                attemptUploadToFirebase( Constants.IMPEX_INTERMIDIATE);
            }
        });

        sendAdvancedChapetrDetailsToFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chapterID = String.valueOf(System.currentTimeMillis());
                attemptUploadToFirebase(Constants.IMPEX_ADVANCE);
            }
        });

        sendCustomerChapterDetailsToFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chapterID = String.valueOf(System.currentTimeMillis());
                attemptUploadToFirebase( Constants.IMPEX_CUSTOMER_SERVICE);
            }
        });

        sendTradeFinanceChapterDetailsToFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptUploadToFirebase( Constants.IMPEX_TRADE_FINANCE);
            }
        });

        sendBusinessManagementChapterDetailsToFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptUploadToFirebase( Constants.IMPEX_BUSINESS_MANAGEMENT);
            }
        });
    }

    private void initialization() {
        addProgramChaptersToDatabase = new AddProgramChaptersToDatabase(this);
        //buttons
        sendBasicChapetrDetailsToFirebase = findViewById(R.id.addBasicChapterDatatoFirebase);
        sendIntermediateChapetrDetailsToFirebase = findViewById(R.id.addIntermideateChapterDatatoFirebase);
        sendAdvancedChapetrDetailsToFirebase = findViewById(R.id.addAdvanceChapterDatatoFirebase);
        sendCustomerChapterDetailsToFirebase = findViewById(R.id.addCustomerServiceChaptertoFirebase);
        sendTradeFinanceChapterDetailsToFirebase = findViewById(R.id.addTradeFinanceChaptertoFirebase);
        sendBusinessManagementChapterDetailsToFirebase = findViewById(R.id.addBusinessManagementChaptertoFirebase);

        linearLayoutview = findViewById(R.id.addchapterslayout);
        mProgressView = findViewById(R.id.chapters_progressbar);

        programChapterTitle = findViewById(R.id.programchaptertitle);
        programChapterDescription = findViewById(R.id.programchapterdescription);
        programChapterNumber = findViewById(R.id.programchapternumber);
        programChapterVideoDuration = findViewById(R.id.programchapterVideoDuaration);
        programChapterVideoStreamLink= findViewById(R.id.programchaptervideostreamlink);
        programchapterAudio= findViewById(R.id.programchapteraudio);
        programChapterID = findViewById(R.id.programchapterID);
        String chapterID = String.valueOf(System.currentTimeMillis());
        programChapterID.setText(chapterID);
    }

    public boolean attemptUploadToFirebase( String ProgramId) {
        boolean valid = true;

        programChapterTitleString = programChapterTitle.getText().toString().trim();

        programChapterDescriptionString = programChapterDescription.getText().toString().trim();
        programChapterNumberString = programChapterNumber.getText().toString().trim();
        programChapterVideoDurationString = programChapterVideoDuration.getText().toString().trim();
        programChapterVideoStreamLinkString = programChapterVideoStreamLink.getText().toString().trim();
        programchapterAudioString = programchapterAudio.getText().toString().trim();
        programChapterIDString = programChapterID.getText().toString().trim();



        View focusview = null;


        if (TextUtils.isEmpty(programChapterTitleString)){
            valid = false;
            programChapterTitle.setError("you must put in name");
            focusview = programChapterTitle;
        }



        if (TextUtils.isEmpty(programChapterDescriptionString)){
            valid = false;
            programChapterDescription.setError("you must put in name");
            focusview = programChapterDescription;
        }
        if (TextUtils.isEmpty(programChapterNumberString)){
            valid = false;
            programChapterNumber.setError("you must put in name");
            focusview = programChapterNumber;
        }

        if (TextUtils.isEmpty(programChapterVideoDurationString)){
            valid = false;
            programChapterVideoDuration.setError("you must put in name");
            focusview = programChapterVideoDuration;
        }



        if (TextUtils.isEmpty(programChapterVideoStreamLinkString)){
            valid = false;
            programChapterVideoStreamLink.setError("you must put in name");
            focusview = programChapterVideoStreamLink;
        }

        if (TextUtils.isEmpty(programchapterAudioString)){
            valid = false;
            programchapterAudio.setError("you must put in name");
            focusview = programchapterAudio;
        }
        if (TextUtils.isEmpty(programChapterIDString)){
            valid = false;
            programChapterID.setError("you must put in ID");
            focusview = programChapterID;
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

            CourseChaptersData courseChaptersData = new CourseChaptersData(programChapterTitleString,
                    programChapterDescriptionString,  programChapterNumberString, programChapterVideoDurationString,
                     programChapterVideoStreamLinkString, programchapterAudioString, programChapterIDString
                    );
            addProgramChaptersToDatabase.AddProgamChapters(programChapterIDString, courseChaptersData, ProgramId);
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
    public void addProgramChaptersSuccess(){
        showProgress(false);
        Toast.makeText(this, "Your Chapters were succesfuly added to the database", Toast.LENGTH_LONG);

    }
}
