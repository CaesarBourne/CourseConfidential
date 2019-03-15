package com.bourne.caesar.impextutors.Models;

public class CoursePayStatus {
    private String courseTitle;
    private String courseID;

    public CoursePayStatus() {
    }

//    @Override
//    public boolean equals( Object obj) {
//
//        if(obj == null)
//            return  true;
//        String courseTitle = (String) obj;
//
//        if(courseTitle == this.getCourseTitle())
//            return true;
//
//        return true;
//    }


    public CoursePayStatus(String courseTitle, String courseID) {
        this.courseTitle = courseTitle;
        this.courseID = courseID;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }
}
