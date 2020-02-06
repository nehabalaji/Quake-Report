package com.example.android.quakereport;

public class Earth {

    private double mMagnitude;
    private String mPlace;
    private String mDate;
    private long mTimeInMilliSeconds;
    private String mURL;



    public Earth(Double Magnitude, String Place, long TimeinMilliSeconds, String url) {
        mMagnitude = Magnitude;
        mPlace = Place;
        mTimeInMilliSeconds = TimeinMilliSeconds;
        mURL = url;
    }

    public double getmMagnitude() {
        return mMagnitude;
    }

    public String getmPlace() {
        return mPlace;
    }

    public String getmDate() {
        return mDate;
    }

    public long getmTimeInMilliSeconds(){
        return mTimeInMilliSeconds;
    }

    public String getmURL(){
        return mURL;
    }
}


