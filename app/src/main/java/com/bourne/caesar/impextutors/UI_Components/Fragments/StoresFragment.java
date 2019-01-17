package com.bourne.caesar.impextutors.UI_Components.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bourne.caesar.impextutors.Adapters.StoresFragmentAdapter;
import com.bourne.caesar.impextutors.Models.CourseFeaturesData;
import com.bourne.caesar.impextutors.R;
import com.bourne.caesar.impextutors.TasksCore.GetProgramFeaturesForStores;
import com.bourne.caesar.impextutors.UI_Components.Activities.CheckoutPayActiviy;
import com.bourne.caesar.impextutors.UI_Components.Activities.OrderDetailsActivity;
import com.bourne.caesar.impextutors.UI_Components.Fragments.StoresFragmentList.AllCoursesListFragment;
import com.bourne.caesar.impextutors.UI_Components.Fragments.StoresFragmentList.ExportBooksFargment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoresFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    GetProgramFeaturesForStores getProgramFeaturesForStores;
    StoresPagerAdapter storesPagerAdapter;
    StoresFragmentAdapter storesFragmentAdapter;
    RecyclerView storesAllRecycler;
    public StoresFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getProgramFeaturesForStores = new GetProgramFeaturesForStores(this);
        getProgramFeaturesForStores.getProgramFeatures();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_stores, container, false);
        initialization(view);

        storesAllRecycler.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        storesAllRecycler.setLayoutManager(linearLayoutManager);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(storesPagerAdapter);
        return view;
    }

    private void initialization(View view) {
        viewPager= view.findViewById(R.id.myViewpager);
        tabLayout = view.findViewById(R.id.myTablayout);
        storesPagerAdapter = new StoresPagerAdapter(getFragmentManager());
        storesAllRecycler = view.findViewById(R.id.storesAllRecycler);
    }

    public void getProgramFeaturesSuccess(final List<CourseFeaturesData> courseFeaturesData){
        storesFragmentAdapter = new StoresFragmentAdapter(courseFeaturesData, getActivity());
        storesFragmentAdapter.setListener(new StoresFragmentAdapter.Listener() {
            @Override
            public void onCourseClicked(int position) {
                Intent intent = new Intent(getActivity(), OrderDetailsActivity.class);
                intent.putExtra(OrderDetailsActivity.COURSE_PRICE, courseFeaturesData.get(position).getProgramfee());
                intent.putExtra(OrderDetailsActivity.COURSE_POSITION, position);
                intent.putExtra(OrderDetailsActivity.COURSE_TITLE, courseFeaturesData.get(position).getProgramTitle());
                startActivity(intent);
            }
        });
        storesAllRecycler.setAdapter(storesFragmentAdapter);
    }

    public class StoresPagerAdapter extends FragmentPagerAdapter{

        public StoresPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "All";
                case 1:
                    return "Export Books";
                case 2:
                    return "CD";
                case 3:
                    return "DVD";
            }
            return null;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new AllCoursesListFragment();
                case 1:
                    return new ExportBooksFargment();
                case 2:
                    return new AllCoursesListFragment();
                case 3:
                    return new ExportBooksFargment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
