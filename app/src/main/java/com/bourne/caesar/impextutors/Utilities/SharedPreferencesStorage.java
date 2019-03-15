package com.bourne.caesar.impextutors.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesStorage {

    public static final String SHARED_PREF_FILE ="sharedfile";
    private Context mycontext;
    private static SharedPreferencesStorage newsharedInstance;

    private SharedPreferencesStorage(Context mycontext) {
        this.mycontext = mycontext;
    }


    public static synchronized SharedPreferencesStorage getSharedPrefInstance( Context mycontext){
        if (newsharedInstance == null){
            newsharedInstance = new SharedPreferencesStorage(mycontext);
        }
        return newsharedInstance;
    }


    public void saveCourseID( String CourseID){
        SharedPreferences mysharedpreferences = mycontext.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor sharededitor = mysharedpreferences.edit();
        sharededitor.putString(Constants.IMPEX_COURSES_STORAGE, CourseID);
        sharededitor.apply();
    }
    public void saveUserImage( String userImage){
        SharedPreferences mysharedpreferences = mycontext.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor sharededitor = mysharedpreferences.edit();
        sharededitor.putString(Constants.IMPEX_USER_IMAGE, userImage);
        sharededitor.apply();
    }
    public String getUserImages(String userImage){
        SharedPreferences tokenSharedPreferences = mycontext.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        String userImageString = tokenSharedPreferences.getString(userImage, null);
        return userImageString;
    }

    public void saveCurrency( String currencyType){
        SharedPreferences mysharedpreferences = mycontext.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor sharededitor = mysharedpreferences.edit();
        sharededitor.putString(Constants.IMPEX_CURRENCY, currencyType);
        sharededitor.apply();
    }
    public String getCurrency(){
        SharedPreferences tokenSharedPreferences = mycontext.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        String userImageString = tokenSharedPreferences.getString(Constants.IMPEX_CURRENCY, null);
        return userImageString;
    }

//    public void saveSession(String sessionString){
//        SharedPreferences mysharedpreferences = mycontext.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
//        SharedPreferences.Editor sharededitor = mysharedpreferences.edit();
//        sharededitor.putString(Constants.SAVED_SESSION, sessionString);
//        sharededitor.apply();
//    }
//
//    public void saveUserId(int userid){
//        SharedPreferences mysharedpreferences = mycontext.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
//        SharedPreferences.Editor sharededitor = mysharedpreferences.edit();
//        sharededitor.putInt(Constants.USER_ID, userid);
//        sharededitor.apply();
//    }
//
//    public boolean isLoggedIn(){
//        SharedPreferences logsharedpreference = mycontext.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
//
//        boolean logedStatus = logsharedpreference.getInt(Constants.USER_ID, -1) != -1;
//        return logedStatus;
//    }
//    public String getBasicID(String tokenKey){
//        SharedPreferences tokenSharedPreferences = mycontext.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
//        String userToken = tokenSharedPreferences.getString(Constants.COOKIE_SAVED_USER, null);
//        return userToken;
//    }
//    public String getSession(String sessionKey){
//        SharedPreferences tokenSharedPreferences = mycontext.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
//        String session = tokenSharedPreferences.getString(sessionKey, null);
//        return session;
//    }
        public String getCoursePayStatus(String courseID){
        SharedPreferences tokenSharedPreferences = mycontext.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        String userCookie = tokenSharedPreferences.getString(courseID, null);
        return userCookie;
    }
    public int getUserId(String useridKey){
        SharedPreferences uidsharedPreferences = mycontext.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        int userId = uidsharedPreferences.getInt(useridKey, -1);
        return userId;
    }
    public void clearLogout(){
        SharedPreferences logoutsharedpreferences = mycontext.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor logoutsharededitor = logoutsharedpreferences.edit();
        logoutsharededitor.clear();
        logoutsharededitor.apply();
    }
}
