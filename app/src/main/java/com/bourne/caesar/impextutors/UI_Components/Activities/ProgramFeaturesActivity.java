package com.bourne.caesar.impextutors.UI_Components.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import com.bourne.caesar.impextutors.Adapters.ProgramChaptersAdapter;
import com.bourne.caesar.impextutors.Models.CourseChaptersData;
import com.bourne.caesar.impextutors.Models.CourseFeaturesData;
import com.bourne.caesar.impextutors.R;
import com.bourne.caesar.impextutors.TasksCore.GetProgramChaptersFromDatabase;
import com.bourne.caesar.impextutors.TasksCore.GetProgramFeaturesFromFirebase;
import com.bourne.caesar.impextutors.UI_Components.Fragments.PreviewCourseVideoDialogFragment;
import com.bourne.caesar.impextutors.UI_Components.Fragments.PurchaseCourseDialogFragment;

import java.util.List;

public class ProgramFeaturesActivity extends AppCompatActivity {

    public static final String PROGRAM_ID = "program";
    private Toolbar myNewToolbar;
    private RecyclerView chaptersRecyclerView;
    private GetProgramChaptersFromDatabase getProgramChaptersFromDatabase;
    private GetProgramFeaturesFromFirebase getProgramFeaturesFromFirebase;
    ProgramChaptersAdapter programChaptersAdapterInstance;
    private Button previewVideoButton, getStartedOrPayForCourse;
    VideoView videoView;
    MediaController mediaController;

    private TextView programTitle, programDescription, programVideoDuration, programVideoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        initialization();
        getIntentData();
        myNewToolbar.setTitle("Impex Program");
        setSupportActionBar(myNewToolbar);


//        videoUrlString = getArguments().getString(VIDEO_URL);

        Uri uri = Uri.parse("https://peptribe-uploads.s3.amazonaws.com/upload/videos/2018/08/Q9xilJ2zY2wjtXO5gsmn_17_3f3a2681d60daae03e11a10075672c06_video.mp4");
        videoView.setVideoURI(uri);
        mediaController = new MediaController(this);
//        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        previewVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showVideoPopUp();
                videoView.start();
            }
        });

        getStartedOrPayForCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchasePopUp();
            }
        });
    }

    private void purchasePopUp() {

        DialogFragment newDialog = PurchaseCourseDialogFragment.newDialogInstance("1000");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment previousFragment = getSupportFragmentManager().findFragmentByTag("fragmentTag");
        if (previousFragment != null){
            fragmentTransaction.remove(previousFragment);
        }

        newDialog.show(fragmentTransaction, "fragmentTag");
    }

    private void getIntentData() {
        if (getIntent().getExtras() != null){
            String progamID = getIntent().getExtras().getString(PROGRAM_ID);

            getProgramFeaturesFromFirebase.getProgramFeatures(progamID);
            getProgramChaptersFromDatabase.getProGramChapters(progamID);
        }
    }



    private void initialization() {

        videoView = findViewById(R.id.programPreviewVideo);
        programTitle = findViewById(R.id.courseTitle);
        programDescription = findViewById(R.id.courseDescription);
        programVideoDuration = findViewById(R.id.coursevideoduration);
//        imagepreview = findViewById(R.id.programPreviewVideo);
//        imagepreview.setImageResource(R.drawable.slidetwo);
        getProgramChaptersFromDatabase = new GetProgramChaptersFromDatabase(this);
        getProgramFeaturesFromFirebase = new GetProgramFeaturesFromFirebase(this);
        chaptersRecyclerView = findViewById(R.id.chaptersRecyclerView);

        previewVideoButton =findViewById(R.id.previewVideo);
        getStartedOrPayForCourse = findViewById(R.id.payForOrStartCourse);

        myNewToolbar = findViewById(R.id.mynewtoolbar);
    }

    private void showVideoPopUp() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        DialogFragment previewCourseVideoDialogFragmentNewInstance;
        Fragment previousFragment;
        previousFragment = getSupportFragmentManager().findFragmentByTag("videopreview");
        if (previousFragment != null) {
            fragmentTransaction.remove(previousFragment);
        }
        previewCourseVideoDialogFragmentNewInstance = PreviewCourseVideoDialogFragment.newVideoDialogInstance("https://peptribe-uploads.s3.amazonaws.com/upload/videos/2018/08/Q9xilJ2zY2wjtXO5gsmn_17_3f3a2681d60daae03e11a10075672c06_video.mp4");
        previewCourseVideoDialogFragmentNewInstance.show(fragmentTransaction, "videopreview");
    }

    public  void getProgramFeaturesSuccess(CourseFeaturesData courseFeaturesData){
        Toast.makeText(this, "Your Program features gorren succesfully", Toast.LENGTH_LONG);
        if (courseFeaturesData != null){
            programTitle.setText(courseFeaturesData.getProgramTitle());
            programDescription.setText(courseFeaturesData.getProgramDescription());
            programVideoDuration.setText(courseFeaturesData.getProgramTimeDuration());
        }
    }


    public void getProgramFeaturesFailure(){
        Toast.makeText(this, "Sorry course feature wasnt gotten succesfully", Toast.LENGTH_LONG);
    }


    public void getCourseChapterSuccess(List<CourseChaptersData> courseChaptersList){
       programChaptersAdapterInstance = new ProgramChaptersAdapter(courseChaptersList,ProgramFeaturesActivity.this);
       programChaptersAdapterInstance.setListenerChild(new ProgramChaptersAdapter.Listener() {
           @Override
           public void chapterOnClick(int position) {
               Intent intent = new Intent(ProgramFeaturesActivity.this, ChaptersDetailActivity.class);
               startActivity(intent);
           }
       });
       programChaptersAdapterInstance.notifyDataSetChanged();
       chaptersRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        chaptersRecyclerView.setLayoutManager(linearLayoutManager);
        chaptersRecyclerView.setAdapter(programChaptersAdapterInstance);
    }
}
