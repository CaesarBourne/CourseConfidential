package com.bourne.caesar.impextutors.UI_Components.Activities;

import android.Manifest;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bourne.caesar.impextutors.R;

import java.util.ArrayList;

public class TestDownload extends AppCompatActivity {

    private DownloadManager downloadManager;
    private long refid;
    private Uri Download_Uri;
    ArrayList<Long> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_download);
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        registerReceiver(onComplete,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));


//        Download_Uri = Uri.parse("http://www.gadgetsaint.com/wp-content/uploads/2016/11/cropped-web_hi_res_512.png");
        Download_Uri = Uri.parse("https://peptribe-uploads.s3.amazonaws.com/upload/videos/2018/08/Q9xilJ2zY2wjtXO5gsmn_17_3f3a2681d60daae03e11a10075672c06_video.mp4");

        TextView  btnSingle = (TextView) findViewById(R.id.single);

        TextView btnMultiple = (TextView) findViewById(R.id.multiple);


        if(!isStoragePermissionGranted())
        {


        }


        btnMultiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                list.clear();

                for(int i = 0; i < 2; i++)
                {
                    DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                    request.setAllowedOverRoaming(false);
                    request.setTitle("Program Downloading " + "Sample_" + i + ".png");
                    request.setDescription("Downloading " + "Sample_" + i + ".png");
                    request.setVisibleInDownloadsUi(true);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/Programs/"  + "/" + "Program_" + i + ".png");

                    refid = downloadManager.enqueue(request);


                    Log.e("OUTNM", "" + refid);

                    list.add(refid);

                }


            }
        });


        btnSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                list.clear();

                DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                request.setAllowedOverRoaming(false);
                request.setTitle("GadgetSaint Downloading " + "ProgramVid" + ".mp4");
                request.setDescription("Downloading " + "Program" + ".mp4");
                request.setVisibleInDownloadsUi(true);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/ProgramId/"  + "/" + "Program" + ".mp4");


                refid = downloadManager.enqueue(request);


                Log.e("OUT", "" + refid);

                list.add(refid);

            }
        });



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





    BroadcastReceiver onComplete = new BroadcastReceiver() {

        public void onReceive(Context ctxt, Intent intent) {




            long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);


            Log.e("IN", "" + referenceId);

            list.remove(referenceId);


            if (list.isEmpty())
            {


                Log.e("INSIDE", "" + referenceId);
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(TestDownload.this)
                                .setSmallIcon(R.mipmap.impexlogo)
                                .setContentTitle("GadgetSaint")
                                .setContentText("All Download completed");


                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(455, mBuilder.build());


            }

        }
    };




    @Override
    protected void onDestroy() {


        super.onDestroy();

        unregisterReceiver(onComplete);



    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){


            // permission granted
            Toast.makeText(this, "Permissions granted", Toast.LENGTH_LONG).show();
        }
    }
}
