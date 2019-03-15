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
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bourne.caesar.impextutors.Adapters.ProgramChaptersAdapter;
import com.bourne.caesar.impextutors.Adapters.RadioPodcastsAdapter;
import com.bourne.caesar.impextutors.FirebaseTasksCore.GetRadioPodcastsFromFirebase;
import com.bourne.caesar.impextutors.MainActivity;
import com.bourne.caesar.impextutors.Models.CourseChaptersData;
import com.bourne.caesar.impextutors.Models.RadioPodcastData;
import com.bourne.caesar.impextutors.Offline_Database.PayTable;
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

import java.io.File;
import java.util.ArrayList;

public class RadioPodcastsActivity extends AppCompatActivity implements Player.EventListener {

    private Button downloadCourseButton;
    RadioPodcastsAdapter radioPodcastsAdapterInstance;
    private ArrayList<RadioPodcastData> radioPodcastDataActivityList;
    private RecyclerView radioPodcastRecyclerView;
    ProgressBar spinnerVideoDetails;

    int newPosition = 1;
    private DownloadManager downloadManager;
    private long refid;
    private Uri Download_Uri;
    ArrayList<Long> list = new ArrayList<>();
    private PlayerView exoplayerView;

    GetRadioPodcastsFromFirebase getRadioPodcastsFromFirebase;

    TextView chapterTitleView, chapterDescription, chaptervideoDurationView;

    String videoUri;
    SimpleExoPlayer player;
    Handler mHandler;
    Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_podcasts);
        initialization();
        getRadioPodcastsFromFirebase.getRadioPodcasts();
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);


        registerReceiver(downloadCompleteReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        if(!isStoragePermissionGranted())
        {


        }
    }
    @Override
    protected void onResume() {
        super.onResume();
//        String path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + File.separator +".Impex" +File.separator +
//                radioPodcastDataActivityList.get(newPosition).getRaadioPodcastID()+".mp4";
//        File file =  new File(path);
//
//        if (file.exists()){
//            downloadCourseButton.setVisibility(View.GONE);
//        }
        if (radioPodcastDataActivityList != null) {

        String path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + File.separator + ".Impex/Radio" + File.separator +
                radioPodcastDataActivityList.get(newPosition).raadioPodcastID + ".mp4";

        File file = new File(path);
        if (file.exists()) {
            Toast.makeText(RadioPodcastsActivity.this, "This chapter is playing from the Impex app" +
                    "as it has been downloaded", Toast.LENGTH_SHORT).show();
            videoUri = path;
            downloadCourseButton.setVisibility(View.GONE);

            setUp();
        }
    }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(downloadCompleteReceiver);
        releasePlayer();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
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
    private void initialization() {
        getRadioPodcastsFromFirebase = new GetRadioPodcastsFromFirebase(this);

        radioPodcastRecyclerView = findViewById(R.id.radioRecyclerView);
        downloadCourseButton = findViewById(R.id.downloadPodcast);
        spinnerVideoDetails = findViewById(R.id.spinnerRadioPlayer);
        exoplayerView = findViewById(R.id.radioExoPlayer);
    }

    private void setViews(final int chapterNewPosition, final ArrayList<RadioPodcastData> radioPodcastDataist) {

//        Log.v("Radioac",radioPodcastDataist.get(0).radioPodcastStreamLink);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        videoUri = radioPodcastDataist.get(chapterNewPosition).radioPodcastStreamLink;
        Download_Uri = Uri.parse(videoUri);
//        chapterTitleView.setText(radioPodcastDataist.get(chapterNewPosition).CourseNumber + " .   " +
//                courseChaptersList.get(chapterNewPosition).CourseTitle);
//        chapterDescription.setText(courseChaptersList.get(chapterNewPosition).CourseDescription);
//        chaptervideoDurationView.setText(courseChaptersList.get(chapterNewPosition).CourseVideoDuration);


        final ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                String path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + File.separator + ".Impex/Radio" + File.separator +
                        radioPodcastDataist.get(chapterNewPosition).raadioPodcastID + ".mp4";

                File file = new File(path);
                if (file.exists()) {
                    Toast.makeText(RadioPodcastsActivity.this, "This chapter is playing from the Impex app" +
                            "as it has been downloaded", Toast.LENGTH_SHORT).show();
                    videoUri = path;
                    downloadCourseButton.setVisibility(View.GONE);

                    setUp();
                } else if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() ==
                        NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                        .getState() == NetworkInfo.State.CONNECTED) {
                    downloadCourseButton.setVisibility(View.VISIBLE);
                    videoUri = radioPodcastDataist.get(chapterNewPosition).radioPodcastStreamLink;
                    setUp();
                    Toast.makeText(RadioPodcastsActivity.this, "Streaming from the Impex network", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(RadioPodcastsActivity.this, "No network connection Try later", Toast.LENGTH_SHORT).show();
//                    playChapterVideo.setBackgroundColor(getResources().getColor(R.color.red_500));
                }



        downloadCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                String downloadCourseName = radioPodcastDataist.get(chapterNewPosition).raadioPodcastID;
                DownloadManager.Request downloadRequest = new DownloadManager.Request(Download_Uri);
                downloadRequest.setDescription("downloading program.mp4.....");
                downloadRequest.setTitle("ProgramPay");



                downloadRequest.setDestinationInExternalFilesDir(RadioPodcastsActivity.this, Environment.DIRECTORY_DOWNLOADS,
                        "/.Impex/Radio/" + downloadCourseName + ".mp4");
                ;

//                String downloadstore = ProgramFeaturesActivity.this,Environment.DIRECTORY_DOWNLOADS,
//                        "/.Impex/programnumber.mp4"
//                downloadRequest.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/.Impex/programnumber.mp4");
                downloadRequest.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                downloadCourseButton.setVisibility(View.GONE);
                long reference_download_ID = downloadManager.enqueue(downloadRequest);

                list.add(reference_download_ID);
            }
        });
    }

        BroadcastReceiver downloadCompleteReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            long downloadTag = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

            list.remove(downloadTag);
            if (list.isEmpty()){
//                String chapterTitle = courseChaptersList.get(chapterPosition).CourseID;
//                PayTable payTable = new PayTable(chapterTitle);
//                payViewModel.InsertPayID(payTable);
                NotificationCompat.Builder notification = new NotificationCompat.Builder(RadioPodcastsActivity.this);
                Intent intentstart = new Intent(RadioPodcastsActivity.this, MainActivity.class);
                intent.putExtra(PaaymentSuccessfulActivity.TRANSACTION_ID, "Your program is downloaded you would be abale to view offline...");

                PendingIntent pendingIntent = PendingIntent.getActivity(RadioPodcastsActivity.this,0, intentstart, PendingIntent.FLAG_UPDATE_CURRENT);


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

    public void getRadioPodcastSuccess(final ArrayList<RadioPodcastData> radioPodcastDataArrayList){

        radioPodcastsAdapterInstance = new RadioPodcastsAdapter(radioPodcastDataArrayList,RadioPodcastsActivity.this);

//        radioPodcastDataActivityList = radioPodcastDataArrayList;

        radioPodcastsAdapterInstance.setRadioListener(new RadioPodcastsAdapter.Listener() {
            @Override
            public void podcastOnClick(int position) {
                newPosition = position;
                radioPodcastDataActivityList = radioPodcastDataArrayList;
                setViews(position, radioPodcastDataArrayList);
            }
        });
        radioPodcastsAdapterInstance.notifyDataSetChanged();
        radioPodcastRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        radioPodcastRecyclerView.setLayoutManager(linearLayoutManager);
        radioPodcastRecyclerView.setAdapter(radioPodcastsAdapterInstance);
    }

    private void setUp() {
        initializePlayer();
        if (videoUri == null) {
            Toast.makeText(this, "Video Link is broken or empty", Toast.LENGTH_SHORT).show();
            return;
        }
        spinnerVideoDetails.setVisibility(View.VISIBLE);
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
            exoplayerView.setPlayer(player);
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

