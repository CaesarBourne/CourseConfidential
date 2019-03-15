package com.bourne.caesar.impextutors.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class RadioPodcastData implements Parcelable  {

    public String radioPodcastTitle;
    public String radioPodcastStreamLink;
    public String radioPodcastDate;
    public String radioPodcastDuration;
    public String raadioPodcastID;

    public RadioPodcastData() {
    }

    public RadioPodcastData(String radioPodcastTitle, String radioPodcastStreamLink, String radioPodcastDate,
                            String radioPodcastDuration, String raadioPodcastID) {
        this.radioPodcastTitle = radioPodcastTitle;
        this.radioPodcastStreamLink = radioPodcastStreamLink;
        this.radioPodcastDate = radioPodcastDate;
        this.radioPodcastDuration = radioPodcastDuration;
        this.raadioPodcastID = raadioPodcastID;
    }

    public RadioPodcastData(Parcel parcel) {
        raadioPodcastID = parcel.readString();
        radioPodcastDate = parcel.readString();
        radioPodcastDuration = parcel.readString();
        radioPodcastStreamLink = parcel.readString();
        radioPodcastTitle = parcel.readString();




    }




        public static final Parcelable.Creator<RadioPodcastData> CREATOR =
            new Parcelable.Creator<RadioPodcastData>(){

                @Override
                public RadioPodcastData createFromParcel(Parcel source) {
                    return new RadioPodcastData(source);
                }

                @Override
                public RadioPodcastData[] newArray(int size) {
                    return new RadioPodcastData[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(radioPodcastTitle);
        dest.writeString(radioPodcastStreamLink);
        dest.writeString(radioPodcastDate);
        dest.writeString(radioPodcastDuration);
        dest.writeString(raadioPodcastID);

    }
}
