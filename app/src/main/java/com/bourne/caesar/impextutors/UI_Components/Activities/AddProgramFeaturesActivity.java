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
import android.widget.Toast;

import com.bourne.caesar.impextutors.Models.CourseFeaturesData;
import com.bourne.caesar.impextutors.R;
import com.bourne.caesar.impextutors.TasksCore.AddProgramFeaturesToDatabase;
import com.bourne.caesar.impextutors.Utilities.Constants;

public class AddProgramFeaturesActivity extends AppCompatActivity {

    private EditText ProgramIdView;
    EditText programTitleView;
    EditText programTargetView;
    EditText programDescriptionView;
    EditText programfeeView;
    EditText programTimeDurationView;
    EditText programFacilitatorContactNumberView;
    EditText programFacilitatorEmailView;
    EditText programPreviewVideoView;
    EditText programImagePreviewView;
    EditText programImageFeaturedView;
    EditText programnumberOfChapterView;
    EditText pogramChapterTitle;
    View mProgressView;
    LinearLayout aaddDataLayout;
    Button sendBasicDetailsToFirebase, sendIntermediateDetailsToFirebase, sendAdvanceDetailsToFirebase,sendCustomerServiceDetailsToFirebase,
            sendTradeFinanaceDetailsToFirebase,sendBusinessManagementDetailsToFirebase;

    String programIdString, programTitleString, programTargetString, programDescriptionString, programFeeString,programTimeaDurationString,
            programFacilitatorContactNumberString, programFacilitatorEmailString, programPreviewVideoString, programImagePreviewString,
            programImageFeaturedString, programNumberOfChapterString;

    AddProgramFeaturesToDatabase addProgramFeaturesToDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_programs);
        initialization();

        sendBasicDetailsToFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptUploadToFirebase(Constants.IMPEX_BASIC);
            }
        });

        sendIntermediateDetailsToFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptUploadToFirebase(Constants.IMPEX_INTERMIDIATE);
            }
        });

        sendAdvanceDetailsToFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptUploadToFirebase(Constants.IMPEX_ADVANCE);
            }
        });
        sendCustomerServiceDetailsToFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptUploadToFirebase(Constants.IMPEX_CUSTOMER_SERVICE);
            }
        });

        sendTradeFinanaceDetailsToFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptUploadToFirebase(Constants.IMPEX_TRADE_FINANCE);
            }
        });

        sendBusinessManagementDetailsToFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptUploadToFirebase(Constants.IMPEX_BUSINESS_MANAGEMENT);
            }
        });
    }

    private void initialization() {
        addProgramFeaturesToDatabase = new AddProgramFeaturesToDatabase(this);
        //buttons
        sendBasicDetailsToFirebase = findViewById(R.id.addBasicDatatoFirebase);
        sendIntermediateDetailsToFirebase = findViewById(R.id.addIntermediateDatatoFirebase);
        sendAdvanceDetailsToFirebase = findViewById(R.id.addAdvanceDatatoFirebase);
        sendCustomerServiceDetailsToFirebase = findViewById(R.id.addCustomerServiceDatatoFirebase);
        sendTradeFinanaceDetailsToFirebase = findViewById(R.id.addTradeFinanceDatatoFirebase);
        sendBusinessManagementDetailsToFirebase = findViewById(R.id.addBusinessManagementDatatoFirebase);

        //progressbar
        mProgressView = findViewById(R.id.login_progress);
        //linearlayout
        aaddDataLayout =findViewById(R.id.adddatalayout);
        //featuresdata
        ProgramIdView = findViewById(R.id.programID);
        programTitleView = findViewById(R.id.programtitle);
        programTargetView = findViewById(R.id.programtarget);
        programDescriptionView = findViewById(R.id.programdescription);
        programfeeView = findViewById(R.id.programfee);
        programTimeDurationView = findViewById(R.id.programTimeDuaration);
        programFacilitatorContactNumberView = findViewById(R.id.programfacilitatorcontact);
        programFacilitatorEmailView = findViewById(R.id.programfacilitatoremail);
        programPreviewVideoView = findViewById(R.id.programpreviewvideo);
        programImagePreviewView = findViewById(R.id.programpreviewimage);
        programImageFeaturedView = findViewById(R.id.programfeaturedimages);
        programnumberOfChapterView = findViewById(R.id.programchapternumbers);
    }

    public boolean attemptUploadToFirebase(String featureID) {
        boolean valid = true;


        programIdString = ProgramIdView.getText().toString().trim();
        programTitleString = programTitleView.getText().toString().trim();
        programTargetString = programTargetView.getText().toString().trim();
        programDescriptionString = programDescriptionView.getText().toString().trim();
        programFeeString = programfeeView.getText().toString().trim();
        programTimeaDurationString = programTimeDurationView.getText().toString().trim();
        programFacilitatorContactNumberString = programFacilitatorContactNumberView.getText().toString().trim();
        programFacilitatorEmailString = programFacilitatorEmailView.getText().toString().trim();
        programPreviewVideoString = programPreviewVideoView.getText().toString().trim();
        programImagePreviewString = programImagePreviewView.getText().toString().trim();
        programImageFeaturedString = programImageFeaturedView.getText().toString().trim();
        programNumberOfChapterString = programnumberOfChapterView.getText().toString().trim();

        View focusview = null;
        if (TextUtils.isEmpty(programIdString)){
            valid = false;
            ProgramIdView.setError("you must put in name");
            focusview = ProgramIdView;
        }

      if (TextUtils.isEmpty(programTitleString)){
            valid = false;
            programTitleView.setError("you must put in name");
            focusview = programTitleView;
        }

       if (TextUtils.isEmpty(programTargetString)){
            valid = false;
            programTargetView.setError("you must put in name");
            focusview = programTargetView;
        }

        if (TextUtils.isEmpty(programDescriptionString)){
            valid = false;
            programDescriptionView.setError("you must put in name");
            focusview = programDescriptionView;
        }

       if (TextUtils.isEmpty(programFeeString)){
            valid = false;
            programfeeView.setError("you must put in name");
            focusview = programfeeView;
        }

        if (TextUtils.isEmpty(programTimeaDurationString)){
            valid = false;
            programTimeDurationView.setError("you must put in name");
            focusview = programTimeDurationView;
        }
            
        if (TextUtils.isEmpty(programFacilitatorContactNumberString)){
            valid = false;
            programFacilitatorContactNumberView.setError("you must put in name");
            focusview = programFacilitatorContactNumberView;
        }

        if (TextUtils.isEmpty(programFacilitatorEmailString)){
            valid = false;
            programFacilitatorEmailView.setError("you must put in name");
            focusview = programFacilitatorEmailView;
        }
            
        if (TextUtils.isEmpty(programPreviewVideoString)){
            valid = false;
            programPreviewVideoView.setError("you must put in name");
            focusview = programPreviewVideoView;
        }

        if (TextUtils.isEmpty(programImagePreviewString)){
            valid = false;
            programImagePreviewView.setError("you must put in name");
            focusview = programImagePreviewView;
        }

        if (TextUtils.isEmpty(programImageFeaturedString)){
            valid = false;
            programImageFeaturedView.setError("you must put in name");
            focusview = programImageFeaturedView;
        }

        if (TextUtils.isEmpty(programNumberOfChapterString)){
            valid = false;
            programnumberOfChapterView.setError("you must put in name");
            focusview = programnumberOfChapterView;
        }

            if (!valid){
            focusview.requestFocus();
            }
            
        else{
            ProgramIdView.setError(null);
            showProgress(true);
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
//            this.programnumberOfChapterView = programnumberOfChapterView;

            CourseFeaturesData courseFeaturesData = new CourseFeaturesData(programIdString,programTitleString,programTargetString,
                    programDescriptionString, programFeeString,programTimeaDurationString,programFacilitatorContactNumberString,
                    programFacilitatorEmailString, programPreviewVideoString, programImagePreviewString, programImageFeaturedString
            ,programNumberOfChapterString);
            addProgramFeaturesToDatabase.AddProgamFeatures(featureID, courseFeaturesData);

        }



        return valid;
    }
    public void addProgramsSuccess(){
        Toast.makeText(this, "Your Programs were succesfuly added to the database", Toast.LENGTH_LONG);
        showProgress(false);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            aaddDataLayout.setVisibility(show ? View.GONE : View.VISIBLE);
            aaddDataLayout.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    aaddDataLayout.setVisibility(show ? View.GONE : View.VISIBLE);
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
            aaddDataLayout.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public void addProgramFeaturesSuccess(){
        showProgress(false);
        Toast.makeText(this, "Your Programs were succesfuly added to the database", Toast.LENGTH_LONG);

    }
}
