package com.bourne.caesar.impextutors.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class FeatureCoursesData implements Parcelable {
    public String featureCourseName;

    public FeatureCoursesData(String featureCourseName) {
        this.featureCourseName = featureCourseName;
    }

    public FeatureCoursesData() {
    }
    public FeatureCoursesData(Parcel parcel) {
        featureCourseName = parcel.readString();

    }

    public static final Parcelable.Creator<FeatureCoursesData> CREATOR =
            new Parcelable.Creator<FeatureCoursesData>(){

                @Override
                public FeatureCoursesData createFromParcel(Parcel source) {
                    return new FeatureCoursesData(source);
                }

                @Override
                public FeatureCoursesData[] newArray(int size) {
                    return new FeatureCoursesData[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(featureCourseName);


    }
}
