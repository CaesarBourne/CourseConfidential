package com.bourne.caesar.impextutors.UI_Components.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bourne.caesar.impextutors.Models.CourseChaptersData;
import com.bourne.caesar.impextutors.R;
import com.bourne.caesar.impextutors.UI_Components.Activities.ChaptersAudioDetailsActivity;
import com.bourne.caesar.impextutors.UI_Components.Activities.ChaptersDetailActivity;
import com.bourne.caesar.impextutors.UI_Components.Activities.ProgramFeaturesActivity;

import java.util.ArrayList;


public class ChapterTypeFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CHAPTER_LIST = "lecturelist";

    // TODO: Rename and change types of parameters
    private String chapterList;
    private String mParam2;
    private ArrayList<CourseChaptersData> courseChaptersList;

    private Button audioLectureButton, videoLectureButton;

    public ChapterTypeFragment() {
        // Required empty public constructor
    }



    public static ChapterTypeFragment newInstance(ArrayList<CourseChaptersData> courseChaptersList) {
        ChapterTypeFragment fragment = new ChapterTypeFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(CHAPTER_LIST, courseChaptersList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            courseChaptersList =  getArguments().getParcelableArrayList(CHAPTER_LIST);
            chapterList = getArguments().getString(CHAPTER_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chapter_type, container, false);
        audioLectureButton = view.findViewById(R.id.audioLectureButton);
        videoLectureButton = view.findViewById(R.id.videoLectureButton);


        audioLectureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
                Intent intent = new Intent(getActivity(), ChaptersAudioDetailsActivity.class);
                intent.putExtra(ChaptersAudioDetailsActivity.EXTRA_CHAPTER_ARRAY , courseChaptersList);
                intent.putExtra(ChaptersAudioDetailsActivity.EXTRA_CHAPTER_POSITION , 0);
                startActivity(intent);
            }
        });

        videoLectureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
                Intent intent = new Intent(getContext(), ChaptersDetailActivity.class);
                intent.putExtra(ChaptersDetailActivity.EXTRA_CHAPTER_ARRAY , courseChaptersList);
                intent.putExtra(ChaptersDetailActivity.EXTRA_CHAPTER_POSITION , 0);
                startActivity(intent);
            }
        });
        return view;
    }



}
