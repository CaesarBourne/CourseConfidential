package com.bourne.caesar.impextutors.UI_Components.Activities;

import android.Manifest;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import com.bourne.caesar.impextutors.Adapters.ProgramChaptersAdapter;
import com.bourne.caesar.impextutors.MainActivity;
import com.bourne.caesar.impextutors.Models.CourseChaptersData;
import com.bourne.caesar.impextutors.Models.CourseFeaturesData;
import com.bourne.caesar.impextutors.Models.CoursePayStatus;
import com.bourne.caesar.impextutors.R;
import com.bourne.caesar.impextutors.FirebaseTasksCore.GetCoursePayStatusForProgramsActivity;
import com.bourne.caesar.impextutors.FirebaseTasksCore.GetProgramChaptersFromDatabase;
import com.bourne.caesar.impextutors.FirebaseTasksCore.GetProgramFeaturesFromFirebase;
import com.bourne.caesar.impextutors.UI_Components.Fragments.ChapterTypeFragment;
import com.bourne.caesar.impextutors.UI_Components.Fragments.PreviewCourseVideoDialogFragment;
import com.bourne.caesar.impextutors.UI_Components.Fragments.PurchaseCourseDialogFragment;

import java.io.File;
import java.util.ArrayList;

public class ProgramFeaturesActivity extends AppCompatActivity {

    public static final String PROGRAM_ID = "program";
    private Toolbar myNewToolbar;
    private RecyclerView chaptersRecyclerView;
    private GetProgramChaptersFromDatabase getProgramChaptersFromDatabase;
    private GetProgramFeaturesFromFirebase getProgramFeaturesFromFirebase;
    ProgramChaptersAdapter programChaptersAdapterInstance;
    private Button previewVideoButton, getStartedOrPayForCourse, downloadCourseButton;
    private VideoView videoView;
    private File file;
    MediaController mediaController;
    private GetCoursePayStatusForProgramsActivity getCoursePayStatusForProgramsActivity;

    private DownloadManager downloadManager;
    private long refid;
    private CourseFeaturesData courseFeaturesDataActivity;
    private boolean courseChapterDataGotten;
    private ArrayList<CourseChaptersData> courseChaptersListActivity;
    boolean coursepaidStatus;
    private Uri Download_Uri;
    private boolean programFeaturesGotten;
    ArrayList<Long> list = new ArrayList<>();
    ArrayList<CoursePayStatus> coursesPaidForList;


    private TextView programTitle, programDescription,  programVideoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        initialization();
        getIntentData();

        getCoursePayStatusForProgramsActivity = new GetCoursePayStatusForProgramsActivity(this);
        getCoursePayStatusForProgramsActivity.getCoursePayStatus();
        //DOWNLOADMANAGER
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);


        registerReceiver(downloadCompleteReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        Download_Uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/impexcollege.appspot.com/o/TPDP%20Customer%20Service%20Lecture-2_SDLow.mp4?alt=media&token=60f1f158-beee-4900-9cc0-d6b0c8ea3ae5");
//Uri.parse("https://peptribe-uploads.s3.amazonaws.com/upload/videos/2018/08/Q9xilJ2zY2wjtXO5gsmn_17_3f3a2681d60daae03e11a10075672c06_video.mp4");

//        videoUrlString = getArguments().getString(VIDEO_URL);


        final ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);


        previewVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playPreview();

            }
        });




        if(!isStoragePermissionGranted())
        {


        }


    }

    private void playPreview() {


        if (programFeaturesGotten && courseFeaturesDataActivity != null){
            String videoFile= courseFeaturesDataActivity.getProgramPreviewVideo();
            Uri uri = Uri.parse("https://peptribe-uploads.s3.amazonaws.com/upload/videos/2018/08/Q9xilJ2zY2wjtXO5gsmn_17_3f3a2681d60daae03e11a10075672c06_video.mp4");

            videoView.setVideoURI(Uri.parse(videoFile));
            mediaController = new MediaController(ProgramFeaturesActivity.this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.start();
        }
        else{
            Toast.makeText(this, "No Preview Video for this course", Toast.LENGTH_SHORT).show();
        }


    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }



    BroadcastReceiver downloadCompleteReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            long downloadTag = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

            list.remove(downloadTag);
            if (list.isEmpty()){
                NotificationCompat.Builder notification = new NotificationCompat.Builder(ProgramFeaturesActivity.this);
                Intent intentstart = new Intent(ProgramFeaturesActivity.this, MainActivity.class);
                intent.putExtra(PaaymentSuccessfulActivity.TRANSACTION_ID, "Your program is downloaded you would be abale to view offline...");

                PendingIntent pendingIntent = PendingIntent.getActivity(ProgramFeaturesActivity.this,0, intentstart, PendingIntent.FLAG_UPDATE_CURRENT);


                notification.setTicker("Download Complete");
                notification.setContentTitle("Impex Tutor");
                notification.setContentText("Doownload succesful ");
                notification.setWhen(System.currentTimeMillis());
                notification.setSmallIcon(R.drawable.impexlogo);
                notification.setLights(Color.BLUE, 500, 500);
                long[] pattern ={500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500};
                notification.setVibrate(pattern);
                notification.setStyle(new NotificationCompat.InboxStyle());
                Uri alarmsound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                notification.setSound(alarmsound);
                notification.setContentIntent(pendingIntent);
                notification.setAutoCancel(true);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(23, notification.build());

            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(downloadCompleteReceiver);
    }

    private void lectureType(ArrayList<CourseChaptersData> courseChaptersListType) {

        DialogFragment newDialog = ChapterTypeFragment.newInstance(courseChaptersListType);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment previousFragment = getSupportFragmentManager().findFragmentByTag("lectureTag");
        if (previousFragment != null){
            fragmentTransaction.remove(previousFragment);
        }

        newDialog.show(fragmentTransaction, "lectureTag");
    }

    private void purchasePopUp(CourseFeaturesData courseFeaturesData) {

        DialogFragment newDialog = PurchaseCourseDialogFragment.newDialogInstance(courseFeaturesData.getProgramfeeNaira(),
                courseFeaturesData.getProgramfeeDollar()
                , courseFeaturesData.getProgramTitle(), courseFeaturesData.getProgramId());
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

//        imagepreview = findViewById(R.id.programPreviewVideo);
//        imagepreview.setImageResource(R.drawable.slidetwo);
        getProgramChaptersFromDatabase = new GetProgramChaptersFromDatabase(this);
        getProgramFeaturesFromFirebase = new GetProgramFeaturesFromFirebase(this);
        chaptersRecyclerView = findViewById(R.id.chaptersRecyclerView);

        previewVideoButton =findViewById(R.id.previewVideo);
        getStartedOrPayForCourse = findViewById(R.id.payForOrStartCourse);
//        downloadCourseButton = findViewById(R.id.downloadCourse);


    }

    private void showVideoPopUp() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        DialogFragment previewCourseVideoDialogFragmentNewInstance;
        Fragment previousFragment;
        previousFragment = getSupportFragmentManager().findFragmentByTag("videopreview");
        if (previousFragment != null) {
            fragmentTransaction.remove(previousFragment);
        }
        previewCourseVideoDialogFragmentNewInstance =
                PreviewCourseVideoDialogFragment.newVideoDialogInstance("https://peptribe-uploads.s3.amazonaws.com/upload/videos/2018/08/Q9xilJ2zY2wjtXO5gsmn_17_3f3a2681d60daae03e11a10075672c06_video.mp4");
        previewCourseVideoDialogFragmentNewInstance.show(fragmentTransaction, "videopreview");
    }

    public void getCoursePayStatusSuccess(ArrayList<CoursePayStatus> coursePayStatuses){
        coursepaidStatus = true;
        coursesPaidForList = coursePayStatuses;
    }
    public  void getProgramFeaturesSuccess(final CourseFeaturesData courseFeaturesData){
        Toast.makeText(this, "Your Program features gorren succesfully", Toast.LENGTH_LONG);
        courseFeaturesDataActivity = courseFeaturesData;

        programFeaturesGotten = true;

        if (courseFeaturesDataActivity != null ){
            programTitle.setText(courseFeaturesData.getProgramTitle());
            programDescription.setText(courseFeaturesData.getProgramDescription());
//            programVideoDuration.setText(courseFeaturesData.getProgramTimeDuration());

            getStartedOrPayForCourse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (courseChapterDataGotten == true && coursepaidStatus == true){
                        boolean found = false;

                        for (int i =0; i <coursesPaidForList.size(); i++){
                            if( TextUtils.equals(coursesPaidForList.get(i).getCourseTitle(), courseFeaturesDataActivity.getProgramTitle()))
                            {
                                found = true;
                                break;
                            }
                        }


                        if (found) {
                            lectureType(courseChaptersListActivity);

                    }else {

                        purchasePopUp(courseFeaturesDataActivity);
                            }

                    }

                }
            });
        }
    }


    public void getProgramFeaturesFailure(){
        Toast.makeText(this, "Sorry course feature wasnt gotten succesfully", Toast.LENGTH_LONG);
    }


    public void getCourseChapterSuccess(final ArrayList<CourseChaptersData> courseChaptersList){
       programChaptersAdapterInstance = new ProgramChaptersAdapter(courseChaptersList,ProgramFeaturesActivity.this);
       courseChaptersListActivity = courseChaptersList;
       courseChapterDataGotten = true;
       programChaptersAdapterInstance.setListenerChild(new ProgramChaptersAdapter.Listener() {
           @Override
           public void chapterOnClick(int position) {
               if ( coursepaidStatus == true){
                   boolean found = false;

                   for (int i =0; i <coursesPaidForList.size(); i++){
                       if( TextUtils.equals(coursesPaidForList.get(i).getCourseTitle(), courseFeaturesDataActivity.getProgramTitle()))
                       {
                           found = true;
                           break;
                       }
                   }


                   if (found) {
//                           Intent intent = new Intent(ProgramFeaturesActivity.this, ChaptersDetailActivity.class);
//                           intent.putExtra(ChaptersDetailActivity.EXTRA_CHAPTER_ARRAY , courseChaptersListActivity);
//                           intent.putExtra(ChaptersDetailActivity.EXTRA_CHAPTER_POSITION , position);
//                           startActivity(intent);
                       lectureType(courseChaptersListActivity);
                       }else {

                           purchasePopUp(courseFeaturesDataActivity);
                       }

               }
//               Intent intent = new Intent(ProgramFeaturesActivity.this, ChaptersDetailActivity.class);
//               intent.putExtra(ChaptersDetailActivity.EXTRA_CHAPTER_ARRAY , courseChaptersListActivity);
//               intent.putExtra(ChaptersDetailActivity.EXTRA_CHAPTER_POSITION , position);
//               startActivity(intent);
           }
       });
       programChaptersAdapterInstance.notifyDataSetChanged();
       chaptersRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        chaptersRecyclerView.setLayoutManager(linearLayoutManager);
        chaptersRecyclerView.setAdapter(programChaptersAdapterInstance);
    }


}
