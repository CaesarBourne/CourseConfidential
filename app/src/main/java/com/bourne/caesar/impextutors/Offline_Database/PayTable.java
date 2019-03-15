package com.bourne.caesar.impextutors.Offline_Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "payment_table")
public class PayTable {

    @PrimaryKey (autoGenerate = true)
    private int courseID;

    private String courseName;

    public PayTable(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getCourseID() {
        return courseID;
    }
}
