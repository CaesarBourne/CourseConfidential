package com.bourne.caesar.impextutors.Offline_Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "payment_table")
public class PayTable {

    @PrimaryKey
    private int courseID;

    public PayTable(int courseID) {
        this.courseID = courseID;
    }

    public int getCourseID() {
        return courseID;
    }
}
