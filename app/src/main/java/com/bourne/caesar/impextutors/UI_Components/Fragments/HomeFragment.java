package com.bourne.caesar.impextutors.UI_Components.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bourne.caesar.impextutors.Adapters.FeaturedProgramsHorizontaalAdapter;
import com.bourne.caesar.impextutors.R;
import com.bourne.caesar.impextutors.UI_Components.Activities.ProgramFeaturesActivity;
import com.bourne.caesar.impextutors.Utilities.Constants;
import com.bourne.caesar.impextutors.Utilities.FixedData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ArrayList<String> coursetitlestring, coursepricestring;
    ArrayList<Integer> couraseimages;
    RecyclerView featuredCoursesrecyclerView;
    Button basicButton, intermediateButton, advanceButton;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        basicButton = view.findViewById(R.id.basicbutton);
        intermediateButton = view.findViewById(R.id.intermediatebutton);
        advanceButton = view.findViewById(R.id.advancebutton);
        featuredCoursesrecyclerView = view.findViewById(R.id.featuredrecyclerView);
        coursetitlestring = new ArrayList<>();
        couraseimages = new ArrayList<>();
        coursepricestring = new ArrayList<>();
        featuredCoursesrecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        featuredCoursesrecyclerView.setLayoutManager(layoutManager);
        for (int i = 0; i < FixedData.CourseTitles.length; i++){
            coursetitlestring.add(FixedData.CourseTitles[i]);
        }
        for (int i= 0; i < FixedData.CoursePrice.length;i++){
            coursepricestring.add(FixedData.CoursePrice[i]);
        }
        for (int i= 0; i < FixedData.CourseImages.length;i++){
            couraseimages.add(FixedData.CourseImages[i]);
        }
        FeaturedProgramsHorizontaalAdapter featuredProgramsHorizontaalAdapter = new FeaturedProgramsHorizontaalAdapter(coursetitlestring,coursepricestring,couraseimages);
        featuredProgramsHorizontaalAdapter.setListener(new FeaturedProgramsHorizontaalAdapter.Listener() {
            @Override
            public void onCourseClick(int position) {
                Intent intent = new Intent(getActivity(), ProgramFeaturesActivity.class);
                intent.putExtra(ProgramFeaturesActivity.PROGRAM_ID, FixedData.CourseID[position]);
                startActivity(intent);

            }
        });
        featuredCoursesrecyclerView.setAdapter(featuredProgramsHorizontaalAdapter);
        basicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProgramFeaturesActivity.class);
                intent.putExtra(ProgramFeaturesActivity.PROGRAM_ID, Constants.IMPEX_BASIC);
                startActivity(intent);
            }
        });

        intermediateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProgramFeaturesActivity.class);
                intent.putExtra(ProgramFeaturesActivity.PROGRAM_ID, Constants.IMPEX_INTERMIDIATE);
                startActivity(intent);
            }
        });

        advanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProgramFeaturesActivity.class);
                intent.putExtra(ProgramFeaturesActivity.PROGRAM_ID, Constants.IMPEX_ADVANCE);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initialization() {

    }

    private void bindViews() {


    }


}
