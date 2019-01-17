package com.bourne.caesar.impextutors.UI_Components.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bourne.caesar.impextutors.R;
import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreviewCourseVideoDialogFragment extends DialogFragment {

    public static final String VIDEO_URL = "videourl";
    VideoView videoView;
    String videoUrlString;
    MediaController mediaController;
    public PreviewCourseVideoDialogFragment() {
        // Required empty public constructor
    }

    public static PreviewCourseVideoDialogFragment newVideoDialogInstance(String urlvideosent){
        Bundle arguments = new Bundle();

        arguments.putString(VIDEO_URL, urlvideosent);
        PreviewCourseVideoDialogFragment previewCourseVideoDialogFragment = new PreviewCourseVideoDialogFragment();
        previewCourseVideoDialogFragment.setArguments(arguments);
        return previewCourseVideoDialogFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_preview_course_video_dialog, container, false);

        videoUrlString = getArguments().getString(VIDEO_URL);
        videoView = view.findViewById(R.id.previewvideoview);
       Uri uri = Uri.parse(videoUrlString);
       videoView.setVideoURI(uri);
       mediaController = new MediaController(getActivity());
        mediaController.setAnchorView(videoView);

        videoView.start();
        return view;

    }


}
