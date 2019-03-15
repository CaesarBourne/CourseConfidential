package com.bourne.caesar.impextutors.Models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class CourseChaptersData implements Parcelable {

    public String CourseTitle;
    public String CourseDescription;
    public String CourseNumber;
    public String CourseVideoDuration;
    public String CourseVideoStream;
    public String CourseAudio;
    public String CourseID;


    public CourseChaptersData() {
    }

    public CourseChaptersData(String courseTitle, String courseDescription, String courseNumber,
                              String courseVideoDuration, String courseVideoStream, String courseAudio, String courseID) {
        CourseTitle = courseTitle;
        CourseDescription = courseDescription;
        CourseNumber = courseNumber;
        CourseVideoDuration = courseVideoDuration;
        CourseVideoStream = courseVideoStream;
        CourseAudio = courseAudio;
        CourseID = courseID;
    }
    //    public CourseChaptersData(String courseTitle, String courseDescription,
//                              String courseNumber, String courseVideoDuration, String courseVideoStream, String courseAudio) {
//        this.CourseTitle = courseTitle;
//        CourseDescription = courseDescription;
//        CourseNumber = courseNumber;
//        CourseVideoDuration = courseVideoDuration;
//        CourseVideoStream = courseVideoStream;
//        CourseAudio = courseAudio;
//    }

    public CourseChaptersData(Parcel parcel) {
        CourseTitle = parcel.readString();
        CourseDescription = parcel.readString();
        CourseNumber = parcel.readString();
        CourseVideoDuration = parcel.readString();
        CourseVideoStream = parcel.readString();
        CourseAudio = parcel.readString();
        CourseID = parcel.readString();
    }

    public static final Parcelable.Creator<CourseChaptersData> CREATOR =
            new Parcelable.Creator<CourseChaptersData>(){

                @Override
                public CourseChaptersData createFromParcel(Parcel source) {
                    return new CourseChaptersData(source);
                }

                @Override
                public CourseChaptersData[] newArray(int size) {
                    return new CourseChaptersData[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(CourseTitle);
        dest.writeString(CourseDescription);
        dest.writeString(CourseNumber);
        dest.writeString(CourseVideoDuration);
        dest.writeString(CourseVideoStream);
        dest.writeString(CourseAudio);
        dest.writeString(CourseID);
    }

//    public String getCourseTitle() {
//        return CourseTitle;
//    }
//
//    public String getCourseDescription() {
//        return CourseDescription;
//    }
//
//    public String getCourseNumber() {
//        return CourseNumber;
//    }
//
//    public String getCourseVideoDuration() {
//        return CourseVideoDuration;
//    }
//
//    public String getCourseVideoStream() {
//        return CourseVideoStream;
//    }
//
//    public String getCourseAudio() {
//        return CourseAudio;
//    }
}
