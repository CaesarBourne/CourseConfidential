package com.bourne.caesar.impextutors.UI_Components.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bourne.caesar.impextutors.Adapters.FeaturedProgramsHorizontaalAdapter;
import com.bourne.caesar.impextutors.FirebaseTasksCore.GetFeaturedCoursesListHomeFragment;
import com.bourne.caesar.impextutors.FirebaseTasksCore.GetProgramFeaturesForHomeFragment;
import com.bourne.caesar.impextutors.Models.CourseFeaturesData;
import com.bourne.caesar.impextutors.Models.FeatureCoursesData;
import com.bourne.caesar.impextutors.R;
import com.bourne.caesar.impextutors.UI_Components.Activities.ProgramFeaturesActivity;
import com.bourne.caesar.impextutors.Utilities.Constants;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ArrayList<String> coursetitlestring, coursepricestring;
    ArrayList<Integer> couraseimages;
    RecyclerView featuredCoursesrecyclerView;
    FeaturedProgramsHorizontaalAdapter featuredProgramsHorizontaalAdapter;
    private GetProgramFeaturesForHomeFragment getProgramFeaturesForHomeFragment;
    Button basicButton, intermediateButton, advanceButton, customerServiceButton, tradefinacebutton, businessmanagementbutton,
            specialistTradeButton, documentryCreditButton ;
    private GetFeaturedCoursesListHomeFragment getFeaturedCoursesListHomeFragment;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getFeaturedCoursesListHomeFragment = new GetFeaturedCoursesListHomeFragment(this);
        getFeaturedCoursesListHomeFragment.getFeaturedCourses();
        initialization(view);


//        for (int i = 0; i < FixedData.CourseTitles.length; i++){
//            coursetitlestring.add(FixedData.CourseTitles[i]);
//        }
//        for (int i= 0; i < FixedData.CoursePrice.length;i++){
//            coursepricestring.add(FixedData.CoursePrice[i]);
//        }
//        for (int i= 0; i < FixedData.CourseImages.length;i++){
//            couraseimages.add(FixedData.CourseImages[i]);
//        }


        viewsAction();

        return view;
    }
    public void getFeaturedCourseList(final ArrayList<FeatureCoursesData> featureCoursesDataArrayList){
        getProgramFeaturesForHomeFragment = new GetProgramFeaturesForHomeFragment(this);
        if (featureCoursesDataArrayList != null){
            getProgramFeaturesForHomeFragment.getFeaturedProgramFeatures(featureCoursesDataArrayList);
        }

    }



    public void getProgramFeaturesSuccess(final ArrayList<CourseFeaturesData> courseFeaturesDataArrayList){

        featuredCoursesrecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        featuredCoursesrecyclerView.setLayoutManager(layoutManager);
            featuredProgramsHorizontaalAdapter = new FeaturedProgramsHorizontaalAdapter(courseFeaturesDataArrayList, getActivity());
            featuredCoursesrecyclerView.setAdapter(featuredProgramsHorizontaalAdapter);
        featuredProgramsHorizontaalAdapter.setListener(new FeaturedProgramsHorizontaalAdapter.Listener() {
            @Override
            public void onCourseClick(int position) {
                Intent intent = new Intent(getActivity(), ProgramFeaturesActivity.class);
                intent.putExtra(ProgramFeaturesActivity.PROGRAM_ID, courseFeaturesDataArrayList.get(position).getProgramId());
                startActivity(intent);

            }
        });

    }



    private void initialization(View view) {
        basicButton = view.findViewById(R.id.basicbutton);
        intermediateButton = view.findViewById(R.id.intermediatebutton);
        advanceButton = view.findViewById(R.id.advancebutton);
        customerServiceButton = view.findViewById(R.id.customerServiceButton);
        tradefinacebutton= view.findViewById(R.id.tradefinacebutton);
        businessmanagementbutton = view.findViewById(R.id.businessmanagementbutton);
        specialistTradeButton = view.findViewById(R.id.specialistTradeButton);
        documentryCreditButton = view.findViewById(R.id.documentrycredit_button);

        featuredCoursesrecyclerView = view.findViewById(R.id.featuredrecyclerView);
        coursetitlestring = new ArrayList<>();
        couraseimages = new ArrayList<>();
        coursepricestring = new ArrayList<>();
    }




    private void viewsAction() {
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

        customerServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProgramFeaturesActivity.class);
                intent.putExtra(ProgramFeaturesActivity.PROGRAM_ID, Constants.IMPEX_CUSTOMER_SERVICE);
                startActivity(intent);
            }
        });

        tradefinacebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProgramFeaturesActivity.class);
                intent.putExtra(ProgramFeaturesActivity.PROGRAM_ID, Constants.IMPEX_TRADE_FINANCE);
                startActivity(intent);
            }
        });

        businessmanagementbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProgramFeaturesActivity.class);
                intent.putExtra(ProgramFeaturesActivity.PROGRAM_ID, Constants.IMPEX_BUSINESS_MANAGEMENT);
                startActivity(intent);
            }
        });

        specialistTradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProgramFeaturesActivity.class);
                intent.putExtra(ProgramFeaturesActivity.PROGRAM_ID, Constants.IMPEX_SPECIALIST_TRADE_FINANCE);
                startActivity(intent);
            }
        });

        documentryCreditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProgramFeaturesActivity.class);
                intent.putExtra(ProgramFeaturesActivity.PROGRAM_ID, Constants.IMPEX_DOCUMENTRY_CREDIT);
                startActivity(intent);
            }
        });
    }


}
