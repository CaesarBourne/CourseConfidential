package com.bourne.caesar.impextutors;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bourne.caesar.impextutors.UI_Components.Activities.AddProgramChaptersActivity;
import com.bourne.caesar.impextutors.UI_Components.Activities.AddProgramFeaturesActivity;
import com.bourne.caesar.impextutors.UI_Components.Activities.LoginActivity;
import com.bourne.caesar.impextutors.UI_Components.Fragments.CoursesFragment;
import com.bourne.caesar.impextutors.UI_Components.Fragments.HomeFragment;
import com.bourne.caesar.impextutors.UI_Components.Fragments.MyAccountFragment;
import com.bourne.caesar.impextutors.UI_Components.Fragments.StoresFragment;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    Toolbar tabToolbar;
    FrameLayout mainFragmentsLayout;
    Fragment homeFragment, coursesFragment, storesFragment, myAccountFragment;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFragmentsLayout = findViewById(R.id.fragmentsmainframe);
        homeFragment = new HomeFragment();
        setFragmentView(homeFragment);
        initialization();
        setSupportActionBar(tabToolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, tabToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Intent intentHome = new Intent(MainActivity.this, MainActivity.class);
                        intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentHome);
                        return true;
                    case R.id.nav_radiopodcast:
                        Intent intentradopodcasts = new Intent(MainActivity.this, MainActivity.class);
                        intentradopodcasts.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentradopodcasts);
                        return true;
                    case R.id.nav_ebooks:
                        Intent intentebooks = new Intent(MainActivity.this, MainActivity.class);
                        intentebooks.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentebooks);
                        return true;
                    case R.id.nav_importexport:
                        Intent intentimportexport = new Intent(MainActivity.this, MainActivity.class);
                        intentimportexport.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentimportexport);
                        return true;


                    case R.id.nav_customerservice:
                        Intent intentcustomerservice = new Intent(MainActivity.this, MainActivity.class);
                        intentcustomerservice.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentcustomerservice);
                        return true;
                    case R.id.nav_invitefriends:
                        Intent intentinvitefriends = new Intent(MainActivity.this, MainActivity.class);
                        intentinvitefriends.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentinvitefriends);
                        return true;
                    case R.id.nav_aboutapp:
                        Intent intentaboutapp = new Intent(MainActivity.this, AddProgramFeaturesActivity.class);
                        intentaboutapp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentaboutapp);
                        return true;
                    case R.id.nav_setingss:
                        Intent intentsettings = new Intent(MainActivity.this, AddProgramChaptersActivity.class);
                        intentsettings.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentsettings);
                        return true;
                    case R.id.nav_logout:
                        startAlertDialogLogout();
                        return true;

                }
                return false;
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        homeFragment = new HomeFragment();
        coursesFragment = new CoursesFragment();
        storesFragment = new StoresFragment();
        myAccountFragment = new MyAccountFragment();

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        setFragmentView(homeFragment);
                        return true;
                    case R.id.navigation_courses:
                        setFragmentView(coursesFragment);
                        return true;
                    case R.id.navigation_store:
                        setFragmentView(storesFragment);
                        return true;
                    case R.id.navigation_myaccount:
                        setFragmentView(myAccountFragment);
                        return true;

                }
                return false;
            }
        });

//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    private void setFragmentView(Fragment newFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentsmainframe, newFragment);
        fragmentTransaction.commit();

    }

//    private void bindViews() {
//        featuredCoursesrecyclerView.setHasFixedSize(true);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        featuredCoursesrecyclerView.setLayoutManager(layoutManager);
//        for (int i= 0; i < FixedData.CourseTitles.length;i++){
//            coursetitlestring.add(FixedData.CourseTitles[i]);
//        }
//        for (int i= 0; i < FixedData.CoursePrice.length;i++){
//            coursepricestring.add(FixedData.CoursePrice[i]);
//        }
//        for (int i= 0; i < FixedData.CoursePrice.length;i++){
//            couraseimages.add(FixedData.CourseImages[i]);
//        }
//        FeaturedProgramsHorizontaalAdapter featuredCoursesAdapter = new FeaturedProgramsHorizontaalAdapter(coursetitlestring,coursepricestring,couraseimages);
//        featuredCoursesrecyclerView.setAdapter(featuredCoursesAdapter);
//
//    }

    private void initialization() {


        tabToolbar =  findViewById(R.id.mytabtoolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return super.onCreateOptionsMenu(menu) ;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logoutUser:
                startAlertDialogLogout();
                return true;
            default
                    :return super.onOptionsItemSelected(item);
        }

    }
    public void startAlertDialogLogout(){
        new AlertDialog.Builder(this).setTitle("Logout")
                .setMessage("Are you sure you want to Logout")
                .setIcon(R.drawable.logoutwarning)
                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        logoutUser();
                        Toast.makeText(MainActivity.this, "You have succesfully signed out", Toast.LENGTH_LONG).show();
                        LoginActivity.startActivityInstance(MainActivity.this, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
    public void logoutUser(){

        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            FirebaseAuth.getInstance().signOut();

        }
    }

}
