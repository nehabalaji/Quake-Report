package com.example.android.quakereport;

public class Earth {

    private String mMagnitude;
    private String mPlace;
    private String mDate;
    private long mTimeInMilliSeconds;



    public Earth(String Magnitude, String Place, long TimeinMilliSeconds) {
        mMagnitude = Magnitude;
        mPlace = Place;
        mTimeInMilliSeconds = TimeinMilliSeconds;
    }

    public String getmMagnitude() {
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
}


