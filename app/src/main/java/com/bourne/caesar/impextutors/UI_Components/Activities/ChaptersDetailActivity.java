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
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bourne.caesar.impextutors.MainActivity;
import com.bourne.caesar.impextutors.Models.CourseChaptersData;
import com.bourne.caesar.impextutors.R;
import com.bourne.caesar.impextutors.Utilities.Constants;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

public class ChaptersDetailActivity extends AppCompatActivity implements Player.EventListener {
    public static final String EXTRA_CHAPTER_POSITION = "chapterposition";
    public static final String EXTRA_CHAPTER_ARRAY = "chapterARRAY";
    private ArrayList<CourseChaptersData> courseChaptersList;
    private PlayerView chapterVideoView;
    private int chapterPosition;

    private static final String KEY_VIDEO_URI = "video_uri";


//    PlayerView chapterVideoView;

    ProgressBar spinnerVideoDetails;

    private DownloadManager downloadManager;
    private long refid;
    private Uri Download_Uri;
    ArrayList<Long> list = new ArrayList<>();

    private Button playChapterVideo, previousChapterButton, nextChapteButton,downloadCourseButton;
    android.widget.MediaController mediaController;
    TextView chapterTitleView, chapterDescription, chaptervideoDurationView;

    String videoUri;
    SimpleExoPlayer player;
    Handler mHandler;
    Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters_detail);
        initialization();
        if (getIntent().getExtras() != null){
            courseChaptersList = (ArrayList<CourseChaptersData>) getIntent().getSerializableExtra(EXTRA_CHAPTER_ARRAY);
            chapterPosition = getIntent().getExtras().getInt(EXTRA_CHAPTER_POSITION);
            final int chapterListSize = courseChaptersList.size();

            setViews(chapterPosition);
//download manager

            downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);


            registerReceiver(downloadCompleteReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));


            Download_Uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/impexcollege.appspot.com/o/TPDP%20Customer%20Service%20Lecture-2_SDLow.mp4?alt=media&token=60f1f158-beee-4900-9cc0-d6b0c8ea3ae5");

            nextChapteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (chapterPosition == 2){
                        nextChapteButton.setEnabled(false);
                    }
                    else if (chapterPosition < chapterListSize ){
                         chapterPosition = chapterPosition + 1;
                        setViews(chapterPosition);
                    }
                    else {
                        Toast.makeText(ChaptersDetailActivity.this, "End of chapters", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            previousChapterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (chapterPosition ==  0){
                        previousChapterButton.setEnabled(false);
                    }
                    else if (chapterPosition < chapterListSize ){
                        chapterPosition = chapterPosition - 1;
                        setViews(chapterPosition);

                    }

                    else {
                        Toast.makeText(ChaptersDetailActivity.this, "No more Previous Chapter" +
                                "", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            if(!isStoragePermissionGranted())
            {


            }

            downloadCourseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.clear();

                    DownloadManager.Request downloadRequest = new DownloadManager.Request(Download_Uri);
                    downloadRequest.setDescription("downloading program.mp4.....");
                    downloadRequest.setTitle("ProgramPay");
                    downloadRequest.setDestinationInExternalFilesDir(ChaptersDetailActivity.this,Environment.DIRECTORY_DOWNLOADS,
                            "/.Impex/programnumber.mp4");
//                String downloadstore = ProgramFeaturesActivity.this,Environment.DIRECTORY_DOWNLOADS,
//                        "/.Impex/programnumber.mp4"
//                downloadRequest.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/.Impex/programnumber.mp4");
                    downloadRequest.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);

                    long reference_download_ID = downloadManager.enqueue(downloadRequest);

                    list.add(reference_download_ID);
                }
            });

        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(downloadCompleteReceiver);
        releasePlayer();
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
                NotificationCompat.Builder notification = new NotificationCompat.Builder(ChaptersDetailActivity.this);
                Intent intentstart = new Intent(ChaptersDetailActivity.this, MainActivity.class);
                intent.putExtra(PaaymentSuccessfulActivity.TRANSACTION_ID, "Your program is downloaded you would be abale to view offline...");

                PendingIntent pendingIntent = PendingIntent.getActivity(ChaptersDetailActivity.this,0, intentstart, PendingIntent.FLAG_UPDATE_CURRENT);


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

    private void setViews(int chapterNewPosition) {
        chapterTitleView.setText(courseChaptersList.get(chapterNewPosition).CourseNumber+ " .   " +
                courseChaptersList.get(chapterNewPosition).CourseTitle);
        chapterDescription.setText(courseChaptersList.get(chapterNewPosition).CourseDescription);
        chaptervideoDurationView.setText(courseChaptersList.get(chapterNewPosition).CourseVideoDuration);
        videoUri =  courseChaptersList.get(chapterNewPosition).CourseVideoStream;

        playChapterVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUp();
            }
        });

//        playChapterVideo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/impexcollege.appspot.com/o/TPDP%20Customer%20Service%20Lecture-2_SDLow.mp4?alt=media&token=60f1f158-beee-4900-9cc0-d6b0c8ea3ae5");
//                chapterVideoView.setVideoURI(uri);
//                mediaController = new MediaController(ChaptersDetailActivity.this);
////        mediaController.setAnchorView(videoView);
//                chapterVideoView.setMediaController(mediaController);
//                chapterVideoView.start();
//            }
//        });
    }

    private void initialization() {
        chapterVideoView = findViewById(R.id.chapterVideoView);
        chapterTitleView = findViewById(R.id.chapterTitle);
        chapterDescription = findViewById(R.id.chapterDescription);
        chaptervideoDurationView = findViewById(R.id.chapterVideoDuration);
        playChapterVideo = findViewById(R.id.playChapterVideo);
        previousChapterButton = findViewById(R.id.chaapterPreviousButton);
        nextChapteButton = findViewById(R.id.chapterNextButton);
        downloadCourseButton = findViewById(R.id.downloadCourse);
        spinnerVideoDetails = findViewById(R.id.spinnerVideoDetails);
        chapterVideoView = findViewById(R.id.chapterVideoView);
    }


    private void setUp() {
        initializePlayer();
        if (videoUri == null) {
            return;
        }
        buildMediaSource(Uri.parse(videoUri));
    }

//    @OnClick(R.id.imageViewExit)
//    public void onViewClicked() {
//        finish();
//    }

    private void initializePlayer() {
        if (player == null) {
            // 1. Create a default TrackSelector
            LoadControl loadControl = new DefaultLoadControl(
                    new DefaultAllocator(true, 16),
                    Constants.MIN_BUFFER_DURATION,
                    Constants.MAX_BUFFER_DURATION,
                    Constants.MIN_PLAYBACK_START_BUFFER,
                    Constants.MIN_PLAYBACK_RESUME_BUFFER, -1, true);

            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory videoTrackSelectionFactory =
                    new AdaptiveTrackSelection.Factory(bandwidthMeter);
            TrackSelector trackSelector =
                    new DefaultTrackSelector(videoTrackSelectionFactory);
            // 2. Create the player
            player = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(this), trackSelector, loadControl);
            chapterVideoView.setPlayer(player);
        }


    }

    private void buildMediaSource(Uri mUri) {
        // Measures bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, getString(R.string.app_name)), bandwidthMeter);
        // This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(mUri);
        // Prepare the player with the source.
        player.prepare(videoSource);
        player.setPlayWhenReady(true);
        player.addListener(this);
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    private void pausePlayer() {
        if (player != null) {
            player.setPlayWhenReady(false);
            player.getPlaybackState();
        }
    }

    private void resumePlayer() {
        if (player != null) {
            player.setPlayWhenReady(true);
            player.getPlaybackState();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        pausePlayer();
        if (mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        resumePlayer();
    }



    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        switch (playbackState) {

            case Player.STATE_BUFFERING:
                spinnerVideoDetails.setVisibility(View.VISIBLE);
                break;
            case Player.STATE_ENDED:
                // Activate the force enable
                break;
            case Player.STATE_IDLE:

                break;
            case Player.STATE_READY:
                spinnerVideoDetails.setVisibility(View.GONE);

                break;
            default:
                // status = PlaybackStatus.IDLE;
                break;
        }
    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }
}
