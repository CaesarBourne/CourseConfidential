package com.bourne.caesar.impextutors.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsLetterData implements Parcelable {

    public String newsTitle;
    public String newsID;
    public String newsReadDuration;
    public String newsContent;
    public String newsContentPreView;
    public String newsDate;

    public NewsLetterData() {
    }

    public NewsLetterData(String newsTitle, String newsID, String newsReadDuration,
                          String newsContent, String newsContentPreView, String newsDate) {
        this.newsTitle = newsTitle;
        this.newsID = newsID;
        this.newsReadDuration = newsReadDuration;
        this.newsContent = newsContent;
        this.newsContentPreView = newsContentPreView;
        this.newsDate = newsDate;
    }
    public NewsLetterData(Parcel parcel) {
        newsTitle = parcel.readString();
        newsID = parcel.readString();
        newsReadDuration = parcel.readString();
        newsContent = parcel.readString();
        newsContentPreView = parcel.readString();
        newsDate = parcel.readString();
    }

    public static final Parcelable.Creator<NewsLetterData> CREATOR =
            new Parcelable.Creator<NewsLetterData>(){

                @Override
                public NewsLetterData createFromParcel(Parcel source) {
                    return new NewsLetterData(source);
                }

                @Override
                public NewsLetterData[] newArray(int size) {
                    return new NewsLetterData[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(newsTitle);
        dest.writeString(newsID);
        dest.writeString(newsReadDuration);
        dest.writeString(newsContent);
        dest.writeString(newsContent);
        dest.writeString(newsContentPreView);
        dest.writeString(newsDate);

    }
}
