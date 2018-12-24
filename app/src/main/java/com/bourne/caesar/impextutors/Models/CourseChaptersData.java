package com.bourne.caesar.impextutors.Models;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class CourseChaptersData {

    public String CourseTitle;
    public String CourseDescription;
    public String CourseNumber;
    public String CourseVideoDuration;
    public String CourseVideoStream;
    public String CourseAudio;

    public CourseChaptersData() {
    }

    public CourseChaptersData(String courseTitle, String courseDescription,
                              String courseNumber, String courseVideoDuration, String courseVideoStream, String courseAudio) {
        this.CourseTitle = courseTitle;
        CourseDescription = courseDescription;
        CourseNumber = courseNumber;
        CourseVideoDuration = courseVideoDuration;
        CourseVideoStream = courseVideoStream;
        CourseAudio = courseAudio;
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
